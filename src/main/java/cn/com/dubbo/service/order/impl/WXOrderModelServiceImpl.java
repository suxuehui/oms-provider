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
import cn.com.dubbo.constant.PayCode;
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
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.service.order.OrderSupplementService;
import cn.com.dubbo.service.order.WXOrderModelService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.ClassInfo;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.HttpClientUtils;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;

/**
 * @author Administrator
 */
@Service("WXOrderModelService")
public class WXOrderModelServiceImpl implements WXOrderModelService{
 
	//	
	public final static String post_url = "http://localhost:8080/oms-weixin/pushorder/queryOrders.do";
	
	public final static int pageSize = 90;//一页显示的条目数量
	
	public final static String orderStatus = "PAID";//一页显示的条目数量

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private PaymentLogService payLogService;
	
	@Resource
	private OrderSupplementService supplementService;
	
	@Resource
	private OrderStepService orderStepService;
	
	@Resource
	private ChannelMapper channelMapper;
	
	@Resource
	private OrderLogService orderLogService;
	
	private DataQueue<OrderModel> orderErrorQueue = new DataQueue<OrderModel>();
	
	private DataQueue<OrderItemModel> orderitemErrorQueue = new DataQueue<OrderItemModel>();
	
	private DataQueue<PaymentLogModel> payLogErrorQueue = new DataQueue<PaymentLogModel>();
	
	private static final Logger logger = Logger.getLogger(WXOrderModelServiceImpl.class);
	
	private int totalAmount = 0;//导入的订单总数量
	
	public static void main(String[] args) {
		new WXOrderModelServiceImpl().dealOrderModel(null, null, false);
	}
	
	@Override
	public void dealOrderModel(String startTime,String endTime,boolean falg){
		
		try {
			//先置为 0
			totalAmount = 0;
			
			//订单状态
			String orderState = "0";
			Long multiChannelOrderBatch = null;
			
			if(falg){
				multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("6");
			}else{
				multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("66");
			}
			 
			//已支付未发货订单：PAID
			this.orderSerach(1,"PAID",startTime,endTime,orderState,multiChannelOrderBatch);
			
//			if(!orderErrorQueue.isEmpty()){
//				this.dealOrderQueue(orderErrorQueue);
//			}
//			
//			if(!orderitemErrorQueue.isEmpty()){
//				this.dealOrderItemQueue(orderitemErrorQueue);
//			}
//			
//			if(!payLogErrorQueue.isEmpty()){
//				this.dealPayLogQueue(payLogErrorQueue);
//			}
//			
//			this.saveChannelOrderBatch(multiChannelOrderBatch);
//			
//			if(falg){
//				//信息补全操作,组合订单拆分
//				Thread.sleep(500);
//				
//				supplementService.orderSupplement(ChannelConstant.CHANNEL_WX,OrderState.STATE_5.getCode());
//				
//				//订单审核
//				orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_WX,OrderState.STATE_5.getCode());
//			}
			
		} catch (Exception e) {
			logger.error("微信订单抓取错误： " +e.getMessage(),e);
		}
		
	}
	
	/**
	 * 保存批量执行信息
	 */
	@Transactional
	private void saveChannelOrderBatch(long multiChannelOrderBatch){
		
		MultiChannelOrderBatchModel channelOrderModel = new MultiChannelOrderBatchModel();
		channelOrderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
		channelOrderModel.setMultiChannelId(6);//微信的渠道为6
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
	
	private void orderSerach(int pageIndex,String method,String startTime,String endTime,String orderState,long multiChannelOrderBatch) {
		
		try {
			System.out.println("wx orderSerach...............");
			
			Map<String,Object> dataMap = this.pageQueryB2COrderForOpenApi(pageIndex,method,orderState,startTime,endTime,multiChannelOrderBatch);
			System.out.println(0);
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
				this.orderSerach(pageIndex,method,startTime,endTime,orderState,multiChannelOrderBatch);
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
	public Map<String,Object> pageQueryB2COrderForOpenApi(int pageIndex,String method,String orderState,String startTime,String endTime,long multiChannelOrderBatch) throws Exception{
		
		Map<String,Object> tempMap = null;
		try {
			
			//post  请求 发起请求代码示例见下方
			String text = HttpClientUtils.sendPost(post_url, "pageNO="+1+"&pageSize="+pageSize+"&orderStatus="+orderStatus);
			System.out.println(text);
			// 解析返回值(此处使用fastjson 1.2.4解析json字符串，也可使用其他json解析类库)
			JSONObject  obj = JSONObject.fromObject(text);
//			String objectStr = obj.get("object").toString();
			Object result =  obj.get("result");
			//调用成功
			if(null != result && !"".equals(result) && "success".equals(result)){
			    //解码返回结果
//					int totalCount = object.getInt("totalCount");
					tempMap = String2Order( obj,multiChannelOrderBatch);
//					tempMap.put("totalCount", totalCount);
					System.out.println(tempMap);
				 
			}else{
				logger.error("wx调用错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			JSONArray modelArray = object.getJSONArray("orders");
			
			if(modelArray!=null && modelArray.size()>0){
				JSONObject orderObj = null;
//				JSONObject bizOrder = null;
				JSONArray items = null;
				
				String nowDate = sdf.format(new Date());
				OrderItemModel detailModel = null;
				
				for(int i=0;i<modelArray.size();i++){
					
					orderModer = new OrderModel();
					
					//--------------订单信息------------------
					orderObj = modelArray.getJSONObject(i);

//					bizOrder = orderObj.getJSONObject("bizOrder");
					//订单号
					String orderNo = orderObj.getString("orderNo");
					
					orderModer.setMultiChannelOrderNo(orderNo);//没有PA标志
					orderModer.setMultiChannelId(6);//来源渠道ID
					orderNo = "WX"+orderNo;
					orderModer.setOrderNo(orderNo);//oderNo
					orderModer.setMultiChannelOrderBatchId(multiChannelOrderBatch);
					orderModer.setValid(Constant.YES);
					//默认申通快递
					orderModer.setLogLogisticCompanyId(58);
					orderModer.setOrderType(0);
					orderModer.setOrderStatus(1);
					//渠道号    有一个对应的渠道表
//					orderModer.setFromMedia(orderObj.getString("fromMedia"));
					orderModer.setFromMedia("WX");
					//商品信息
					items = orderObj.getJSONArray("orderItems");
					JSONObject itemsTmp=null;
					for(int j=0;j<items.size();j++){
							detailModel = new OrderItemModel();
							itemsTmp = items.getJSONObject(j);
							detailModel.setOrderNo(orderNo);
							//合作方商品ID
							detailModel.setGoodsNo_69(itemsTmp.getString("goodsNo69"));
							//是否处方药（1有处方药，0没有）
							Integer isPrescribed = itemsTmp.getString("goodsType") == null || "null".equals(itemsTmp.getString("goodsType"))?0:Integer.valueOf(itemsTmp.getString("goodsType"));
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
							
							//明细类型
							if(!StringUtil.isBlank(detailModel.getGoodsNo_69())&&detailModel.getGoodsNo_69().contains("TM")){
								detailModel.setGoodsListType(Constant.GOODS_TYPE_GROUP);
							}else{
								detailModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
							}
							//商品名
							detailModel.setName(itemsTmp.getString("name"));
							//实际价格，销售单价，单位分
							BigDecimal oldPrice = new BigDecimal(itemsTmp.getLong("oldPrice"));
							detailModel.setOldPrice(MathUtil.changeF2Y(oldPrice));
							//购买数量
							detailModel.setAmount(itemsTmp.getLong("amount"));
							//结算价格 (作废)
	//						Long settlementPrice = itemsTmp.getLong("settlementPrice");
	//						detailModel.setPrice(MathUtil.changeF2Y(new BigDecimal(settlementPrice)));
							//抵扣金额    实际价格-结算价格
	//						detailModel.setPriceDis(deductFee);
							//目前保存商品的结算架构
	//						detailModel.setGoodsSumFee(MathUtil.changeF2Y(new BigDecimal(settlementPrice)));
							//支付时间
							detailModel.setAddTime(nowDate);
							detailModel.setGroupStatus(itemsTmp.getString("groupStatus"));
							//商品skuId
	//						itemsTmp.getLong("itemSkuId");
							detailModel.setPriceDis(new BigDecimal(0));
							itemList.add(detailModel);
					}
					//------------购买者相关 信息---------------
//					JSONObject buyerInfo = orderObj.getJSONObject("buyerInfo");
					String memberNick = orderObj.getString("memberNick");
					if(!StringUtil.isBlank(memberNick)){
						orderModer.setMemberNick(memberNick);
					}
					
					//----------------支付相关信息------------
//					JSONObject payOrder = orderObj.getJSONObject("orders"); 
					//支付状态 
					Long payTime = orderObj.getString("platformCreateTime") == null || "null".equals(orderObj.getString("platformCreateTime"))? 0 : orderObj.getLong("platformCreateTime");
					if(payTime>0){
						orderModer.setIsPay(Constant.YES);
					}else{
						orderModer.setIsPay(Constant.NO);
					}
					Object deliveryFeeOldTmp = orderObj.getString("deliveryFeeOld");
					if(null == deliveryFeeOldTmp || "".equals(deliveryFeeOldTmp.toString().trim()) || "null".equals(deliveryFeeOldTmp.toString().trim())){
						deliveryFeeOldTmp = "0";
					}
					Object deliveryFeeTmp = orderObj.getString("deliveryFee");
					if(null == deliveryFeeTmp || "".equals(deliveryFeeTmp.toString().trim()) || "null".equals(deliveryFeeTmp.toString().trim())){
						deliveryFeeTmp = "0";
					}
					Object skuFeeTmp = orderObj.getString("skuFee");
					if(null == skuFeeTmp || "".equals(skuFeeTmp.toString().trim()) || "null".equals(skuFeeTmp.toString().trim())){
						skuFeeTmp = "0";
					}
					Object paidFeeTmp = orderObj.getString("paidFee");
					if(null == paidFeeTmp || "".equals(paidFeeTmp.toString().trim()) || "null".equals(paidFeeTmp.toString().trim())){
						paidFeeTmp = "0";
					}
					Object orderPayFeeTmp = orderObj.getString("orderPayFee");
					if(null == orderPayFeeTmp || "".equals(orderPayFeeTmp.toString().trim()) || "null".equals(orderPayFeeTmp.toString().trim())){
						orderPayFeeTmp = "0";
					}
					
					//原始运费
					BigDecimal deliveryFeeOld = new BigDecimal(Float.valueOf(deliveryFeeOldTmp.toString()));
					orderModer.setDeliveryFeeOld(MathUtil.changeF2Y(deliveryFeeOld));
					//实际运费
					BigDecimal deliveryFee = new BigDecimal(Float.valueOf(deliveryFeeTmp.toString()));
					orderModer.setDeliveryFee(MathUtil.changeF2Y(deliveryFee));
					//实际商品销售总价
					BigDecimal skuFee = new BigDecimal(Float.valueOf(skuFeeTmp.toString()));
					orderModer.setSkuFee(MathUtil.changeF2Y(skuFee));
					//用户实际支付的现金
					BigDecimal paidFee = new BigDecimal(Float.valueOf(paidFeeTmp.toString()));
					orderModer.setPaidFee(MathUtil.changeF2Y(paidFee));
					BigDecimal orderPayFee = new BigDecimal(Float.valueOf(orderPayFeeTmp.toString()));
					orderModer.setOrderFee(MathUtil.changeF2Y(orderPayFee));
					//优惠价格=商品总价+原始邮费-用户实际支付的现金
					BigDecimal discountFee = MathUtil.sub(MathUtil.add(skuFee, deliveryFeeOld), paidFee);
					orderModer.setActivityDiscountFee(MathUtil.changeF2Y(discountFee));
					int r = paidFee.compareTo(BigDecimal.ZERO); //和0，Zero比较
					if(r==1){//大于0
						orderModer.setPaymentType(Constant.PAYMENT_TYPE_KDFH);
					}else{
						orderModer.setPaymentType(Constant.PAYMENT_TYPE_HDFK);
					}
					//待支付金额
					orderModer.setOrderPayFee(MathUtil.changeF2Y(
							BussinessUtil.orderPayFee(skuFee, deliveryFeeOld, paidFee, discountFee)));
					
					JSONArray payItems = orderObj.getJSONArray("orderItems");
					if(payItems!=null && payItems.size()>0){
						JSONObject payItemsObj = null;
						PaymentLogModel logModel = null;
//						int method = 0;
						for(int p=0;p<payItems.size();p++){
							payItemsObj = payItems.getJSONObject(p);
							logModel = new PaymentLogModel();
							logModel.setBusinessType("ADD");//业务类型
							logModel.setBusinessId(orderNo);
//							method = payItemsObj.getInt("method");
							//当前算的 是现金支付  method = 1
							this.setPayMethod(1,logModel);
							//支付金额（如果为现金，单位为分）
							logModel.setPaidFee(MathUtil.changeF2Y(new BigDecimal(payItemsObj.getString("price"))));
							//utc时间格式转换必须*1000
							logModel.setPaymentTime(sdf.format(new Date(payTime*1000)));
							orderModer.setPlatformCreateTime(logModel.getPaymentTime());
							orderModer.setCommitTime(logModel.getPaymentTime());
							logModel.setAddTime(sdf.format(new Date()));
							//skuid
//							payItemsObj.getString("itemSkuId");
							//促销活动 或 优惠券信息
//							if(method==8){
//								orderModer.setOrderVouchers(logModel.getPaidFee());
//								orderModer.setCouponNo(payItemsObj.getString("promotionRefCode"));
//							}
							
							payLogList.add(logModel);
						}
					}
					//-------物流相关---------------
//					JSONObject lgOrder = orderObj.getJSONObject("lgOrder");
					orderModer.setLongitude(orderObj.getString("receiveLongitude"));
					orderModer.setLatitude(orderObj.getString("receiveLatitude"));
					
					//收货人
					orderModer.setReceiveUser(orderObj.getString("receiveUser"));
					//获取省市区
//					String prov = lgOrder.getString("prov");
//					String city = lgOrder.getString("city");
//					String area = lgOrder.getString("area");
					String provinceId = orderObj.getString("provinceId");
					if(null == provinceId || "".equals(provinceId) || "null".equals(provinceId)){
						
					}else{
						orderModer.setProvinceId(Integer.valueOf(provinceId));
					}
					String cityId = orderObj.getString("cityId");
					if(null == cityId || "".equals(cityId) || "null".equals(cityId)){
						
					}else{
						orderModer.setCityId(Integer.valueOf(cityId));
					}
					
					String areaId = orderObj.getString("areaId");
					if(null == areaId || "".equals(areaId) || "null".equals(areaId)){
						
					}else{
						orderModer.setAreaId(Integer.valueOf(areaId));
					}
//					orderModer.setAreaId(orderObj.getString("areaId") == null || "null".equals(orderObj.getString("areaId") )? null:orderObj.getInt("areaId"));
					
//					orderModer.setCityId(orderObj.getString("cityId") == null  || "null".equals(orderObj.getString("cityId"))?null:orderObj.getInt("cityId"));
//					int countyId = 0;
//					if(!StringUtil.isBlank(area)&&area.contains("市辖区")){
//						countyId = areaService.findArea(orderModer.getCityId(), "市辖区");
//					}else{
//						countyId = areaService.findArea(orderModer.getCityId(),area);
//					}
				
					orderModer.setProvinceName(orderObj.getString("provinceName"));
					orderModer.setCityName(orderObj.getString("cityName"));
					orderModer.setAreaName(orderObj.getString("areaName"));
					//收货地址
					orderModer.setReceiveAddress(orderObj.getString("receiveAddress"));
					//完整收货地址
					orderModer.setReceiveFullAddress(orderObj.getString("receiveFullAddress"));
					//收货手机
					orderModer.setReceiveMobile(orderObj.getString("receiveMobile"));
					
					//发票相关信息
//					JSONObject invoice = orderObj.getJSONObject("invoice");
					//发票抬头
//					String invoiceTitle = invoice.getString("invoiceTitle");
//					if(!StringUtil.isBlank(invoiceTitle)){invoiceTitle
				   orderModer.setInvoiceTitle(orderObj.getString("invoiceTitle"));
//					}
					//发票内容
					//发票类型（1普通发票，2增值税发票，0没有发票）
//					int invoiceTypeId = orderObj.getString("invoiceContent");
//					if(1==invoiceTypeId){
					orderModer.setInvoiceContent(orderObj.getString("invoiceContent"));
//					}else if(2==invoiceTypeId){
//						orderModer.setInvoiceContent("增值税发票");
//					}
					orderModer.setOrderReturnFee(new BigDecimal(orderObj.getInt("orderReturnFee")));
					orderModer.setOrderRealFee(new BigDecimal(orderObj.getInt("orderRealFee")));
					orderModer.setAddTime(orderObj.getString("addTime"));
					tempList.add(orderModer);
					
				}
				tempMap.put("orderList", tempList);
				tempMap.put("orderItemList", itemList);
				tempMap.put("payLogList", payLogList);
			}
			
		} catch (Exception e) {
			System.out.println("组装数据失败");
			e.printStackTrace();
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
	
	private Object isEmptyNumber(Object obj){
		if(null == obj || "".equals(obj.toString().trim()) || "null".equals(obj.toString().trim())){
			return 0;
		}
		return obj;
	}
}
