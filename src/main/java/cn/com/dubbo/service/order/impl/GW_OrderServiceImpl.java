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
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.OrderLogService;
import cn.com.dubbo.service.inter.PaymentLogService;
import cn.com.dubbo.service.order.GW_OrderService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.ClassInfo;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.HttpClientUtils;
import cn.com.dubbo.util.MathUtil;


/**
 * 从官网的订单保存到oms
 * @author hhr
 */
@Service("gwOrderService")
public class GW_OrderServiceImpl implements GW_OrderService{	
	
	@Autowired
	private OrderService orderService;
	
	@Resource
	private OrderLogService orderLogService;
	
	@Resource
	private PaymentLogService payLogService;
	
	@Resource
	private GoodsMapper goodsMapper;
	
	@Resource
	private ChannelMapper channelMapper;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public int pageSize = 40;//static
	
	private static final Logger logger = Logger.getLogger(GW_OrderServiceImpl.class);
	//返回格式
	public static final String format = "json";
	
	private DataQueue<OrderModel> orderErrorQueue = new DataQueue<OrderModel>();
	
	private DataQueue<OrderItemModel> orderitemErrorQueue = new DataQueue<OrderItemModel>();
	
	private DataQueue<PaymentLogModel> payLogErrorQueue = new DataQueue<PaymentLogModel>();
	
	private Integer  totalAmount = 0;
	
	
	//有效保存方法
	@Override
	public String  getGwOrderTrades(String[] str,Long pageIndex,String orderStatus){
		Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("7");
		try {
			if(null == str || str.length == 0){
				return "时间参数必须指定";
			}
			String orderResponse=this.getGwOrder(str[0], str[1], pageIndex, orderStatus);
			if(null == orderResponse){
				return "没有找到相应的商品";
			}
			System.out.println("***"+orderResponse);
			JSONObject obj = JSONObject.fromObject(orderResponse);
			if(!"".equals(obj.getString("msg")) ){
				return obj.getString("msg");
			}
			String totalResults_Str = obj.getString("OrderInfoDetail");
			JSONArray orderList  = JSONArray.fromObject(totalResults_Str);
			if(null != orderList && orderList.size() == 0){
				return "没有订单";
			}
			//执行组装数据
			Map<String,Object> map =  this.createOrderModel(orderList,multiChannelOrderBatch);
			//具体的保存方法
			this.saveOrderEtc(map);
			//
			if(!orderErrorQueue.isEmpty()){
				this.dealOrderQueue(orderErrorQueue);
			}
			
			if(!orderitemErrorQueue.isEmpty()){
				this.dealOrderItemQueue(orderitemErrorQueue);
			}
			
			if(!payLogErrorQueue.isEmpty()){
				this.dealPayLogQueue(payLogErrorQueue);
			}
			//保存批量信息
			if(orderList.size() > 0){
				this.saveChannelOrderBatch(multiChannelOrderBatch);
			}
		}catch(Exception e1 ){
			logger.error("getGwOrderTrades 执行 失败：",e1);
			e1.printStackTrace();
		}
		return "N";
	}
	
	/**
	 * 获取信息
	 * @author hhr
	 * @return
	 */
	private String getGwOrder(String beiginTime,String endTime,Long pageIndex,String orderStatus ){
		try {
			String pamaras = "beiginTime="+beiginTime+"&endTime="+endTime+"" +"&pageIndex="+pageIndex+"&orderStatus="+orderStatus;
			String text = HttpClientUtils.do_post(BaseData.GW_ORDER, pamaras);
			return text;
		} catch (Exception e) {
			logger.error("获取官网订单 报错...",e);
		}
		return null;
	}
	

	
	//执行具体的保存方法
	private Map<String,Object> createOrderModel(JSONArray orderList,Long multiChannelOrderBatch) {
		
		List<OrderModel> orderModelList = new ArrayList<OrderModel>();
		
		List<PaymentLogModel> paymentLogList = new ArrayList<PaymentLogModel>();
		
		List<OrderItemModel> itemModelList =  new ArrayList<OrderItemModel>();
		
		totalAmount = 0;
		
		if(null != orderList && orderList.size() > 0){
				for (int i = 0; i < orderList.size(); i++) {
					totalAmount++;
					//实例化对象
					OrderModel orderModel = new OrderModel();
					JSONObject obj = JSONObject.fromObject(orderList.get(i)); 
		//获取数据*******************************************************************
					String orderNo = obj.getString("orderNo"); 
					String logLogisticCompanyNo =  obj.getString("logLogisticCompanyNo");
		//			String goodsSeller =  obj.getString("goodsSeller");
		//			String fromMedia =  obj.getString("fromMedia");
		//			String orderType =  obj.getString("orderType");
					String orderStatus =  obj.getString("orderStatus");
					String valid =  obj.getString("valid");
		//			String memberId =  obj.getString("memberId");
					String isPay =  obj.getString("isPay");
					String paymentType =  obj.getString("paymentType");
		//			String skuWeights =  obj.getString("skuWeights");
					String deliveryFeeOld =  obj.getString("deliveryFeeOld");
					String deliveryFee =  obj.getString("deliveryFee");
					String skuFee =  obj.getString("skuFee");
					String orderPoints =  obj.getString("orderPoints");
					String orderVouchers =  obj.getString("orderVouchers");
		//			String couponType =  obj.getString("couponType");
					String couponNo =  obj.getString("couponNo");
					String activityDiscountFee =  obj.getString("activityDiscountFee");
					String otherDiscountFee =  obj.getString("otherDiscountFee");
					String orderFee =  obj.getString("orderFee");
					String paidFee =  obj.getString("paidFee");
					String orderPayFee =  obj.getString("orderPayFee");
					String orderReturnFee =  obj.getString("orderReturnFee");
					String orderRealFee =  obj.getString("orderRealFee");
		//			String memberAddressId =  obj.getString("memberAddressId");
					String receiveUser =  obj.getString("receiveUser");
					String receiveTime =  obj.getString("receiveTime");
		//			String provinceId =  obj.getString("provinceId");
					String provinceName =  obj.getString("provinceName");
					String cityName =  obj.getString("cityName");
					String areaName =  obj.getString("areaName");
					String receiveAddress =  obj.getString("receiveAddress");
					String receiveFullAddress =  obj.getString("receiveFullAddress");
		//			String receivePost =  obj.getString("receivePost");
					String receiveTel =  obj.getString("receiveTel");
					String receiveMobile =  obj.getString("receiveMobile");
					String invoiceTitle =  obj.getString("invoiceTitle");
					String invoiceContent =  obj.getString("invoiceContent");
					String orderMsg =  obj.getString("orderMsg");
					String orderNotes =  obj.getString("orderNotes");
		//			String auditUserId =  obj.getString("auditUserId");
		//			String auditTime =  obj.getString("auditTime");
		//			String isLock =  obj.getString("isLock");
		//			String lockTime =  obj.getString("lockTime");
		//			String lockUserId =  obj.getString("lockUserId");
					String commitTime =  obj.getString("commitTime");
					String finishTime =  obj.getString("finishTime");
		//			String cancelTime =  obj.getString("cancelTime");
		//			String cancelUserId =  obj.getString("cancelUserId");
		//			String cancelNotes =  obj.getString("cancelNotes");
					String haveCfy =  obj.getString("haveCfy");
					String isDelete =  obj.getString("isDelete");
		//			String addUserId =  obj.getString("addUserId");
		//			String addTime =  obj.getString("addTime");
		//			String editUserId =  obj.getString("editUserId");
		//			String editTime =  obj.getString("editTime");
					
		//拼接数据********************************************************************
					orderModel.setMultiChannelId(7);
					orderModel.setFromMedia("官网");
					orderModel.setOrderType(0);
					orderModel.setOrderStatus(orderStatus == null?1:Integer.valueOf(orderStatus));
					orderModel.setSendStatus("N");
					orderModel.setValid(valid);
					orderModel.setOrderNo("GW"+orderNo);
					orderModel.setMultiChannelOrderNo(orderNo);
					if(null != receiveTime && !"".equals(receiveTime)){
						orderModel.setReceiveTime(new Date(receiveTime));
					}
					if(null != logLogisticCompanyNo && !"".equals(logLogisticCompanyNo)){
						orderModel.setLogLogisticCompanyId(Integer.valueOf(logLogisticCompanyNo));
					}
					orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
					orderModel.setIsPay(isPay);
					orderModel.setPaymentType(paymentType);
					orderModel.setDeliveryFeeOld(new BigDecimal(isNotNull(deliveryFeeOld)));			
					orderModel.setDeliveryFee(new BigDecimal(isNotNull(deliveryFee)));
					orderModel.setSkuFee(new BigDecimal(isNotNull(skuFee)));			
					orderModel.setOrderPoints(new BigDecimal(isNotNull(orderPoints)));			
					orderModel.setOrderVouchers(new BigDecimal(isNotNull(orderVouchers)));			
					orderModel.setCouponNo(strIsNotNull(couponNo));			
					orderModel.setActivityDiscountFee(new BigDecimal(isNotNull(activityDiscountFee)));			
					orderModel.setPaidFee(new BigDecimal(isNotNull(paidFee)));			
					orderModel.setOrderPayFee(new BigDecimal(isNotNull(orderPayFee)));			
					orderModel.setOrderFee(new BigDecimal(isNotNull(orderFee)));		
					orderModel.setOrderReturnFee(new BigDecimal(isNotNull(orderReturnFee)));		
					orderModel.setOrderRealFee(new BigDecimal(isNotNull(orderRealFee)));		
		//			orderModel.setSupplyPrice(new BigDecimal(isNotNull()));		
					orderModel.setReceiveUser(receiveUser);	
		//			orderModel.setReceiveTime(new Date(receiveTime));
					orderModel.setProvinceName(provinceName);
					orderModel.setCityName(cityName);			
					orderModel.setAreaName(areaName);			
					orderModel.setReceiveAddress(receiveAddress);			
					orderModel.setReceiveFullAddress(receiveFullAddress);			
					orderModel.setReceiveTel(strIsNotNull(receiveTel));			
					orderModel.setReceiveMobile(strIsNotNull(receiveMobile));			
					orderModel.setInvoiceTitle(strIsNotNull(invoiceTitle));			
					orderModel.setInvoiceContent(strIsNotNull(invoiceContent));			
					orderModel.setOrderMsg(strIsNotNull(orderMsg));			
					orderModel.setOrderNotes(strIsNotNull(orderNotes));			
					orderModel.setIsDelete(isDelete);
					orderModel.setCommitTime(commitTime);
					orderModel.setPlatformCreateTime(commitTime);
					orderModel.setAddTime(sdf.format(new Date()));
					orderModel.setHaveCfy(haveCfy);
					orderModel.setFinishTime(finishTime);
		//			orderModel.set			
					
					
		//明细处理*******************************************************************
					String items = obj.getString("orderItems");
					JSONArray itemList = JSONArray.fromObject(items);
					
					for (int j = 0; j < itemList.size(); j++) {
						JSONObject item = JSONObject.fromObject(itemList.get(j)); 
						OrderItemModel itemModel = new OrderItemModel();
		//				String packageId = item.getString("packageId");
						String goodsType = item.getString("goodsType");
		//				String goodsId = item.getString("goodsId");
						String goodsNo = item.getString("goodsNo");
						String goodsName = item.getString("goodsName");
		//				String goodsUnit = item.getString("goodsUnit");
						String goodsCostPrice = item.getString("goodsCostPrice");
						String goodsOldPrice = item.getString("goodsOldPrice");
						String goodsPrice = item.getString("goodsPrice");
						String goodsAmount = item.getString("goodsAmount");
						String goodsSumFee = item.getString("goodsSumFee");
						String saleSumFee = item.getString("saleSumFee");
		//				String status = item.getString("status");
						String isDeleteItem = item.getString("isDelete");
		//				String addUserIdItem = item.getString("addUserId");
		//				String addTimeItem = item.getString("addTime");
		//				String editUserIdItem = item.getString("editUserId");
		//				String editTimeItem = item.getString("editTime");
						
						itemModel.setOrderNo("GW"+orderNo);
		//				itemModel.setGoodsListType(goodsListType);
						
						itemModel.setGoodsNo(goodsNo);
						itemModel.setGoodsListType(goodsType);
						itemModel.setName(goodsName);
						
		//				itemModel.setGoodsNo_69(goodsNo_69);
		//				itemModel.setName(name);
		//				itemModel.setStandard(standard);
		//				itemModel.setSupplyPrice(new BigDecimal(0));
						itemModel.setCostPrice(new BigDecimal(isNotNull(goodsCostPrice)));
						itemModel.setOldPrice(new BigDecimal(isNotNull(goodsOldPrice)));
						itemModel.setPrice(new BigDecimal(isNotNull(goodsPrice)));
						itemModel.setAmount(Long.valueOf(goodsAmount));
						itemModel.setPriceDis(MathUtil.sub(new BigDecimal(isNotNull(goodsSumFee)),new BigDecimal(isNotNull(saleSumFee)))); 
						itemModel.setGoodsSumFee(new BigDecimal(isNotNull(saleSumFee)));
						itemModel.setAddTime(sdf.format(new Date()));
						itemModel.setIsDelete(isDeleteItem);
//						GoodsModel newGoods = goodsMapper.getGoodByGoodNo(goodsNo);
//						if(null != newGoods){
//							itemModel.setGoodsNo_69(newGoods.getGoodsNo_69());
//							itemModel.setName(newGoods.getName());
//							itemModel.setStandard(newGoods.getStandard());
//							itemModel.setGoodsListType(newGoods.getGoodsType());
//						}
						itemModelList.add(itemModel);
					}
		//			orderModel.setItemList(itemModelList);
				
					//支付信息
					
					  if(2 > 1){
							// 付款金额处理
							PaymentLogModel logModel = new PaymentLogModel();
							logModel.setBusinessType("ADD");//业务类型
							logModel.setBusinessId("GW"+orderNo);//订单号
							//utc时间格式转换必须*1000
		//					logModel.setPaymentTime(pay_time);
							BigDecimal order_totalfee = new BigDecimal(isNotNull(paidFee));//订单总金额
 							//if(MathUtil.compare(order_totalfee, new BigDecimal(0)) > 0){
							if(!"HDFK".equals(orderModel.getPaymentType())){
	//							if("4-在线支付".equals(pay_mothed.trim())){
								logModel.setOrderPaymentId(PayCode.ZFB_ALI);
								logModel.setOrderPaymentName("支付宝");
								orderModel.setPaymentType(Constant.PAYMENT_TYPE_KDFH);// 
								logModel.setPaidFee(order_totalfee);
								logModel.setAddTime(sdf.format(new Date()));//添加时间
								paymentLogList.add(logModel);
							}
						  //优惠总额
						  Integer totlePriceYouHui = Integer.valueOf(isNotNull(orderPoints)) + Integer.valueOf(isNotNull(orderVouchers)) + Integer.valueOf(isNotNull(activityDiscountFee))
								  + Integer.valueOf(isNotNull(otherDiscountFee)) ; 
					      if(!"".equals(totlePriceYouHui) && null != totlePriceYouHui && totlePriceYouHui > 0 ){
					    	  PaymentLogModel logModel2 = new PaymentLogModel();
								logModel2.setBusinessType("ADD");//业务类型
								logModel2.setBusinessId("GW"+orderNo);//订单号 
								//utc时间格式转换必须*1000
								logModel2.setOrderPaymentId(PayCode.ZDYH_ALI);
								logModel2.setOrderPaymentName("整单优惠");
								logModel2.setAddTime(sdf.format(new Date()));//添加时间
								logModel2.setPaidFee(new BigDecimal(totlePriceYouHui));//实付金额
								paymentLogList.add(logModel2);
					      }
					      if(null != orderModel.getPaymentType() && !"".equals(orderModel.getPaymentType()) && "HDFK".equals(orderModel.getPaymentType())){ 
					    	  orderModel.setPaymentType(Constant.PAYMENT_TYPE_HDFK);
					      }
					 }	
						 orderModelList.add(orderModel); 
						
				
				} 
				Map<String,Object> tempMap = new HashMap<String, Object>();
				tempMap.put("orderList", orderModelList);
				tempMap.put("orderItemList", itemModelList);
				tempMap.put("payLogList", paymentLogList);
				return tempMap;
		}else{
			return null;
		}
	}
	
	//将BigDecimal 进行判断
	private String isNotNull(String price){
		if(null == price || "".equals(price)){
			return "0";
		}
		return price;
	}
	
	//将BigDecimal 进行判断
	private String strIsNotNull(String str){
		if(null == str || "".equals(str.trim())){
			return null;
		}
		return str;
	}
	
	//保存订单的各种信息
	private void saveOrderEtc(Map<String,Object> dataMap) {
		
		try {
			System.out.println("gw order save...............");
			
			List<OrderModel> orderList = (List<OrderModel>) dataMap.get("orderList");
			List<OrderItemModel> orderItemList = (List<OrderItemModel>) dataMap.get("orderItemList");
			List<PaymentLogModel> payLogList = (List<PaymentLogModel>) dataMap.get("payLogList");
			
				//订单处理
				int num = orderService.saveBatchOrder(orderList,orderErrorQueue);
				totalAmount = totalAmount+ num;
				//程序休眠5秒    并将错误信息做处理
				Thread.sleep(500);
				
				orderService.saveBatchOrderItem(orderItemList,orderitemErrorQueue);
				
				//程序休眠5秒    并将错误信息做处理
				Thread.sleep(500);
				
				payLogService.saveBatchPayLog(payLogList,payLogErrorQueue);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
	
	/**
	 * 保存批量执行信息
	 */
	@Transactional
	private void saveChannelOrderBatch(long multiChannelOrderBatch){
		if(totalAmount > 0){
			MultiChannelOrderBatchModel channelOrderModel = new MultiChannelOrderBatchModel();
			channelOrderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
			channelOrderModel.setMultiChannelId(7);//GW
			channelOrderModel.setImportTime(sdf.format(new Date()));
			channelOrderModel.setOrderAmount(totalAmount);
			channelOrderModel.setAddTime(sdf.format(new Date()));
			channelMapper.saveChannelOrder(channelOrderModel);
		}
		
	}
	
	public static void main(String[] args) {
		String str  = new GW_OrderServiceImpl().getGwOrderTrades(new String[]{"2016-09-01 00:00:00","2016-10-15 00:00:00"}, 1L,"7");
		System.out.println(str);
	}

}
