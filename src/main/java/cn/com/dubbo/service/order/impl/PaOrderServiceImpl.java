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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.AreaService;
import cn.com.dubbo.service.inter.OrderLogService;
import cn.com.dubbo.service.inter.PaymentLogService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.PaOrderService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.ClassInfo;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.HttpClientUtils;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;

import com.pajk.openapi.codec.client.RequestEncoder;
import com.pajk.openapi.codec.client.RequestEntity;
import com.pajk.openapi.codec.client.ResponseDecoder;

/**
 * http://localhost:8080/dubbo-provider/index.jsp
 *
 */
@Service("paOrderService")
public class PaOrderServiceImpl implements PaOrderService{
	
	// 数据准备，服务端提供的配置参数
	public final static String apiId = "e4e0475234a625f4f78ca7477296b396#PROD"; //即上文apiId
	public final static String apiName = "pageQueryB2COrderForOpenApi"; //即上文apiName,订单查询接口
	
	public final static int pageSize = 90;//一页显示的条目数量
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private PaymentLogService payLogService;
	
	@Resource
	private ChannelMapper channelMapper;
	
	@Resource
	private OrderLogService orderLogService;
	
	private DataQueue<OrderModel> orderErrorQueue = new DataQueue<OrderModel>();
	
	private DataQueue<OrderItemModel> orderitemErrorQueue = new DataQueue<OrderItemModel>();
	
	private DataQueue<PaymentLogModel> payLogErrorQueue = new DataQueue<PaymentLogModel>();
	
	private static final Logger logger = Logger.getLogger(PaOrderServiceImpl.class);
	
	private int totalAmount = 0;//导入的订单总数量
	
	@Override
	public void dealOrder(String startTime,String endTime,boolean falg){
		
		try {
			//先置为 0
			totalAmount = 0;
			
			//订单状态
			String orderState = "0";
			Long multiChannelOrderBatch = null;
			
			if(falg){
				multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("4");
			}else{
				multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("14");
			}
			
			//已支付未发货订单：PAID
			this.orderSerach(1,"PAID",startTime,endTime,orderState,multiChannelOrderBatch);
			
			if(!orderErrorQueue.isEmpty()){
				this.dealOrderQueue(orderErrorQueue);
			}
			
			if(!orderitemErrorQueue.isEmpty()){
				this.dealOrderItemQueue(orderitemErrorQueue);
			}
			
			if(!payLogErrorQueue.isEmpty()){
				this.dealPayLogQueue(payLogErrorQueue);
			}
			
			this.saveChannelOrderBatch(multiChannelOrderBatch);
			
		} catch (Exception e) {
			logger.error("平安订单抓取错误： " +e.getMessage(),e);
		}
		
	}
	
	/**
	 * 保存批量执行信息
	 */
	@Transactional
	private void saveChannelOrderBatch(long multiChannelOrderBatch){
		
		MultiChannelOrderBatchModel channelOrderModel = new MultiChannelOrderBatchModel();
		channelOrderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
		channelOrderModel.setMultiChannelId(4);//平安
		channelOrderModel.setImportTime(sdf.format(new Date()));
		channelOrderModel.setOrderAmount(totalAmount);
		channelOrderModel.setAddTime(sdf.format(new Date()));
		channelMapper.saveChannelOrder(channelOrderModel);
		
	}
	
	
	private void dealPayLogQueue(DataQueue<PaymentLogModel> errorQueue){
		PaymentLogModel model = null;
		try {
			Thread.sleep(1000);
			logger.info("休息5秒后开始保存订单支付日志异常数据...........共 "+errorQueue.getLength()+" 条");
			while(!errorQueue.isEmpty()){
				model = errorQueue.deQueue();
				boolean bl = payLogService.isLogByOrderNo(model.getBusinessId());
				if(!bl){
					payLogService.savePaymentLog(model);
				}
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("从队列中保存平安订单支付日志失败 ： " +e.getMessage());
		}
	}
	
	
	private void dealOrderItemQueue(DataQueue<OrderItemModel> errorQueue){
		OrderItemModel model = null;
		try {
			Thread.sleep(1000);
			logger.info("休息5秒后开始保存订单Item异常数据...........共 "+errorQueue.getLength()+" 条");
			while(!errorQueue.isEmpty()){
				model = errorQueue.deQueue();
				boolean bl = orderService.isOrderItemByNo(model.getOrderNo());
				if(!bl){
					orderService.saveOrderItem(model);
				}
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("从队列中保存平安订单Item失败 ： " +e.getMessage());
		}
	}
	
	private void dealOrderQueue(DataQueue<OrderModel> errorQueue){
		OrderModel model = null;
		try {
			Thread.sleep(1000);
			logger.info("休息5秒后开始保存订单异常数据...........共 "+errorQueue.getLength()+" 条");
			while(!errorQueue.isEmpty()){
				model = errorQueue.deQueue();
				boolean bl = orderService.isOrderByNo(model.getOrderNo());
				if(!bl){
					orderService.saveOrder(model);
				}
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			OrderLogModel orderLog = new OrderLogModel();
			orderLog.setOrderNo(model.getOrderNo());
			orderLog.setAddTime(sdf.format(new Date()));
			orderLog.setOrderStateId(12);
			orderLog.setLogContent("订单抓取异常，未入库");
			orderLog.setLogTime(orderLog.getAddTime());
			orderLogService.saveOrderLog(orderLog);
			logger.error("从队列中保存平安订单失败 ： " +e.getMessage());
		}
	}
	
	private void orderSerach(int pageIndex,String method,
			String startTime,String endTime,String orderState,
			long multiChannelOrderBatch) {
		
		try {
			System.out.println(sdf.format(new Date()) + " pa orderSerach...............");
			
			Map<String,Object> dataMap = this.pageQueryB2COrderForOpenApi(pageIndex,method,
					orderState,startTime,endTime,multiChannelOrderBatch);
			
			List<OrderModel> orderList = (List<OrderModel>) dataMap.get("orderList");
			List<OrderItemModel> orderItemList = (List<OrderItemModel>) dataMap.get("orderItemList");
			List<PaymentLogModel> payLogList = (List<PaymentLogModel>) dataMap.get("payLogList");
			
			if("PAID".equals(method)){//添加订单
				
				//订单处理
				int num = orderService.saveBatchOrder(orderList,orderErrorQueue);
				totalAmount = totalAmount+ num;
				//程序休眠5秒    并将错误信息做处理
				Thread.sleep(500);
				
				orderService.saveBatchOrderItem(orderItemList,orderitemErrorQueue);
				
				//程序休眠5秒    并将错误信息做处理
				Thread.sleep(500);
				
				payLogService.saveBatchPayLog(payLogList,payLogErrorQueue);
				
			}else{
				System.out.println("---------------已退款的订单处理");
				
			}
			
			int totalCount = new Integer(dataMap.get("totalCount").toString()).intValue();
			//总页码数
			int pageCount = getPageCount(totalCount);
			pageIndex = getNextPage(pageIndex, pageCount);
			
			if(pageIndex>0&&totalCount>0){
				System.out.println("pageIndex: "+pageIndex+" totalCount: "+totalCount+" pageCount: "+pageCount);
				this.orderSerach(pageIndex,method,startTime,endTime,orderState,
						multiChannelOrderBatch);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	/*
	 * 获得下页的页码数
	 * @params currentIndex 当前页码
	 * @params pageCount 总页数
	 * 
	 */
	public static int getNextPage(int currentIndex,int pageCount){
		currentIndex = currentIndex +1;
		if(currentIndex>pageCount){
			return 0;
		}
		return currentIndex;
	}
	
    /*
     * 总页数
     * @params totalCount 信息总条数
     * @return 总页码
     */
    public static int getPageCount(int totalCount) {
    	
        int size = totalCount/pageSize;//总条数/每页显示的条数=总页数
        int mod = totalCount % pageSize;//最后一页的条数
        if(mod != 0)
            size++;
        return totalCount == 0 ? 1 : size;
        
    } 
	
	
	/*
	 * 订单查询接口
	 */
	public Map<String,Object> pageQueryB2COrderForOpenApi(int pageIndex,String method,
			String orderState,String startTime,String endTime,long multiChannelOrderBatch) throws Exception{
		
		Map<String,Object> tempMap = null;
		
		RequestEncoder encoder = new RequestEncoder(ThirdConstant.partnerId_pa, ThirdConstant.key_pa, apiId);
		
		//string 类型参数
		String arg1 = ThirdConstant.sellerId_pa;//卖家id
		String arg2 = startTime;//查询开始时间（支付时间）
		String arg3 = endTime;//查询结束时间（支付时间）
		/*
		 * 订单状态 
		 * 1：已支付未发货订单：PAID
		 * 2：已经退款的订单:REFUND
		 */
		String arg4 = method;
		
		/*
		 * 订单状态
		 * 0：全部订单
		 * 1：普通订单
		 * 2：未审核处方药订单
		 * 3：已审核通过处方药订单
		 * 4：已审核未通过处方药订单
		 */
		String arg5 = orderState;
		String arg6 = new Integer(pageIndex).toString();//第几页
		String arg7 = new Integer(pageSize).toString();//每页的最大数，默认40，最多不大于100
		
		//按接口定义的参数顺序放入参数
		encoder.addParameter(arg1);
		encoder.addParameter(arg2);
		encoder.addParameter(arg3);
		encoder.addParameter(arg4);
		encoder.addParameter(arg5);
		encoder.addParameter(arg6);
		encoder.addParameter(arg7);
		
		//进行加密
		RequestEntity e = encoder.encode();

		//拼装url
		String url = ThirdConstant.baseUrl_pa + ThirdConstant.apiGroup_pa +"/"+ apiName +"?";
		String postURL = url + e.getQueryParams();
//		System.out.println("url: "+postURL);
		String postData = e.getFormParams();

		//post "application/x-www-form-urlencoded" http请求 发起请求代码示例见下方
		String text = HttpClientUtils.do_post(postURL, postData);
		
		// 解析返回值(此处使用fastjson 1.2.4解析json字符串，也可使用其他json解析类库)
		Map obj = JSONObject.fromObject(text);
		String objectStr = obj.get("object").toString();
		Integer code =  (Integer)obj.get("code");
		//调用成功
		if(code==0){
		    //解码返回结果
			ResponseDecoder decoder =new ResponseDecoder(ThirdConstant.key_pa);
			decoder.decode(objectStr);
			String resultData = decoder.getData();
			JSONObject object = new JSONObject().fromObject(resultData);
			int resultCode = object.getInt("resultCode");
			if(0==resultCode){//返回成功
				//总数量
				int totalCount = object.getInt("totalCount");
				tempMap = String2Order(object,multiChannelOrderBatch);
				tempMap.put("totalCount", totalCount);
			}else{
				logger.error("错误码："+resultCode + ",错误信息："+objectStr);
			}
		}else{
			logger.error("错误码code："+code + ",错误信息："+obj);
		}
		return tempMap;
	}
	
	private Map<String,Object> String2Order(JSONObject object,long multiChannelOrderBatch){
		
		Map<String,Object> tempMap = new HashMap<String, Object>();
		try {
			List<OrderModel> tempList = new ArrayList<OrderModel>();
			OrderModel orderModer = null;
			
			List<OrderItemModel> itemList = new ArrayList<OrderItemModel>();
			List<PaymentLogModel> payLogList = new ArrayList<PaymentLogModel>();
			JSONArray modelArray = object.getJSONArray("model");
			
			if(modelArray!=null && modelArray.size()>0){
				JSONObject orderObj = null;
				JSONObject bizOrder = null;
				JSONArray items = null;
				
				String nowDate = sdf.format(new Date());
				OrderItemModel detailModel = null;
				
				for(int i=0;i<modelArray.size();i++){
					
					orderModer = new OrderModel();
					
					//--------------订单信息------------------
					orderObj = modelArray.getJSONObject(i);

					bizOrder = orderObj.getJSONObject("bizOrder");
					//订单号
					String bizOrderId = bizOrder.getString("bizOrderId");
					
					orderModer.setMultiChannelOrderNo(bizOrderId);//没有PA标志
					orderModer.setMultiChannelId(4);//来源渠道ID
					bizOrderId = "PA"+bizOrderId;
					orderModer.setOrderNo(bizOrderId);//oderNo
					orderModer.setMultiChannelOrderBatchId(multiChannelOrderBatch);
					orderModer.setValid(Constant.YES);
					//默认申通快递
					orderModer.setLogLogisticCompanyId(58);
					orderModer.setOrderType(0);
					orderModer.setOrderStatus(1);
					//渠道号 TODO  有一个对应的渠道表
					orderModer.setFromMedia(bizOrder.getString("channel"));
					//商品信息
					items = bizOrder.getJSONArray("items");
					JSONObject itemsTmp=null;
					for(int j=0;j<items.size();j++){
						detailModel = new OrderItemModel();
						itemsTmp = items.getJSONObject(j);
						detailModel.setOrderNo(bizOrderId);
						//合作方商品ID
						String referId = itemsTmp.getString("referId");
						if(!StringUtil.isBlank(referId)){
							if(referId.startsWith("TZB")){
								detailModel.setGoodsNo(referId);
								detailModel.setGoodsListType(Constant.GOODS_TYPE_GROUP);
							}else if(StringUtil.isNum(referId)){
								detailModel.setGoodsNo_69(referId);
								detailModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
							}else if(referId.startsWith("TM")&&referId.length()>15){
								detailModel.setGoodsNo_69(referId);
								detailModel.setGoodsListType(Constant.GOODS_TYPE_GROUP);
							}else{
								detailModel.setGoodsNo(referId);
								detailModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
							}
						}
						//是否处方药（1有处方药，0没有）
						int isPrescribed = itemsTmp.getInt("isPrescribed");
						if(1==isPrescribed){
							//商品类型
							detailModel.setGoodsType(Constant.GOODS_TYPE_RX);
							orderModer.setHaveCfy(Constant.YES);
						}else{
							detailModel.setGoodsType(Constant.GOODS_TYPE_OTHER);
							orderModer.setHaveCfy(Constant.NO);
						}
						
						//合作方商品编码（如果没有则为空）
						/*String referCode = itemsTmp.getString("referCode");
						if(StringUtil.isBlank(referCode)){
							detailModel.setGoodsNo_69(itemsTmp.getString("referId"));
						}else{
							detailModel.setGoodsNo_69(referCode);
						}*/
						
						//商品名
						detailModel.setName(itemsTmp.getString("itemName"));
						//实际价格，销售单价，单位分
						BigDecimal actualPrice = new BigDecimal(itemsTmp.getLong("actualPrice"));
						detailModel.setOldPrice(MathUtil.changeF2Y(actualPrice));
						//购买数量
						detailModel.setAmount(itemsTmp.getLong("buyAmount"));
						//结算价格 (作废)
//						Long settlementPrice = itemsTmp.getLong("settlementPrice");
//						detailModel.setPrice(MathUtil.changeF2Y(new BigDecimal(settlementPrice)));
						//抵扣金额 TODO  实际价格-结算价格
//						detailModel.setPriceDis(deductFee);
						//目前保存商品的结算架构
//						detailModel.setGoodsSumFee(MathUtil.changeF2Y(new BigDecimal(settlementPrice)));
						//支付时间
						detailModel.setAddTime(nowDate);
						detailModel.setGroupStatus(null);
						detailModel.setGoodsStatus(OrderState.STATE_1.getCode());
						//商品skuId
//						itemsTmp.getLong("itemSkuId");
						detailModel.setPriceDis(new BigDecimal(0));
						itemList.add(detailModel);
					}
					//------------购买者相关 信息---------------
					JSONObject buyerInfo = orderObj.getJSONObject("buyerInfo");
					String buyerNick = buyerInfo.getString("buyerNick");
					if(!StringUtil.isBlank(buyerNick)){
						orderModer.setMemberNick(buyerNick);
					}
					
					//----------------支付相关信息------------
					JSONObject payOrder = orderObj.getJSONObject("payOrder");
					//支付状态
					long payTime = bizOrder.getLong("payTime");
					if(payTime>0){
						orderModer.setIsPay(Constant.YES);
					}else{
						orderModer.setIsPay(Constant.NO);
					}
					//原始运费
					BigDecimal originalPostFee = new BigDecimal(payOrder.getLong("originalPostFee"));
					orderModer.setDeliveryFeeOld(MathUtil.changeF2Y(originalPostFee));
					//实际运费
					BigDecimal actualPostFee = new BigDecimal(payOrder.getLong("actualPostFee"));
					orderModer.setDeliveryFee(MathUtil.changeF2Y(actualPostFee));
					//实际商品销售总价
					BigDecimal actualProductFee = new BigDecimal(payOrder.getLong("actualProductFee"));
					orderModer.setSkuFee(MathUtil.changeF2Y(actualProductFee));
					//用户实际支付的现金
					BigDecimal actualTotalFee = new BigDecimal(payOrder.getLong("actualTotalFee"));
					orderModer.setPaidFee(MathUtil.changeF2Y(actualTotalFee));
					orderModer.setOrderFee(MathUtil.changeF2Y(actualTotalFee));
					//优惠价格=商品总价+原始邮费-用户实际支付的现金
					BigDecimal discountFee = MathUtil.sub(MathUtil.add(actualProductFee, actualPostFee), actualTotalFee);
					orderModer.setActivityDiscountFee(MathUtil.changeF2Y(discountFee));
					int r = actualTotalFee.compareTo(BigDecimal.ZERO); //和0，Zero比较
					if(r==1){//大于0
						orderModer.setPaymentType(Constant.PAYMENT_TYPE_KDFH);
					}else{
						orderModer.setPaymentType(Constant.PAYMENT_TYPE_HDFK);
					}
					//待支付金额
					orderModer.setOrderPayFee(MathUtil.changeF2Y(
							BussinessUtil.orderPayFee(actualProductFee, originalPostFee, 
									actualTotalFee, discountFee)));
					
					JSONArray payItems = payOrder.getJSONArray("payItems");
					if(payItems!=null && payItems.size()>0){
						JSONObject payItemsObj = null;
						PaymentLogModel logModel = null;
						int method = 0;
						for(int p=0;p<payItems.size();p++){
							payItemsObj = payItems.getJSONObject(p);
							logModel = new PaymentLogModel();
							logModel.setBusinessType("ADD");//业务类型
							logModel.setBusinessId(bizOrderId);
							method = payItemsObj.getInt("method");
							this.setPayMethod(method,logModel);
							//支付金额（如果为现金，单位为分）
							logModel.setPaidFee(MathUtil.changeF2Y(new BigDecimal(payItemsObj.getString("amount"))));
							//utc时间格式转换必须*1000
							logModel.setPaymentTime(sdf.format(new Date(payTime*1000)));
							orderModer.setPlatformCreateTime(logModel.getPaymentTime());
							orderModer.setCommitTime(logModel.getPaymentTime());
							logModel.setAddTime(sdf.format(new Date()));
							//skuid
							payItemsObj.getString("itemSkuId");
							//促销活动 或 优惠券信息
							if(method==8){
								orderModer.setOrderVouchers(logModel.getPaidFee());
								orderModer.setCouponNo(payItemsObj.getString("promotionRefCode"));
							}
							
							payLogList.add(logModel);
						}
					}
					//-------物流相关---------------
					JSONObject lgOrder = orderObj.getJSONObject("lgOrder");
					orderModer.setLongitude(lgOrder.getString("longitude"));
					orderModer.setLatitude(lgOrder.getString("latitude"));
					
					//收货人
					orderModer.setReceiveUser(lgOrder.getString("fullName"));
					//获取省市区
					String prov = lgOrder.getString("prov");
					String city = lgOrder.getString("city");
					String area = lgOrder.getString("area");
					orderModer.setProvinceId(areaService.findArea(0,prov));
					orderModer.setCityId(areaService.findArea(orderModer.getProvinceId(),city));
					int countyId = 0;
					if(!StringUtil.isBlank(area)&&area.contains("市辖区")){
						countyId = areaService.findArea(orderModer.getCityId(), "市辖区");
					}else{
						countyId = areaService.findArea(orderModer.getCityId(),area);
					}
					orderModer.setAreaId(countyId);
					orderModer.setProvinceName(prov);
					orderModer.setCityName(city);
					orderModer.setAreaName(area);
					//收货地址
					orderModer.setReceiveAddress(prov+city+area);
					//完整收货地址
					orderModer.setReceiveFullAddress(orderModer.getReceiveAddress()+lgOrder.getString("address"));
					//收货手机
					orderModer.setReceiveMobile(lgOrder.getString("mobilePhone"));
					
					//发票相关信息
					JSONObject invoice = orderObj.getJSONObject("invoice");
					//发票抬头
					String invoiceTitle = invoice.getString("invoiceTitle");
					if(!StringUtil.isBlank(invoiceTitle)){
						orderModer.setInvoiceTitle(invoiceTitle);
					}
					//发票内容
					//发票类型（1普通发票，2增值税发票，0没有发票）
					int invoiceTypeId = invoice.getInt("invoiceTypeId");
					if(1==invoiceTypeId){
						orderModer.setInvoiceContent("普通发票");
					}else if(2==invoiceTypeId){
						orderModer.setInvoiceContent("增值税发票");
					}
					orderModer.setOrderReturnFee(new BigDecimal(0));
					orderModer.setOrderRealFee(new BigDecimal(0));
					orderModer.setAddTime(nowDate);
					tempList.add(orderModer);
					
				}
				tempMap.put("orderList", tempList);
				tempMap.put("orderItemList", itemList);
				tempMap.put("payLogList", payLogList);
			}
			
		} catch (Exception e) {
			logger.error("组装数据失败，"+e.getMessage(),e);
		}
		return tempMap;
	}
	
	private void setPayMethod(int method,PaymentLogModel logModel){
		/*
		 * 支付方式
		 *  -1：虚拟支付，后结算模式
		 *  1：现金  2：积分  3免费
		 *  4抵扣券  5 现金，担保模式    6 健康金
		 *  7抵扣券    8 促销抵扣
		 */
		if(-1==method){
			logModel.setOrderPaymentId(PayCode.KDFH_ALI);
			logModel.setOrderPaymentName("虚拟支付，后结算模式");
		}else if(1==method){
			logModel.setOrderPaymentId(PayCode.XJ_ALI);
			logModel.setOrderPaymentName("现金");
		}else if(2==method){
			logModel.setOrderPaymentId(PayCode.JF_ALI);
			logModel.setOrderPaymentName("积分");
		}else if(3==method){
			logModel.setOrderPaymentId(PayCode.MF_ALI);
			logModel.setOrderPaymentName("免费");
		}else if(4==method||7==method){
			logModel.setOrderPaymentId(PayCode.DKQ_ALI);
			logModel.setOrderPaymentName("抵扣券");
		}else if(5==method){
			logModel.setOrderPaymentId(PayCode.XJDB_ALI);
			logModel.setOrderPaymentName("现金，担保模式");
		}else if(6==method){
			logModel.setOrderPaymentId(PayCode.JKJ_ALI);
			logModel.setOrderPaymentName("健康金");
		}else if(8==method){
			logModel.setOrderPaymentId(PayCode.CXDK_ALI);
			logModel.setOrderPaymentName("促销抵扣");
		}
	}
	
}
