package cn.com.dubbo.service.order.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.LogisticChannelModel;
import cn.com.dubbo.model.LogisticCompany;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.AreaService;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.service.order.Edb_PA_OrderService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;
import cn.com.dubbo.util.TmUtil;


//从e店宝同步平安的订单
@Service("edbPaOrderService")
//@Transactional
public class Edb_PA_OrderServiceImpl implements Edb_PA_OrderService{	
	
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
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private GoodsMapper goodsMapper;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public int pageSize = 40;//static
	
	private static final Logger logger = Logger.getLogger(Edb_PA_OrderServiceImpl.class);
	//--------------------------------------------------------------------------------------

	private String testUrl = BaseData.edbUrl;
	// 申请的appkey
	private String appkey =  BaseData.appkey;
	// 申请的secret
	private  String secret = BaseData.secret;
	// 申请的token
	private String token = BaseData.token;
	// 主帐号
	private String dbhost = BaseData.dbhost;
	// 返回格式 
	private String format = BaseData.format;
	
	/**
	 * 查询卖家交易数据,按照页码查询
	 * @param pageNo
	 * @return    
	 */
	public String getTradesSoldGetResponse(int pageNo,int pageSize,String[] str) {
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
        apiparamsMap.put("method", "edbTradeGet");//添加请求参数——接口名称
        String fields="storage_id,tid,transaction_id,customer_id,shop_name,out_tid,out_pay_tid,shopid,order_channel,order_from,buyer_id,buyer_name,type,status,abnormal_status," +
        		"merge_status,receiver_name,receiver_mobile,phone,province,city,district,address,post,email,is_bill,invoice_name,invoice_situation,invoice_title,invoice_type,invoice_content,pro_totalfee,order_totalfee," +
        		"reference_price_paid,invoice_fee,cod_fee,refund_totalfee,discount_fee,discount,channel_disfee,merchant_disfee,order_disfee,commission_fee,is_cod,cost_point,point,royalty_fee," +
        		"express_no,express,express_coding,online_express,sending_type,real_income_freight,real_pay_freight,freight_expain," +
        		"tid_net_weight,tid_net_weight,tid_time,pay_time,get_time,review_orders_time," +
        		"distributer,distribut_time," +
        		"book_delivery_time,delivery_operator,delivery_time,file_operator,file_time,finish_time,modity_time," +
        		"good_receive_time,receive_time,verificaty_time,alipay_id,alipay_status,pay_mothed,pay_status,platform_status,deliver_status," +
        		"buyer_message,service_remarks,system_remarks,other_remarks,message,message_time,delivery_name,is_new_customer," +
        		"product_num,sku,item_num,single_num," +
        		"plat_send_status,plat_type,is_adv_sale,provinc_code,city_code,area_code,express_code,last_returned_time,last_refund_time,deliver_centre,deliver_station," +
        		"jd_delivery_time,storage_id,Tid,pro_detail_code,pro_name,Specification,Barcode,scombination," +
        		"Isgifts,gift_num,pro_num,send_num,refund_num,refund_renum,inspection_num,Timeinventory,cost_price,sell_price,original_price,sys_price,Ferght,item_discountfee," +
        		"inspection_time,Shopid,out_tid,out_proid,out_prosku,Proexplain,buyer_memo,seller_remark,product_no,brand_number,brand_name," +
        		"discount_amount,credit_amount,MD5_encryption,review_orders_operator,delivery_status,iscombination";        
		apiparamsMap.put("fields", fields);
		apiparamsMap.put("date_type", "订货日期");//获取日期   订货日期
		
//		$params['out_tid'] = "15310803805";                //外部订单ID
//		$params['order_channel'] = "直营网店";
		
		//TODO
//		apiparamsMap.put("begin_time", "2016-07-06 10:00:08");
//		apiparamsMap.put("end_time", "2016-07-06 13:38:40");
		
		apiparamsMap.put("begin_time", str[0]);
		apiparamsMap.put("end_time", str[1]);
		apiparamsMap.put("page_size", new Integer(pageSize).toString());
		apiparamsMap.put("page_no", new Integer(pageNo).toString());

		apiparamsMap.put("dbhost", dbhost);//添加请求参数——主帐号
		apiparamsMap.put("appkey",appkey);//添加请求参数——appkey
        apiparamsMap.put("format", format);//添加请求参数——返回格式
        apiparamsMap.put("v", "2.0");//添加请求参数——版本号（目前只提供2.0版本）
        apiparamsMap.put("slencry","0");//添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
		apiparamsMap.put("ip","172.16.2.28");//添加请求参数——IP地址       172.16.2.28:是正确的      		
		
		apiparamsMap.put("shopid", "6");//shopid:店铺id   "shopid": "1"----"shop_name": "淘宝国泰永康大药房旗舰店";
//		apiparamsMap.put("proce_Status", "已确认");//TODO  
		
		apiparamsMap.put("appscret",secret);//添加请求参数——appscret
		apiparamsMap.put("token",token);//添加请求参数——token
		String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		apiparamsMap.put("timestamp", timestamp);//添加请求参数——时间戳
		
		//获取数字签名
		String sign = TmUtil.md5Signature(apiparamsMap, appkey);//appkey   secret
		apiparamsMap.put("sign", sign);
		
		StringBuilder param = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			if(e.getKey()!="appscret" && e.getKey()!="token"){
//				param.append("&").append(e.getKey()).append("=").append(e.getValue());
				if(e.getKey()=="shop_id" || e.getKey()=="wangwang_id" || e.getKey()=="date_type"){
					try {
						param.append("&").append(e.getKey()).append("=").append(TmUtil.encodeUri(e.getValue()));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}else{
					param.append("&").append(e.getKey()).append("=").append(e.getValue());
				}
			}
		}
		
		String PostData="";
		PostData=param.toString().substring(1);
		String result="";
		result=TmUtil.getResult(testUrl,PostData);
		return result;
	}		
	
	@Override
	public void dealOrder_TradesSoldGetResponse(String[] str,Long multiChannelOrderBatch){
		try {
//			int num=0;
			String tradesSoldGetResponse=null;
			int pageNo = 1;
//			int pageSize = 40;
			tradesSoldGetResponse=this.getTradesSoldGetResponse(pageNo,pageSize,str);
//			logger.info(tradesSoldGetResponse.getBody().toString());
			if(!StringUtil.isStringNotBlank(tradesSoldGetResponse)){
				logger.error("调用EDB获取平安数据为空，当前时间是："+sdf.format(new Date()));
				return;
			}
			JSONObject obj = JSONObject.fromObject(tradesSoldGetResponse);
			
			obj = JSONObject.fromObject(obj.getString("Success"));
			String totalResults_Str = obj.getString("total_results");
			int totalResults=0;
			if(StringUtil.isStringNotBlank(totalResults_Str)){
				totalResults=new Integer(totalResults_Str).intValue();
			}    
			
			int page_index=0;
			if (totalResults<pageSize) {
				page_index++;
				this.saveTradesSoldGetResponse6667(tradesSoldGetResponse,0,multiChannelOrderBatch);
				logger.info("当前时间是："+sdf.format(new Date())+"---"+str[0]+"--"+str[1]+"第"+page_index+"页,  总记录数："+totalResults);
			} else {
				int num=0;
				for (int i = 0; i < 1; i++) {//TODO
//					tempMap.put("totalResults", totalResults);
					page_index++;
					Thread.sleep(10);
					logger.info("当前时间是："+sdf.format(new Date())+"---"+str[0]+"--"+str[1]+"----第"+page_index);
					try {
						tradesSoldGetResponse=this.getTradesSoldGetResponse(i+1,pageSize,str);
					} catch (Exception e) {
						logger.error("调用EDB接口获取平安数据异常："+e.getMessage(),e);
						Thread.sleep(3000);
						tradesSoldGetResponse=this.getTradesSoldGetResponse(i+1,pageSize,str);
					}
					
					Map<String,Object> map=this.saveTradesSoldGetResponse6667(tradesSoldGetResponse,0,multiChannelOrderBatch);
					int totalResults_Temp=0;
					totalResults_Temp=new Integer(map.get("totalResults").toString()).intValue();
					if (totalResults_Temp<pageSize) {
						num=num+totalResults_Temp;
						break;
					} else {
						num=num+40;
						continue;
					}
			    }
				logger.info("时间段："+str[0]+"--"+str[1]+"  总记录数："+num);
			}
		}catch(Exception e1 ){
			logger.error("执行平安分页获取信息失败："+e1.getMessage(),e1);
			
		}
		return;
	}
	/**
	 * 查询卖家交易数据:解析返回的对象，保存到数据库
	 * @param trades_JSONArray
	 * @throws Exception 
	 * @returnss
	 */
	public Map<String,Object> saveTradesSoldGetResponse6667(String tradesSoldGetResponse,int flag_save_update,Long multiChannelOrderBatch) {
		
		OrderModel orderModel=new OrderModel();
		try {
			
			Map<String,Object> tempMap = new HashMap<String,Object>();
			String retultStr=tradesSoldGetResponse;
			JSONObject obj = JSONObject.fromObject(retultStr);
			
			String success=obj.getString("Success");
			if (StringUtil.isStringNotBlank(success)) {
				obj = JSONObject.fromObject(obj.get("Success").toString());
				int totalResults=new Integer(obj.getString("total_results")).intValue();
				tempMap.put("totalResults", totalResults);
				obj = JSONObject.fromObject(obj.get("items").toString());
				JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("item").toString());
				int recordNum=channelMapper.isExistChannelOrderBatch_PA(multiChannelOrderBatch);
				if (flag_save_update!=2 && recordNum<=0) {
					//保存multiChannelOrderBatch:渠道订单批次号到表ec_multi_channel_order_batch
					MultiChannelOrderBatchModel multiChannelOrderBatchModel=new MultiChannelOrderBatchModel();
					multiChannelOrderBatchModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
					multiChannelOrderBatchModel.setMultiChannelId(4);//看表ec_multi_channel，1:天猫 ,4 PA
					if(trades_JSONArray!=null){
						multiChannelOrderBatchModel.setOrderAmount(trades_JSONArray.size());
					}
					multiChannelOrderBatchModel.setAddTime(sdf.format(new Date()));
					channelMapper.saveChannelOrder(multiChannelOrderBatchModel);
				}
				
			    if(trades_JSONArray.size()>0){
			      List<OrderModel> orderModelList=new ArrayList<OrderModel>();
			      for(int j=0;j<trades_JSONArray.size();j++){  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			        JSONObject trades_JSONObject = trades_JSONArray.getJSONObject(j);
//			        trades_JSONObject.isEmpty();
			        if (trades_JSONObject.isEmpty()) {
						continue;
					}
			        
			        String status66 = trades_JSONObject.getString("status");
			        if(StringUtil.isBlank(status66)){
			        	logger.error("调用E店宝的接口：edbTradeGet【获取订单信息】，返回的status(orderStatus)为空");
//			        	throw new RuntimeException();
			        }
			        
	        		int orderStatus_temp=0;
			        if (status66.equals("未确认")) {
			        	orderStatus_temp=1;
			        }
			        if (status66.equals("已确认")) {
				        String delivery_status = trades_JSONObject.getString("delivery_status");
				        if(StringUtil.isStringNotBlank(delivery_status) && (delivery_status.equals("已发货"))){
				        	orderStatus_temp=6;
				        }else{
				        	orderStatus_temp=2;
				        }
			        }
			        String platStatus = trades_JSONObject.getString("platform_status");
			        
			        if (status66.equals("已作废")||platStatus.equals("交易关闭")) {
			        	orderStatus_temp=7;
			        }
			        //TODO
			        if (status66.equals("已归档") || status66.equals("已财务审核")) {
			        	orderStatus_temp=14;//已完成
//			        	continue;
			        }
			        //此处的思路：参照订单导入。第一步：根据E店宝的tid进行判断，分支处理。第二：不判断是否数据库是否存在该订单，直接更新订单状态。订单状态的更新后入为主
//			        String out_tid = trades_JSONObject.getString("out_tid");//外部平台单号
			        int number=0;
//			        number=orderMapper.isOrderByNo(tid_temp);
			        
			        String tid_e = trades_JSONObject.getString("tid");
			        number=orderMapper.isTid(tid_e);
			        
			        //第一种情况：只改变了订单状态，E店宝不会生成新的订单tid,直接更新订单状态
			        //第二种情况：订单改变了订单状态，而且该订单其它信息也改变了,E店宝此时会生成新的订单号.
			                    //如果数据库中OrderModel表orderNo已经存在，删除原来该订单的所有相关信息，重新插入。
			                    //如果数据库中OrderModel表orderNo不存在，直接插入。
			        if (number>0) {
			        	//表里只要有该订单，直接更新。更新订单状态：orderStatus
				        if (orderStatus_temp==0) {
				        	orderStatus_temp=1;
						}
		        		orderModel.setOrderStatus(orderStatus_temp);
		        		orderModel.setTid(tid_e);
		        		orderModel.setGet_time(trades_JSONObject.getString("get_time"));
		        		orderModel.setEditTime(sdf.format(new Date()));
		        		System.out.println("订单存在,更新状态,订单号:"+orderModel.getTid() + " , 状态:"+orderStatus_temp); 
		        		orderModel.setSendStatus("Y");
		        		orderMapper.updateOrderStatus_TM(orderModel);
//		        		logger.info("更新订单状态-get_time-editTime");
					} else {
				        //---------------如果以上的num<0,即不存在,直接保存到数据库,进行如下处理-------------------------------------------------------------------------------------------------------------
				      //平安的不加前缀,后期在处理
						String tid_PA =  trades_JSONObject.getString("out_tid");//外部平台单号
				        orderModel.setOrderStatus(orderStatus_temp);
				        orderModel.setTid(tid_e);
				        orderModel.setGet_time(trades_JSONObject.getString("get_time"));//获取日期
				        orderModel.setMultiChannelId(4);
				    
				        if (multiChannelOrderBatch.longValue()!=111) {
				        	orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
						}
				        
				    	String pay_status = trades_JSONObject.getString("pay_status");//付款状态 
				    	if(StringUtil.isStringNotBlank(pay_status) && (pay_status.equals("已付款"))){
				    		orderModel.setIsPay("Y");
				    	}else{
			    			orderModel.setIsPay("N");
			    		}
				    	
				    	orderModel.setMultiChannelOrderNo(trades_JSONObject.getString("out_tid"));//渠道订单号
				    	
				    	orderModel.setOrderNo(tid_PA);
				    	String tid_time = trades_JSONObject.getString("tid_time");
				    	if(StringUtil.isStringNotBlank(tid_time)){
					        orderModel.setPlatformCreateTime(tid_time);
					        orderModel.setCommitTime(tid_time);
				    	}  
				    	String receiverProvinceName = trades_JSONObject.getString("province");
				    	orderModel.setProvinceName(receiverProvinceName);
				    	if(StringUtil.isStringNotBlank(receiverProvinceName)){
				    		orderModel.setProvinceId(areaService.findArea(0, receiverProvinceName));
				    	} 
				    	
				    	String receiverCity = trades_JSONObject.getString("city");
				    	orderModel.setCityName(receiverCity);
				    	if(StringUtil.isStringNotBlank(receiverCity)){
				    		orderModel.setCityId(areaService.findArea(orderModel.getProvinceId(), receiverCity));
				    	} 
				    	String receiverDistrict = trades_JSONObject.getString("district");
				    	orderModel.setAreaName(receiverDistrict);
				    	if(StringUtil.isStringNotBlank(receiverDistrict)){
				    		orderModel.setAreaId(areaService.findArea(orderModel.getCityId(), receiverDistrict));
				    	} 
				    	
				    	String receive_address = trades_JSONObject.getString("province")+trades_JSONObject.getString("city")+trades_JSONObject.getString("district");
				    	if(StringUtil.isStringNotBlank(receive_address)){
				    		orderModel.setReceiveAddress(receive_address);//收货地址(只包含省市区)
				    	}
				    	
				    	String detailedAddress = trades_JSONObject.getString("address");
				    	orderModel.setReceiveFullAddress(detailedAddress);//完整收货地址
				        
				    	String receiverName = trades_JSONObject.getString("receiver_name");
				    	if(StringUtil.isStringNotBlank(receiverName)){
				    		orderModel.setReceiveUser(receiverName);
				    	}   
				    	String receiverPhone = trades_JSONObject.getString("phone");
				    	if(StringUtil.isStringNotBlank(receiverPhone)){
				    		 orderModel.setReceiveTel(receiverPhone);
				    	}   
				    	String receiverMobile = trades_JSONObject.getString("receiver_mobile");
				    	if(StringUtil.isStringNotBlank(receiverMobile)){
				    		 orderModel.setReceiveMobile(receiverMobile);
				    	} 
				    	
				    	String companyName = trades_JSONObject.getString("express");//物流公司名字
				    	String logisticsCompanyCode = trades_JSONObject.getString("express_coding");//物流公司code
				    	LogisticChannelModel logisticChannelModel=null;
				    	if(StringUtil.isStringNotBlank(companyName)){
				    		int logisticCompanyId=0;
				    		if (companyName.equals("申通")) {
				    			companyName="申通快递";
				    			orderModel.setLogLogisticCompanyId(58);
							}else{
								List<LogisticCompany> logisticCompanyList=logisticMapper.findLogisticCompanyId(companyName);
					    		if (logisticCompanyList!=null && logisticCompanyList.size()>0 && (logisticCompanyList.get(0)!=null)) {
					    			logisticCompanyId=logisticCompanyList.get(0).getLogisticCompanyId();
					    		}
					    		if (logisticCompanyId!=0) {
					    			
					    			orderModel.setLogLogisticCompanyId(logisticCompanyId);
					    			
									Map<String, Object> map=new HashMap<String, Object>();
									map.put("channelType", "PA");
									map.put("logisticId", logisticCompanyId);
									String channel_code = logisticMapper.findThirdCode(map);
									
									if (StringUtil.isBlank(channel_code)) {
								    	//保存信息到表logistic_channel
							    		logisticChannelModel=new LogisticChannelModel();
							    		logisticChannelModel.setChannelType("PA");
							    		logisticChannelModel.setChannelCode(logisticsCompanyCode);
							    		logisticChannelModel.setCompanyName(companyName);
							    		logisticChannelModel.setLogisticId(logisticCompanyId);
									}
								}
							}
				    	} 
				    	
				    	String modifyTime = trades_JSONObject.getString("modity_time");
				    	if(StringUtil.isStringNotBlank(modifyTime)){
					        orderModel.setPlatformEditTime(modifyTime);
				    	}  
					    String type = trades_JSONObject.getString("type");
					    if (type.equals("正常订单")) {
					    	orderModel.setOrderType(0);//订单类型(0.正常订单  1.补发订单)
						}
					    
				    	String invoice_title = trades_JSONObject.getString("invoice_title");
				    	String invoice_content = trades_JSONObject.getString("invoice_content");
				    	if(StringUtil.isStringNotBlank(invoice_title)){
				        	orderModel.setInvoiceTitle(invoice_title);
				        	orderModel.setInvoiceContent(invoice_content);
				    	}    
				    	
//				    	String pamentType = trades_JSONObject.getString("pay_mothed");
//				        if(StringUtil.isStringNotBlank(pamentType)){
//				        	orderModel.setPaymentType(pamentType.toUpperCase());//支付宝
//				        }
				    	
				    	String postFee = trades_JSONObject.getString("real_pay_freight");//实收运费
				    	if(StringUtil.isStringNotBlank(postFee)){
				    		orderModel.setDeliveryFeeOld(new BigDecimal(postFee));
				    	}    
				    	String venderRemark = trades_JSONObject.getString("service_remarks");
				    	if(StringUtil.isStringNotBlank(venderRemark)){
				    		orderModel.setOrderNotes(venderRemark);
				    	}    
				    	String customerRemark = trades_JSONObject.getString("buyer_message");
				    	if(StringUtil.isStringNotBlank(customerRemark)){
				    		orderModel.setOrderMsg(customerRemark);
				    	}    
				    	String tradeFrom = trades_JSONObject.getString("order_channel");//订单渠道
				    	if(StringUtil.isStringNotBlank(tradeFrom)){
						    orderModel.setFromMedia(tradeFrom);
				    	}
				    	
				    	String temp_addTime=sdf.format(new Date());//添加时间
				    	if (flag_save_update!=2){
				    		orderModel.setAddTime(temp_addTime);//添加时间
				    	}else{
				    		orderModel.setEditTime(sdf.format(new Date()));//修改时间
				    	}
				    	orderModel.setHaveCfy("N");
				    	if (flag_save_update==2){
				    		orderModelList.add(orderModel);
				    	}
				    	String pay_time = trades_JSONObject.getString("pay_time");//订单渠道
				    	
				        if (flag_save_update!=2) {  //flag_save_update!=2:不是更新。暂时不需要更新订单明细
							JSONArray tradeDetails_JSONArray = JSONArray.fromObject(trades_JSONObject.getString("tid_item"));
						    if(tradeDetails_JSONArray.size()>0){
						    	  List<OrderItemModel> orderItemModelList=new ArrayList<OrderItemModel>();
						    	  List<PaymentLogModel> paymentLogList = new ArrayList<PaymentLogModel>();
						    	  
						    	  BigDecimal deliveryFee=new BigDecimal(0.0000);//运费
						    	  BigDecimal activityDiscountFee=new BigDecimal(0.0000);//优惠金额
						    	  BigDecimal paidFee=new BigDecimal(0.0000);//实际支付金额
						    	  
//						    	  BigDecimal divideFee99=new BigDecimal(0.0000);//
						    	  
							      for(int k=0;k<tradeDetails_JSONArray.size();k++){  //遍历 jsonarray 数组，把每一个对象转成 json 对象
								        JSONObject tradeDetails_JSONObject = tradeDetails_JSONArray.getJSONObject(k);  
								        if (tradeDetails_JSONObject.isEmpty()) {
											continue;
										}
								    	// primaryKey-orderItemId  title-name num-amount   price-price;//商品成交价    cid
								    	//outer_item_id-goodsNo     divide_fee-goodsSumFee;//小计金额        detailsDiscount
								        
								        //商品成交价*商品数量-优惠金额----订单明细之和；再加上订单主表的delivery_fee；等于订单明细的“小计金额”之和
								        OrderItemModel orderItemModel=new OrderItemModel();
								        
								        orderItemModel.setOrderNo(tid_PA);
								        
							    		String title = tradeDetails_JSONObject.getString("pro_name");
							    		if(StringUtil.isStringNotBlank(title)){
							    			orderItemModel.setName(title);
							    		}						    	
							    		String num = tradeDetails_JSONObject.getString("pro_num");
							    		if(StringUtil.isStringNotBlank(num)){
							    			orderItemModel.setAmount(new Long(num).longValue());//商品数量
							    		}	
							    		
							    		String sell_price = tradeDetails_JSONObject.getString("sell_price");//实际销售单价
							    		orderItemModel.setPrice(new BigDecimal(sell_price));
							    		
										BigDecimal divideFee55 = MathUtil.mul(new BigDecimal(sell_price), new BigDecimal(num));
										
										orderItemModel.setGoodsSumFee(divideFee55);//小计金额
							    		
										String iscombination = tradeDetails_JSONObject.getString("iscombination");
										
										String credit_amount55 = "0.00";
							    		credit_amount55 = tradeDetails_JSONObject.getString("credit_amount");//抵扣分摊金额
							    		if (StringUtil.isBlank(credit_amount55) || iscombination.equalsIgnoreCase("1")) {
							    			credit_amount55 = "0.00";
										}
							    		activityDiscountFee=MathUtil.add(activityDiscountFee, new BigDecimal(credit_amount55));
							    		
							    		BigDecimal oldPrice=MathUtil.div(new BigDecimal(credit_amount55), new BigDecimal(num), 4);
							    		oldPrice=MathUtil.add(new BigDecimal(sell_price), oldPrice);
//							    		String price = tradeDetails_JSONObject.getString("price");
//							    		if(StringUtil.isStringNotBlank(price)){
							    			orderItemModel.setOldPrice(oldPrice);//  oldPrice;//商品原价         price;//商品成交价
//							    		}
							    		
							    		String barcode = tradeDetails_JSONObject.getString("barcode");
							    		if(StringUtil.isStringNotBlank(barcode)){
							    			orderItemModel.setGoodsNo_69(barcode);
							    			List<GoodsModel> goodsModelList = goodsMapper.getGoodListByGoodNo69(barcode);
							    			if (goodsModelList.size()>0) {
							    				String otcFlag=goodsModelList.get(0).getOtcFlag();//是否处方药     0不是    1是
							    				if (otcFlag.equals("1")) {
							    					orderItemModel.setGoodsType(Constant.GOODS_TYPE_RX);//商品类型（other/rx(处方药))
							    					orderModel.setHaveCfy("Y");
												}else{
													orderItemModel.setGoodsType(Constant.GOODS_TYPE_OTHER);//商品类型（other/rx(处方药))
												}
											}
							    		}
							    		String product_no = tradeDetails_JSONObject.getString("product_no");
							    		if(StringUtil.isStringNotBlank(product_no)){
							    			orderItemModel.setGoodsNo(product_no);//商品编码
							    		}	
							    		
										//明细类型
										if(!StringUtil.isBlank(barcode)&&barcode.startsWith("TM")){
											orderItemModel.setGoodsListType(Constant.GOODS_TYPE_GROUP);
										}else{
											orderItemModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
											orderItemModel.setTmSource(tradeDetails_JSONObject.getString("combine_barcode"));//源tm组合码
										}
										 
										if (StringUtil.isStringNotBlank(tradeDetails_JSONObject.getString("combine_barcode"))) {
											orderItemModel.setGroupStatus("Y");//组合拆分状态
										}
										 
							    		orderItemModel.setPriceDis(new BigDecimal(credit_amount55));//优惠金额
							    		orderItemModel.setAddTime(temp_addTime);//添加时间
							    		
							    		//调用OrderUpdate方法去查询成本价和规格  
						    			orderUpdate(product_no, barcode,orderItemModel);
								        orderItemModelList.add(orderItemModel);
							    		
							    		 //以下是订单主表还需要插入的一些信息
								        
							    		String ferght = tradeDetails_JSONObject.getString("ferght");
							    		if (StringUtil.isBlank(ferght) || iscombination.equalsIgnoreCase("1")) {
							    			ferght = "0.00";
										}
							    		deliveryFee=MathUtil.add(deliveryFee, new BigDecimal(ferght));
							    		
							    		if (iscombination.equalsIgnoreCase("1")) {
							    			paidFee=MathUtil.add(paidFee, new BigDecimal(0.00));
										}
						          }
							      
							   
									
							      String buyer_id = trades_JSONObject.getString("buyer_id");//买家昵称
							      if(StringUtil.isStringNotBlank(buyer_id)){
									    orderModel.setMemberNick(buyer_id);
							      }
							      String post = trades_JSONObject.getString("post");//邮编
							      if(StringUtil.isStringNotBlank(post)){
								      boolean judgeContains = judgeContainsStr(post);
								      int num_length = post.length();
								      if (!judgeContains && num_length<9) {
								    	  orderModel.setReceivePost(new Integer(post.trim()).intValue());
									  } 
							      }

							      String order_totalfee = trades_JSONObject.getString("order_totalfee");//订单总金额
							      String order_disfee = trades_JSONObject.getString("order_disfee");//整单优惠
							      if(StringUtil.isBlank(order_disfee)){
							    	  order_disfee = "0.00";
							      }
							      
						          BigDecimal  orderPayFee_temp = new BigDecimal(0.00);
							      if(StringUtil.isStringNotBlank(order_totalfee)){
							    	  orderPayFee_temp = MathUtil.sub(new BigDecimal(order_totalfee), new BigDecimal(order_disfee));
							      }else{
							    	  orderPayFee_temp = MathUtil.sub(new BigDecimal(0.00), new BigDecimal(order_disfee));
							      }
							      
						          if (orderPayFee_temp.compareTo(new BigDecimal(0.00))<0) {
						        	  orderPayFee_temp=new BigDecimal(0.00);
								  }
								  orderModel.setOrderPayFee(orderPayFee_temp);//待支付金额
							      
							      orderModel.setDeliveryFeeOld(deliveryFee);//原运费金额
							      orderModel.setDeliveryFee(deliveryFee);//实际运费金额
							      orderModel.setActivityDiscountFee(activityDiscountFee);//优惠
							      
//							      BigDecimal paidFee66=MathUtil.add(paidFee, deliveryFee);
							      orderModel.setPaidFee(orderModel.getOrderPayFee());//实际支付的价格
							      
//							      BigDecimal skuFee=MathUtil.add(deliveryFee, activityDiscountFee);
							      BigDecimal paidFee123456=orderModel.getPaidFee();
							      BigDecimal skuFee=MathUtil.add(activityDiscountFee, paidFee123456);
							      
							      orderModel.setOrderFee(orderModel.getOrderPayFee());//订单金额
							      orderModel.setSkuFee(skuFee);//货款金额（不含优惠）
							      
	//以下是支付日志的开始 ********************************************
							      if("Y".equals(orderModel.getIsPay())){
								        //货到付款金额处理
										PaymentLogModel logModel = new PaymentLogModel();
										logModel.setBusinessType("ADD");//业务类型
										logModel.setBusinessId(tid_PA);//订单号
										//utc时间格式转换必须*1000
										logModel.setPaymentTime(pay_time);
										String pay_mothed = trades_JSONObject.getString("pay_mothed");
										logModel.setPaidFee(new BigDecimal(trades_JSONObject.getString("reference_price_paid")));//在线支付金额
										if(null != pay_mothed && !"".equals(pay_mothed)){
											if("4-在线支付".equals(pay_mothed.trim())){
												logModel.setOrderPaymentId(PayCode.XJ_ALI);
												logModel.setOrderPaymentName("在线支付");
												orderModel.setPaymentType(Constant.PAYMENT_TYPE_KDFH);// 
											}
											if("1-货到付款".equals(pay_mothed.trim())){
												logModel.setOrderPaymentId(PayCode.XX_ALI);
												logModel.setOrderPaymentName("货到付款");
												if(!"".equals(trades_JSONObject.getString("pay_mothed"))){
													logModel.setPaidFee(new BigDecimal(trades_JSONObject.getString("reference_price_paid")));//如果存在货到付款金额,则set
												}
												orderModel.setPaymentType(Constant.PAYMENT_TYPE_HDFK);// 
											}
										}
										
										logModel.setAddTime(temp_addTime);//添加时间
										if (logModel.getPaidFee() !=null && !logModel.getPaidFee().equals("0.00")) {
											paymentLogList.add(logModel);
										}
										
										//优惠金额处理 discount_fee
								      if(!"0.00".equals(trades_JSONObject.getString("discount_fee")) && null != trades_JSONObject.getString("discount_fee") && !"".equals(trades_JSONObject.getString("discount_fee"))){
								    	  PaymentLogModel logModel2 = new PaymentLogModel();
											logModel2.setBusinessType("ADD");//业务类型
											logModel2.setBusinessId(tid_PA);//订单号 
											//utc时间格式转换必须*1000
											logModel2.setPaymentTime(pay_time);
											logModel2.setOrderPaymentId(PayCode.DKQ_ALI);
											logModel2.setOrderPaymentName("抵扣券");
											String discount_fee = trades_JSONObject.getString("discount_fee");
											logModel2.setPaidFee(new BigDecimal(discount_fee));//抵扣券金额
											logModel2.setAddTime(temp_addTime);//添加时间
											if (logModel2.getPaidFee()==null || (logModel2.getPaidFee().equals(""))) {
												logModel2.setPaidFee(new BigDecimal(0.00));//实付金额
											}
											paymentLogList.add(logModel2);
								      }
								      //消耗积分
								      if(!"0.00".equals(trades_JSONObject.getString("cost_point")) && null != trades_JSONObject.getString("cost_point") && !"".equals(trades_JSONObject.getString("cost_point"))){
								    	  PaymentLogModel logModel2 = new PaymentLogModel();
								    	  logModel2.setBusinessType("ADD");//业务类型
								    	  logModel2.setBusinessId(tid_PA);//订单号 
								    	  //utc时间格式转换必须*1000
								    	  logModel2.setPaymentTime(pay_time);
								    	  logModel2.setOrderPaymentId(PayCode.JF_ALI);
								    	  logModel2.setOrderPaymentName("积分");
								    	  logModel2.setPaidFee(new BigDecimal(trades_JSONObject.getString("cost_point")));//抵扣券金额
								    	  logModel2.setAddTime(temp_addTime);//添加时间
								    	  if (logModel2.getPaidFee()==null || (logModel2.getPaidFee().equals(""))) {
								    		  logModel2.setPaidFee(new BigDecimal(0.00));//实付金额
								    	  }
								    	  paymentLogList.add(logModel2);
								      }
								      //整单优惠
								      if(!"0.00".equals(trades_JSONObject.getString("order_disfee")) && null != trades_JSONObject.getString("order_disfee") && !"".equals(trades_JSONObject.getString("order_disfee"))){
								    	  PaymentLogModel logModel2 = new PaymentLogModel();
								    	  logModel2.setBusinessType("ADD");//业务类型
								    	  logModel2.setBusinessId(tid_PA);//订单号 
								    	  //utc时间格式转换必须*1000
								    	  logModel2.setPaymentTime(pay_time);
								    	  logModel2.setOrderPaymentId(PayCode.ZDYH_ALI);
								    	  logModel2.setOrderPaymentName("整单优惠");
								    	  logModel2.setPaidFee(new BigDecimal(trades_JSONObject.getString("order_disfee")));//抵扣券金额
								    	  logModel2.setAddTime(temp_addTime);//添加时间
								    	  if (logModel2.getPaidFee()==null || (logModel2.getPaidFee().equals(""))) {
								    		  logModel2.setPaidFee(new BigDecimal(0.00));//实付金额
								    	  }
								    	  paymentLogList.add(logModel2);
								      }
							     }	
//以下是支付日志的结束****************************************************
									
							      orderModel.setSendStatus("Y");
							      orderModelList.add(orderModel);

						    	
					             if (orderItemModelList.size()>0) {
					            	 System.out.println("订单号："+orderModel.getMultiChannelOrderNo());
					            	 orderService.saveTrade(orderModel,orderItemModelList,paymentLogList,logisticChannelModel);
								 }
					         }
						}
					}
			    }
			    
		         if (orderModelList.size()>0) {
	            	 if (flag_save_update==2) {
	            		 for (int h = 0; h < orderModelList.size(); h++) {
	            			 OrderModel temOrderModel=orderModelList.get(h);
	            			 int exist=orderMapper.isOrderByNo(temOrderModel.getOrderNo());
	            			 // 0 不存在，1 存在
	            			 if (exist==0) {
	            				 orderModelList.remove(h);
	            				 h=h-1;
							}
						}
		     		  //批量更新订单
	        		 orderService.updateBatchOrder666(orderModelList);
					} 
				 }
			  } 
			}
			return tempMap;
		} catch (Exception e) {
			logger.error("平安组装数据失败,订单号："+orderModel.getOrderNo()+" ,异常信息"+e.getMessage(),e);
		}
		return null;
	 }		

	//补充成本价和规格
	public void orderUpdate(String goodNo, String goodsNo_69,OrderItemModel orderItemModel){
		GoodsModel result;
		//查成本价
		//GoodsModel result = goodsMapper.getGoodByGoodNo(goodNo);
		if(!StringUtil.isBlank(goodNo)){
			result  = goodsService.findGoodNo(goodNo);
		}else{
			result  = goodsService.findGood69(goodsNo_69);
		}
		if(result == null){
//			logger.info("商品号："+goodNo+"  69码："+goodsNo_69+"该商品暂无信息！");
		}else{
			orderItemModel.setCostPrice(result.getCostPriceAverage());
			orderItemModel.setStandard(result.getStandard());
		}
	}
	
	/**  
     * 该方法主要使用正则表达式来判断字符串中是否包含字母  
     * @param cardNum 待检验的  
     * @return 返回是否包含  
     */  
    public boolean judgeContainsStr(String cardNum) {  
        String regex=".*[a-zA-Z]+.*";  
        Matcher m=Pattern.compile(regex).matcher(cardNum);  
        return m.matches();  
    }
    
    public static void main(String[] args) {
    	String str = new Edb_PA_OrderServiceImpl().getTradesSoldGetResponse(1, 10, new String[]{"2016-04-14 00:00:00","2016-04-15 20:00:00"});
    	System.out.println(str);
    }
    
}
