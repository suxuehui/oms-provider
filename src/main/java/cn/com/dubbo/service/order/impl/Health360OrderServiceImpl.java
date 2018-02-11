package cn.com.dubbo.service.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.mapper.OrderLogMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.LogisticChannelModel;
import cn.com.dubbo.model.LogisticCompany;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.AreaService;
import cn.com.dubbo.service.order.Health360OrderService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.DateUtil;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.galaxy.pop.api.client.ApiException;
import com.galaxy.pop.api.client.DefaultPopApiClient;
import com.galaxy.pop.api.client.PopApiClient;
import com.galaxy.pop.api.client.request.OfflineSendRequest;
import com.galaxy.pop.api.client.request.TradesSoldGetRequest;
import com.galaxy.pop.api.client.request.TradesSoldIncrementGetRequest;
import com.galaxy.pop.api.client.response.OfflineSendResponse;
import com.galaxy.pop.api.client.response.TradesSoldGetResponse;

@Service("health360OrderService")
//@Transactional
public class Health360OrderServiceImpl implements Health360OrderService{	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ChannelMapper channelMapper;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private LogisticMapper logisticMapper;
	
//	@Resource
//	private OrderDeliveryMapper orderDeliveryMapper;
	
	@Resource
	private OrderPackageMapper orderPackageMapper;
	
	@Resource
	private OrderLogMapper orderLogMapper;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger logger = Logger.getLogger(Health360OrderServiceImpl.class);
	
	/**
	 * 查询卖家交易数据,按照页码查询
	 * @param pageNo
	 * @return    
	 */
	public TradesSoldGetResponse getTradesSoldGetResponse(int pageNo,int pageSize,String[] str) {
		try {
		// key: D0000293   测试密钥: 6F5559D4CB82833323A235DF24F0B6E75E725DD20EF5D1B839A9C677D01F9120    13FC726AF3C77D5A5DC7D7E0682C1623EB10F8597559CC0104A4CE90BCF4F149
		PopApiClient client = new DefaultPopApiClient(Constant.URL360,  Constant.key360,Constant.secretkey360);
//		start_create;
//		end_create;
		TradesSoldGetRequest request = new TradesSoldGetRequest();
		
		
//		request.setStartCreated("2016-06-05 11:50:00");
//		request.setEndCreated("2016-06-05 13:39:59");
		request.setStartCreated(str[0]);
		request.setEndCreated(str[1]);
		
/*	    TradesSoldIncrementGetRequest request = new TradesSoldIncrementGetRequest();
		request.setStartModified("2016-06-07 11:50:00");
		request.setEndModified("2016-06-07 13:30:00");*/
		
		request.setHaveCFY("1");//是否包含处方药(1-包含,0-普通,””和null-普通订单)   haveCFY  request.setHaveCFY("0"):只查询不包含处方药的；request.setHaveCFY("1")：包含和不包含处方药的都查询
//		request.setVenderId("241");//商家编号(不能为空)
		request.setPdState("ALL");
		request.setPageNo(pageNo);
		request.setPageSize(pageSize);
		
		//pay_time,end_time,delivery_time,logistics_no,total_fee,discount_fee,merchants_receivable_amount,refund_id,payment
		request.setFields("tid,create_time,receiver_province,receiver_province_code,receiver_city,receiver_city_code,receiver_district,receiver_district_code,receiver_address,buyer_nick,receiver_zip,receiver_mobile" +
				",receiver_name,receiver_phone,logistics_company_code,logistics_company,modify_time,status,haveCFY,invoice,payment_type,post_fee,trade_details,vender_remark,customer_remark,trade_from" +
				",pay_time,end_time,delivery_time,logistics_no,total_fee,discount_fee,merchants_receivable_amount,refund_id,payment");
//		request.check();
		TradesSoldGetResponse response=null;
		
			response = client.execute(request);
			return response;
		}catch(Exception e1 ){
			e1.printStackTrace();
			logger.error("360订单抓取,查询卖家交易数据错误：时间段是 ："+str[0]+"-----" +str[1]+"   错误信息是"+e1.getMessage(),e1);
		}
//		System.out.println(response.getBody().toString());
		return null;
	}		
	
	/**
	 * 查询卖家交易数据:解析返回的对象，保存到数据库
	 * @param trades_JSONArray
	 * @throws Exception 
	 * @return map
	 */
	public Map<String,Object> saveTradesSoldGetResponse(TradesSoldGetResponse tradesSoldGetResponse,int flag_save_update,Long multiChannelOrderBatch) {
		
		Map<String,Object> tempMap = new HashMap<String,Object>();
		String retultStr=tradesSoldGetResponse.getBody().toString();
		JSONObject obj = JSONObject.fromObject(retultStr);
		obj = JSONObject.fromObject(obj.get("root").toString());
//		int totalResults=new Integer(obj.get("totalResults").toString()).intValue();
		String success=obj.get("success").toString();
//		String trades_temp=obj.getString("trades");
		
		if (success.equalsIgnoreCase("true")) {
//			System.out.println("23156   "+obj.getString("trades"));
		  if (StringUtil.isStringNotBlank(obj.getString("trades"))) {
			  JSONArray trades_JSONArray = JSONArray.fromObject(obj.getString("trades"));
			
			int recordNum=channelMapper.isExistChannelOrderBatch(multiChannelOrderBatch);
			if (flag_save_update!=2 && recordNum<=0) {
				//保存multiChannelOrderBatch:渠道订单批次号到表ec_multi_channel_order_batch
				MultiChannelOrderBatchModel multiChannelOrderBatchModel=new MultiChannelOrderBatchModel();
				multiChannelOrderBatchModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
				multiChannelOrderBatchModel.setMultiChannelId(3);
				if(trades_JSONArray!=null){
					multiChannelOrderBatchModel.setOrderAmount(trades_JSONArray.size());
				}
				multiChannelOrderBatchModel.setAddTime(sdf.format(new Date()));
//				sdf.format(multiChannelOrderBatch.toString());
				channelMapper.saveChannelOrder(multiChannelOrderBatchModel);
			}
			
		    if(trades_JSONArray.size()>0){
		      List<OrderModel> orderModelList=new ArrayList<OrderModel>();
		      
		      for(int j=0;j<trades_JSONArray.size();j++){  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		        JSONObject trades_JSONObject = trades_JSONArray.getJSONObject(j);
//		        trades_JSONObject.isEmpty();
		        if (trades_JSONObject.isEmpty()) {
					continue;
				}
		        OrderModel orderModel=new OrderModel();
//					-------------------------------------
//					批次号：年月日时分秒--OrderBatchIdUtil类的multiChannelOrderBatch     post_fee--deliveryFeeOld
//					receiver_name,receiver_phone,logistics_company_code,logistics_company,modify_time,status,haveCFY,invoice,payment_type,post_fee
				//------------以下是新增的--------------------------------------------------
				// trade_from--from_media    customer_remark-orderMsg:用户备注 			  
//					request.setFields("tid,create_time,receiver_province,receiver_province_code,receiver_city,receiver_city_code,receiver_district,
//			        receiver_district_code,receiver_address,buyer_nick,receiver_zip,receiver_mobile" +
//							",receiver_name,receiver_phone,logistics_company_code,logistics_company,modify_time,status,haveCFY,invoice,
//			        payment_type,post_fee,trade_details,vender_remark,customer_remark,trade_from");
		        
		        orderModel.setMultiChannelId(3);
//		        Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch();
//		        orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
		        //TODO
		        if (multiChannelOrderBatch.longValue()!=111) {
		        	orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
				}
		        
		        
		    	String payment = trades_JSONObject.getString("payment");//payment:实付金额。 pay_status
		    	if(StringUtil.isStringNotBlank(payment)){
		    		orderModel.setIsPay("Y");
		    	}else{
		    		orderModel.setIsPay("N");
		    	}
		        
		        //----------------------------------------------------
		    	String tid = trades_JSONObject.getString("tid");
		    	orderModel.setMultiChannelOrderNo(tid);//渠道订单号
		    	if(StringUtil.isStringNotBlank(tid)){
		    		tid="36" + tid;
		    		orderModel.setOrderNo(tid);
		    	}
		    	//createTime是毫秒
		    	String createTime_UTC = trades_JSONObject.getString("createTime");
		    	if(StringUtil.isStringNotBlank(createTime_UTC)){
			        Date utc_to_Date=DateUtil.getUTCToDate(createTime_UTC);
			        orderModel.setPlatformCreateTime(sdf.format(utc_to_Date));
			        orderModel.setCommitTime(sdf.format(utc_to_Date));
		    	}  
		    	 // TODO
		    	String receiverProvinceName = trades_JSONObject.getString("receiverProvince");
		    	orderModel.setProvinceName(receiverProvinceName);
		    	if(StringUtil.isStringNotBlank(receiverProvinceName)){
//		    		AreaModel areaModel=areaMapper.findArea(receiverProvinceName);
		    		orderModel.setProvinceId(areaService.findArea(0, receiverProvinceName));
//			        receiverProvinceName根据省会名称，从redis查询省会ID
//			        orderModel.setProvinceId(areaModel.getAreaId());
		    	} 
		    	
		    	String receiverCity = trades_JSONObject.getString("receiverCity");
		    	orderModel.setCityName(receiverCity);
		    	if(StringUtil.isStringNotBlank(receiverCity)){
		    		orderModel.setCityId(areaService.findArea(orderModel.getProvinceId(), receiverCity));
		    	} 
		    	String receiverDistrict = trades_JSONObject.getString("receiverDistrict");
		    	orderModel.setAreaName(receiverDistrict);
		    	if(StringUtil.isStringNotBlank(receiverDistrict)){
		    		orderModel.setAreaId(areaService.findArea(orderModel.getCityId(), receiverDistrict));
		    	} 
		    	
		    	String detailedAddress = trades_JSONObject.getString("receiverAddress");
		    	String tempReceiveAddress=receiverProvinceName.concat(receiverCity).concat(receiverDistrict);
		    	orderModel.setReceiveAddress(tempReceiveAddress);//收货地址
		    	orderModel.setReceiveFullAddress(tempReceiveAddress.concat(detailedAddress));//完整收货地址
				//县
//				String county = Jd_User.getCounty();
//				orderModel.setAreaId(areaService.findArea(orderModel.getCityId(), county));
		    	
		    	
		        
		    	String receiverName = trades_JSONObject.getString("receiverName");
		    	if(StringUtil.isStringNotBlank(receiverName)){
		    		orderModel.setReceiveUser(receiverName);
		    	}   
		    	String receiverPhone = trades_JSONObject.getString("receiverPhone");
		    	if(StringUtil.isStringNotBlank(receiverPhone)){
		    		 orderModel.setReceiveTel(receiverPhone);
		    	}   
		    	String receiverMobile = trades_JSONObject.getString("receiverMobile");
		    	if(StringUtil.isStringNotBlank(receiverMobile)){
		    		 orderModel.setReceiveMobile(receiverMobile);
		    	}  
		    	String companyName = trades_JSONObject.getString("logisticsCompany");
		    	String logisticsCompanyCode = trades_JSONObject.getString("logisticsCompanyCode");
		    	LogisticChannelModel logisticChannelModel=null;
		    	//如果快递公司为空,则默认是申通
		    	if(StringUtil.isBlank(companyName)){
		    		companyName="申通快递";
		    		logisticsCompanyCode="6000032";
		    	}
		    	if(StringUtil.isStringNotBlank(companyName)){
		    		int logisticCompanyId=0;
//		    		logisticCompanyId=logisticMapper.findLogisticCompanyId(companyName);
		    		List<LogisticCompany> logisticCompanyList=logisticMapper.findLogisticCompanyId(companyName);
		    		if (logisticCompanyList!=null && logisticCompanyList.size()>0 && (logisticCompanyList.get(0)!=null)) {
		    			logisticCompanyId=logisticCompanyList.get(0).getLogisticCompanyId();
		    		}
		    		
		    		if (logisticCompanyId!=0) {
		    			orderModel.setLogLogisticCompanyId(logisticCompanyId);
		    			
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("channelType", "36");
						map.put("logisticId", logisticCompanyId);
						String channel_code = logisticMapper.findThirdCode(map);
						
						if (StringUtil.isBlank(channel_code)) {
					    	//保存信息到表logistic_channel
				    		logisticChannelModel=new LogisticChannelModel();
				    		logisticChannelModel.setChannelType("36");
				    		logisticChannelModel.setChannelCode(logisticsCompanyCode);
				    		logisticChannelModel.setCompanyName(companyName);
				    		logisticChannelModel.setLogisticId(logisticCompanyId);
						}
					}
		    	} 
		    	
		    	//modifyTime是毫秒
		    	String modifyTime = trades_JSONObject.getString("modifyTime");
		    	if(StringUtil.isStringNotBlank(modifyTime)){
			        Date utc_to_Date2=DateUtil.getUTCToDate(modifyTime);
			        orderModel.setPlatformEditTime(sdf.format(utc_to_Date2));
		    	}  
		    	String status = trades_JSONObject.getString("status");
 
			    	if(StringUtil.isStringNotBlank(status)){
			    		if (status.equals("WAIT_SELLER_SEND_GOODS") || status.equals("WAIT_BUYER_PAY")) {
			    			orderModel.setOrderStatus(1);
			    		}
			    		if(status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
			    			orderModel.setOrderStatus(6);
			    		} 
			    		if(status.equals("TRADE_CLOSED")) {//交易关闭
			    			orderModel.setOrderStatus(7);
			    		} 
			    		if(status.equals("TRADE_BUYER_SIGNED")) {//买家已签收
			    			orderModel.setOrderStatus(12);
			    		} 			    		
			    		if(status.equals("TRADE_FINISHED")) {//交易成功
			    			orderModel.setOrderStatus(13);
			    		} 				    		
//			        	int orderStatus=getCodeValue(status);
//			        	orderModel.setOrderStatus(orderStatus);
			    	}  
			    	
		    	orderModel.setOrderType(0);//订单类型(0.正常订单  1.补发订单)
		    	String haveCFY = trades_JSONObject.getString("haveCFY");
		    	if("1".equals(haveCFY)){
			        orderModel.setHaveCfy("Y");
		    	}else {
		    		orderModel.setHaveCfy("N");
		    	}

		    	String invoice = trades_JSONObject.getString("invoice");
		    	if(StringUtil.isStringNotBlank(invoice)){
		        	JSONObject obj_Invoice = JSONObject.fromObject(invoice);
		        	orderModel.setInvoiceTitle(obj_Invoice.getString("title"));
		        	//TODO
		        	orderModel.setInvoiceContent(obj_Invoice.getString("content"));
		    	}    
		    	
		    	
		    	String paymentType = trades_JSONObject.getString("paymentType");
		    	String paymentType_Code=setPayMethod(paymentType);
		    	if(StringUtil.isStringNotBlank(paymentType_Code)){
			        //TODO
			        orderModel.setPaymentType(paymentType_Code);
		    	}    
		    	String postFee = trades_JSONObject.getString("postFee");
		    	if(StringUtil.isStringNotBlank(postFee)){
		    		orderModel.setDeliveryFeeOld(new BigDecimal(postFee));
		    	}    
		    	String venderRemark = trades_JSONObject.getString("venderRemark");
		    	if(StringUtil.isStringNotBlank(venderRemark)){
		    		orderModel.setOrderNotes(venderRemark);
		    	}    
		    	String customerRemark = trades_JSONObject.getString("customerRemark");
		    	if(StringUtil.isStringNotBlank(customerRemark)){
		    		orderModel.setOrderMsg(customerRemark);
		    	}    
		    	String tradeFrom = trades_JSONObject.getString("tradeFrom");
		    	if(StringUtil.isStringNotBlank(tradeFrom)){
			        //TODO    OrderModel--fromMedia      fromMedia;//来源媒体,存储ios/android/第三方渠道url
				    orderModel.setFromMedia(tradeFrom);
		    	}
		    	
		    	String temp_addTime=sdf.format(new Date());//添加时间
		    	if (flag_save_update!=2){
		    		orderModel.setAddTime(temp_addTime);//添加时间
		    	}else{
		    		orderModel.setEditTime(sdf.format(new Date()));//修改时间
		    	}
		    	
		    	if (flag_save_update==2){
		    		orderModelList.add(orderModel);
		    	}
//		        orderModelList.add(orderModel);
//		        orderMapper.saveOrder(orderModel);
		        
		        if (flag_save_update!=2) {  //flag_save_update!=2:不是更新。暂时不需要更新订单明细
					JSONArray tradeDetails_JSONArray = JSONArray.fromObject(trades_JSONObject.get("tradeDetails").toString());
				    if(tradeDetails_JSONArray.size()>0){
				    	  List<OrderItemModel> orderItemModelList=new ArrayList<OrderItemModel>();
				    	  List<PaymentLogModel> paymentLogList = new ArrayList<PaymentLogModel>();
				    	  
				    	  BigDecimal deliveryFee=new BigDecimal(0.00000);
				    	  BigDecimal activityDiscountFee=new BigDecimal(0.00000);
				    	  BigDecimal paidFee=new BigDecimal(0.00000);//支付金额
				    	  
					      for(int k=0;k<tradeDetails_JSONArray.size();k++){  //遍历 jsonarray 数组，把每一个对象转成 json 对象
						        JSONObject tradeDetails_JSONObject = tradeDetails_JSONArray.getJSONObject(k);  
						        tradeDetails_JSONObject.isEmpty();
						        if (tradeDetails_JSONObject.isEmpty()) {
									continue;
								}
						        
						    	// primaryKey-orderItemId  title-name num-amount   price-price;//商品成交价    cid
						    	//outer_item_id-goodsNo     divide_fee-goodsSumFee;//小计金额        detailsDiscount
						        
						        //商品成交价*商品数量-优惠金额----订单明细之和；再加上订单主表的delivery_fee；等于订单明细的“小计金额”之和
						        OrderItemModel orderItemModel=new OrderItemModel();
						        
						        orderItemModel.setOrderNo(tid);
						        
//					    		String primaryKey = tradeDetails_JSONObject.getString("primaryKey");
//					    		if(StringUtil.isStringNotBlank(primaryKey)){
//					    			orderItemModel.setOrderItemId(new Long(primaryKey).longValue());
//					    		}
					    		String title = tradeDetails_JSONObject.getString("title");
					    		if(StringUtil.isStringNotBlank(title)){
					    			orderItemModel.setName(title);
					    		}						    	
					    		String num = tradeDetails_JSONObject.getString("num");
					    		if(StringUtil.isStringNotBlank(num)){
					    			orderItemModel.setAmount(new Long(num).longValue());//商品数量
					    		}	
					    		String price = tradeDetails_JSONObject.getString("price");
					    		if(StringUtil.isStringNotBlank(price)){
					    			orderItemModel.setOldPrice(new BigDecimal(price));//  oldPrice;//商品原价         price;//商品成交价
					    		}						    	
					    		String outerItemId = tradeDetails_JSONObject.getString("outerItemId");
					    		if(StringUtil.isBlank(outerItemId)){
					    			outerItemId ="6923146105016";
					    		}	
					    		if(StringUtil.isStringNotBlank(outerItemId)){
					    			orderItemModel.setGoodsNo_69(outerItemId);
					    		}	
					    		
								//明细类型
								if(!StringUtil.isBlank(outerItemId)&&outerItemId.startsWith("TM")){
									orderItemModel.setGoodsListType(Constant.GOODS_TYPE_GROUP);
								}else{
									orderItemModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
								}
								
					    		String divideFee = tradeDetails_JSONObject.getString("divideFee");
					    		if(StringUtil.isStringNotBlank(divideFee)){
					    			orderItemModel.setGoodsSumFee(new BigDecimal(divideFee));//小计金额
					    		}
					    		
					    		
					    		 //以下是订单主表还需要插入的一些信息
					    		String partPostFee = tradeDetails_JSONObject.getString("partPostFee");
					    		deliveryFee=MathUtil.add(deliveryFee, new BigDecimal(partPostFee));
					    		
					    		String partDiscount = tradeDetails_JSONObject.getString("partDiscount");
					    		activityDiscountFee=MathUtil.add(activityDiscountFee, new BigDecimal(partDiscount));
					    		String detailsDiscount = tradeDetails_JSONObject.getString("detailsDiscount");
					    		activityDiscountFee=MathUtil.add(activityDiscountFee, new BigDecimal(detailsDiscount));
					    		
					    		String divideFee66 = tradeDetails_JSONObject.getString("divideFee");
					    		paidFee=MathUtil.add(paidFee, new BigDecimal(divideFee66));
					    		
					    		BigDecimal priceDis=new BigDecimal(0.00000);
					    		String partDiscount66 = tradeDetails_JSONObject.getString("partDiscount");
					    		priceDis=MathUtil.add(priceDis, new BigDecimal(partDiscount66));
					    		String detailsDiscount66 = tradeDetails_JSONObject.getString("detailsDiscount");
					    		priceDis=MathUtil.add(priceDis, new BigDecimal(detailsDiscount66));
					    		orderItemModel.setPriceDis(priceDis);//优惠金额
					    		
					    		orderItemModel.setAddTime(temp_addTime);//添加时间
						        orderItemModelList.add(orderItemModel);
						        
						        //以下是支付日志的
//						        List<PaymentLogModel> payLogList = new ArrayList<PaymentLogModel>();
//								PaymentLogModel logModel = null;
								PaymentLogModel logModel = new PaymentLogModel();
								logModel.setBusinessType("ADD");//业务类型
								logModel.setBusinessId(tid);//订单号
								
								logModel.setPaidFee(new BigDecimal(divideFee));
								//utc时间格式转换必须*1000
								logModel.setPaymentTime(sdf.format(new Date(new Long(createTime_UTC).longValue())));
//								logModel.setAddTime(sdf.format(new Date()));
								
								setPayMethod(paymentType,logModel);
								logModel.setAddTime(temp_addTime);//添加时间
								paymentLogList.add(logModel);
						        
				          }
					      
					      orderModel.setDeliveryFeeOld(deliveryFee);//原运费金额
					      orderModel.setDeliveryFee(deliveryFee);//实际运费金额
					      orderModel.setActivityDiscountFee(activityDiscountFee);//优惠
					      
					      BigDecimal paidFee66=MathUtil.sub(paidFee, deliveryFee);
					      orderModel.setPaidFee(paidFee66);//实际支付的价格
					      
//					      BigDecimal skuFee=MathUtil.add(deliveryFee, activityDiscountFee);
					      BigDecimal skuFee=MathUtil.add(activityDiscountFee, paidFee);
					      
					      skuFee=MathUtil.sub(skuFee, deliveryFee);//货款金额（不含优惠）--不包含邮费
					      
					      orderModel.setOrderFee(paidFee66);//orderFee;//订单金额
					      orderModel.setSkuFee(skuFee);//货款金额（不含优惠）
					      //-----------------------------------------------------------
					      BigDecimal temp1=MathUtil.add(skuFee, deliveryFee);
					      BigDecimal paidFee77=MathUtil.sub(temp1, activityDiscountFee);
					      orderModel.setPaidFee(paidFee77);//实际支付的价格
					      orderModel.setOrderFee(paidFee77);//orderFee;//订单金额
					      
					      orderModelList.add(orderModel);
					      if (paymentType_Code.equalsIgnoreCase("HDFK")) {
					    	  paymentLogList = new ArrayList<PaymentLogModel>();
						  }
			             if (orderItemModelList.size()>0) {
			            	 //logisticChannelModel有可能是null
			            	 orderService.saveTrade_360(orderModel,orderItemModelList,paymentLogList,logisticChannelModel);
						 }
			        }
				}
		    }
		    
	         if (orderModelList.size()>0) {
            	 if (flag_save_update==2) {
//            		 int orderModelList_num=orderModelList.size();
//            		 String orderNo_temps=null;
            		 for (int h = 0; h < orderModelList.size(); h++) {
            			 OrderModel temOrderModel=orderModelList.get(h);
            			 
//            			 if (temOrderModel.getOrderNo().equalsIgnoreCase("36A1478654946099844101")) {
//            				 temOrderModel.setOrderNo("36A1571146972665284101");
//						 }
            			 
            			 
            			 int exist=orderMapper.isOrderByNo(temOrderModel.getOrderNo());
            			 // 0 不存在，1 存在
            			 if (exist==0) {
            				 orderModelList.remove(h);
            				 h=h-1;
//            				if (h==0) {
//            					h=h-1;
//							} else {
//								h=h-1;
//							}
//            				 String	orderNo_temp=temOrderModel.getOrderNo();
						}
					}
//            		 if (orderNo_temps!=null) {
//            			 String[] orderNo_Array=orderNo_temps.split(";");
//                		 for (int i = 0; i < orderNo_Array.length; i++) {
//                			 for (int j = 0; j < orderModelList.size(); j++) {
//                				 if (orderModelList.get(j).getOrderNo().equalsIgnoreCase(orderNo_Array[i])) {
//                    				 orderModelList.remove(j);
//								}
//							}
//     					}
//					}

		     		  //批量更新订单
            		 orderService.updateBatchOrder666(orderModelList);
				} 
//            	 else {
//		 		    //批量保存订单
//					orderService.saveBatchOrder(orderModelList);
//				}
			 }
//	         tempMap.put("orderModelList", orderModelList);
//	         tempMap.put("orderItemModelList", orderItemModelList);
		    }
		  } 
		} else {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("请求360haoyao的接口：查询卖家交易数据,抛出异常，没有成功");
				e.printStackTrace();
			}
		}
		return tempMap;
	 }	
	
	//把一条订单主表记录、以及相关的订单明细记录、以及相关的支付日志记录作为一个原子。或者保存都成功，或者保存都失败
//	@Transactional
//	public void saveTrade(OrderModel orderModel,List<OrderItemModel> orderItemModelList,List<PaymentLogModel> paymentLogList){
//		boolean bool=orderService.isOrderByNo(orderModel.getOrderNo());
//		//bool
//		if (!bool) {
//			orderService.saveOrder(orderModel);
//			 //保存订单明细
//	   	    orderService.saveBatchOrderItem(orderItemModelList);
//	        //保存支付日志表
//	        orderService.savePaymentLogModelList(paymentLogList);
//		}
//		if (orderModel.getOrderNo().equalsIgnoreCase("36A1576548549642864102")) {
//			int a=2/0;
//		}
//	}
	
	
	/**
	 * 根据返回的订单状态转换为订单code
	 * @param status
	 * @return
	 */
	public int getCodeValue(String status) {
//		int orderStatus=0;
		if (status.equals("WAIT_SELLER_SEND_GOODS") || status.equals("WAIT_BUYER_PAY")) {
			return 1;
		} else if(status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
			return 6;
		} else {
			return 0;
		}
	}	
	
	//查询卖家交易数据
	@Override
	public void dealOrder_TradesSoldGetResponse(String[] str,Long multiChannelOrderBatch){
//		String[] str=DateUtil.getStartEnd();
//		Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch();
		try {
			int num=0;
			int pageNo = 1;
			int pageSize = 40;
//			String has_next=null;
			TradesSoldGetResponse tradesSoldGetResponse = this.getTradesSoldGetResponse(pageNo,pageSize,str);			
//			System.out.println(tradesSoldGetResponse.getBody().toString());
			//------------------aaaa-------------------
			if (StringUtil.isNotNullObject(tradesSoldGetResponse)) {
				JSONObject obj = JSONObject.fromObject(tradesSoldGetResponse.getBody().toString());
				
				obj = JSONObject.fromObject(obj.getString("root"));
				String totalResults_Str = obj.getString("totalResults");
				int totalResults=0;
				if(StringUtil.isStringNotBlank(totalResults_Str)){
					totalResults=new Integer(totalResults_Str).intValue();
				}    
				
				if (totalResults<=pageSize) {
					this.saveTradesSoldGetResponse(tradesSoldGetResponse,0,multiChannelOrderBatch);
				} else {
					if (totalResults%pageSize==0) {
						num=totalResults/pageSize;
					} else {
						num=totalResults/pageSize+1;
					}
					for (int i = 0; i < num; i++) {
						tradesSoldGetResponse=this.getTradesSoldGetResponse(i+1,pageSize,str);
//						System.out.println(tradesSoldGetResponse.getBody().toString());
						this.saveTradesSoldGetResponse(tradesSoldGetResponse,0,multiChannelOrderBatch);
					}
				}
			}
		}catch(Exception e1 ){
			logger.error("360订单抓取,增量查询卖家交易错误：时间段是 ："+str[0]+"-----" +str[1]+"   错误信息是"+e1.getMessage(),e1);
		}
	}	
	
	
	/**
	 * 增量查询卖家交易,按照页码查询
	 * @param pageNo
	 * @return    
	 */
	public TradesSoldGetResponse getTradesSoldIncrementGetResponse(int pageNo,int pageSize,String[] str) {
		// key: D0000293   测试密钥: 6F5559D4CB82833323A235DF24F0B6E75E725DD20EF5D1B839A9C677D01F9120    13FC726AF3C77D5A5DC7D7E0682C1623EB10F8597559CC0104A4CE90BCF4F149
		PopApiClient client = new DefaultPopApiClient(Constant.URL360,  Constant.key360,Constant.secretkey360);
//		start_create;
//		end_create;
/*		TradesSoldGetRequest request = new TradesSoldGetRequest();
		request.setStartCreated("2016-06-05 11:50:00");
		request.setEndCreated("2016-06-05 13:30:00");*/
		
	    TradesSoldIncrementGetRequest request = new TradesSoldIncrementGetRequest();
//		request.setStartModified("2016-06-07 11:50:00");
//		request.setEndModified("2016-06-07 13:30:00");
		request.setStartModified(str[0]);
		request.setEndModified(str[1]);
		
		request.setHaveCFY("1");
		request.setVenderId("241");
		request.setPdState("ALL");
		request.setPageNo(pageNo);
		request.setPageSize(pageSize);
		
		//pay_time  end_time   delivery_time  logistics_no  total_fee  discount_fee  merchants_receivable_amount  refund_id  payment
		request.setFields("tid,create_time,receiver_province,receiver_province_code,receiver_city,receiver_city_code,receiver_district,receiver_district_code,receiver_address,buyer_nick,receiver_zip,receiver_mobile" +
				",receiver_name,receiver_phone,logistics_company_code,logistics_company,modify_time,status,haveCFY,invoice,payment_type,post_fee,trade_details,vender_remark,customer_remark,trade_from" +
				",pay_time,end_time,delivery_time,logistics_no,total_fee,discount_fee,merchants_receivable_amount,refund_id,payment");
//		request.check();
		TradesSoldGetResponse response=null;
		try {
			response = client.execute(request);
			return response;
		} catch (ApiException e){
			logger.error("360订单抓取,增量查询卖家交易错误：时间段是 ："+str[0]+"-----" +str[1]+"   错误信息是"+e.getMessage(),e);
		}catch(Exception e1 ){
//			e1.printStackTrace();
			logger.error("360订单抓取,增量查询卖家交易错误：时间段是 ："+str[0]+"-----" +str[1]+"   错误信息是"+e1.getMessage(),e1);
		}
//		System.out.println(response.getBody().toString());
//		if (response==null || response.equals("")) {
//			System.err.println("方法getTradesSoldIncrementGetResponse,时间段:"+str[0]+"---"+str[1]+"的增量查询卖家交易失败");
//		}
		return null;
	}
	
	@Override
	public void dealOrder_TradesSoldIncrementGetResponse(String[] str,Long multiChannelOrderBatch){
//		String[] str=DateUtil.getStartEnd();
		try {
			int num=0;
			int pageNo = 1;
			int pageSize = 40;
//			String has_next=null;
			TradesSoldGetResponse tradesSoldGetResponse = this.getTradesSoldIncrementGetResponse(pageNo,pageSize,str);			
//			System.out.println(tradesSoldGetResponse.getBody().toString());
			//------------------aaaa-------------------
			//
			if (StringUtil.isNotNullObject(tradesSoldGetResponse)) {
				JSONObject obj = JSONObject.fromObject(tradesSoldGetResponse.getBody().toString());
				
				obj = JSONObject.fromObject(obj.getString("root"));
				String totalResults_Str = obj.getString("totalResults");
				int totalResults=0;
				if(StringUtil.isStringNotBlank(totalResults_Str)){
					totalResults=new Integer(totalResults_Str).intValue();
				}    
				
				if (totalResults<=pageSize) {
					this.saveTradesSoldGetResponse(tradesSoldGetResponse,2,new Long(111));
				} else {
					if (totalResults%pageSize==0) {
						num=totalResults/pageSize;
					} else {
						num=totalResults/pageSize+1;
					}
					for (int i = 0; i < num; i++) {
						tradesSoldGetResponse=this.getTradesSoldIncrementGetResponse(i+1,pageSize,str);
//						System.out.println(tradesSoldGetResponse.getBody().toString());
						this.saveTradesSoldGetResponse(tradesSoldGetResponse,2,new Long(111));
					}
				}
			}
		}catch(Exception e1 ){
			logger.error("360订单抓取,增量查询卖家交易错误：时间段是 ："+str[0]+"-----" +str[1]+"   错误信息是"+e1.getMessage(),e1);
		}		
	}	
	
//	@Override
//	public void test6666(){
//		MultiChannelOrderBatchModel multiChannelOrderBatchModel=new MultiChannelOrderBatchModel();
//		multiChannelOrderBatchModel.setMultiChannelOrderBatchId(111333);
//		multiChannelOrderBatchModel.setMultiChannelId(3);
//		multiChannelOrderBatchModel.setOrderAmount(222);
//		channelOrderService.saveChannelOrder(multiChannelOrderBatchModel);
//	}
	private String setPayMethod(String payment_type){
		if(payment_type.equalsIgnoreCase("A4A")){
			return "KDFH";
		}else if(payment_type.equalsIgnoreCase("A4C")){
			return "KDFH";
		}else if(payment_type.equalsIgnoreCase("A4E")){
			return "HDFK";
		}
		return null;
	}	
	
	private void setPayMethod(String payment_type,PaymentLogModel logModel){
		/*
		 * 支付方式
		 */
		if(payment_type.equalsIgnoreCase("A4A")){
			logModel.setOrderPaymentId(PayCode.WS_ALI);
			logModel.setOrderPaymentName("网上支付");
		}else if(payment_type.equalsIgnoreCase("A4C")){
			logModel.setOrderPaymentId(PayCode.YH_ALI);
			logModel.setOrderPaymentName("银行转账");
		}else if(payment_type.equalsIgnoreCase("A4E")){
			logModel.setOrderPaymentId(PayCode.XX_ALI);
			logModel.setOrderPaymentName("线下支付");
		}
	}	
	
	@Override
	public void deliveryInterface(){
		List<OrderPackage> list=orderPackageMapper.getOrderPackage_36("N");
		for (int i = 0; i < list.size(); i++) {
			OrderPackage temp_OrderPackage=list.get(i);
			//调用360的接口，如果成功，然后更新表OrderModel的orderStatus---最后更新OrderDelivery的state字段   TODO
			Boolean success = deliveryInterface(temp_OrderPackage);
			//如果成功，然后更新表OrderModel的orderStatus,最后更新OrderPackage的delivery_status字段
			if (success) {
				OrderModel orderModel = new OrderModel();
				orderModel.setOrderNo(ChannelConstant.CHANNEL_36+temp_OrderPackage.getOrder_no());
				orderModel.setOrderStatus(6);
				//更新order_info表对应单子状态，可能存在order_info表无此数据，需判断  
				int rst = orderMapper.updateOrder(orderModel);
				if(rst == 0){
					OrderLogModel orderLogModel = new OrderLogModel();
					orderLogModel.setOrderNo(temp_OrderPackage.getOrder_no());
					//单子已经发货
					orderLogModel.setOrderStateId(6);
					orderLogModel.setLogTime(sdf.format(new Date()));
					orderLogModel.setLogContent("订单号："+temp_OrderPackage.getOrder_no()+"在order_info中更新时影响行数为0，故在order_info中可能无此订单");
					orderLogModel.setAddTime(sdf.format(new Date()));
					orderLogMapper.saveOrderLog(orderLogModel);
				}
				OrderPackage orderPackage=new OrderPackage();
				String order_no=temp_OrderPackage.getOrder_no();
				orderPackage.setOrder_no(order_no);
				orderPackage.setDelivery_status("Y");
				orderPackage.setSync_time(sdf.format(new Date())); 		//存入同步时间--------add by Lichen.Zheng  2016/8/19
				orderPackageMapper.updateOrderPackage(order_no);
			}
		}
	}
	
	private Boolean deliveryInterface(OrderPackage temp_OrderPackage){
		Boolean isSuccess=false;
		try {
			PopApiClient client = new DefaultPopApiClient(Constant.URL360,  Constant.key360,Constant.secretkey360);
			OfflineSendRequest request = new OfflineSendRequest();
			
			String order_no = temp_OrderPackage.getOrder_no();
			if(order_no.startsWith(ChannelConstant.CHANNEL_36)){
				  order_no = order_no.replace(ChannelConstant.CHANNEL_36, "");
			}else if(order_no.contains("BF")){
				return false;
			}else if(order_no.contains("TH")){
				  order_no = order_no.replace("TH", "");
			} 
			
			//从晓峰的表查询出  TODO
			request.setTid(order_no); //360健康交易ID
			request.setLogisticsNo(temp_OrderPackage.getLogistic_no());//运单号.具体一个物流公司的真实运单号码。
			request.setVenderId("241");//国泰永康的商家编码  商家编码（每个商家对应唯一的编码）
			request.setCompanyCode(temp_OrderPackage.getLogistic_company_no());//shentong  物流公司代码.调用查询卖家承运商接口获取
			
			OfflineSendResponse response = client.execute(request);
			
			String errorCode=response.getErrorCode();
			if (errorCode.equalsIgnoreCase("REPEAT_DELIVERY")) {
				return true;
			}
			
			isSuccess=response.isSuccess();
			if(response.isSuccess()){
				return isSuccess;
			}else{
				OrderLogModel orderLog=new OrderLogModel();
				orderLog.setOrderNo(temp_OrderPackage.getOrder_no());
				orderLog.setLogTime(sdf.format(new Date()));
				//TODO
				orderLog.setLogContent(response.getErrorCode());
				orderLog.setAddTime(sdf.format(new Date()));
				orderLogMapper.saveOrderLog(orderLog);
				return isSuccess;
			}
			} catch (ApiException e) {
				e.printStackTrace();
			}
		return isSuccess;
	 }
	
	
	//查看360的承运物流
//	 public static void main(String[] args) {
//		 try {
//			 //查询3660所有的快递承运商
//			PopApiClient client = new DefaultPopApiClient(Constant.URL360,  Constant.key360,Constant.secretkey360);
//			LogisticsCompaniesGetRequest request = new LogisticsCompaniesGetRequest();
//			request.setLogisticsType(0);
//			LogisticsCompaniesGetResponse response = client.execute(request);
//			System.out.println(0);
//			System.out.println(JSON.toJSONString(response));
//			System.out.println(1);
//			} catch (ApiException e) {
//				e.printStackTrace();
//			}
//	}
	
	public static void main(String[] args) {
		TradesSoldGetResponse  s = new Health360OrderServiceImpl().getTradesSoldGetResponse(1, 2, new String[]{"2016-10-01 00:00:00","2016-10-10 00:00:00"});
	    System.out.println("--------------------------------");
		System.out.println(JSON.toJSONString(s)); 
	}
	 
	 
}
