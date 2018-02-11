package cn.com.dubbo.service.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.AreaService;
import cn.com.dubbo.service.inter.PaymentLogService;
import cn.com.dubbo.service.order.JDOrderService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.ClassInfo;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.CouponDetail;
import com.jd.open.api.sdk.domain.order.ItemInfo;
import com.jd.open.api.sdk.domain.order.OrderResult;
import com.jd.open.api.sdk.domain.order.OrderSearchInfo;
import com.jd.open.api.sdk.domain.order.UserInfo;
import com.jd.open.api.sdk.request.order.OrderSearchRequest;
import com.jd.open.api.sdk.response.order.OrderSearchResponse;

/**
 * 京东订单查询
 */
@Service
public class JDOrderServiceImpl implements JDOrderService {
	
	public JdClient client = new DefaultJdClient(ThirdConstant.SERVER_URL,
			ThirdConstant.accessToken,ThirdConstant.appKey,ThirdConstant.appSecret);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private DataQueue<OrderModel> orderErrorQueue = new DataQueue<OrderModel>();
	
	private DataQueue<OrderItemModel> orderitemErrorQueue = new DataQueue<OrderItemModel>();
	
	private DataQueue<PaymentLogModel> payLogErrorQueue = new DataQueue<PaymentLogModel>();
	
	private static final Logger logger = Logger.getLogger(JDOrderServiceImpl.class);
	
	private final static int pageSize = 40;//一页显示的条目数量
	
	private int totalAmount = 0;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private PaymentLogService payLogService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private ChannelMapper channelMapper;
	
	@Override
	public void dealOrder(String startString,String endString,String orderState){
		try {
			
			totalAmount = 0;
			
			Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("2");
			
			System.out.println("jd orderSerach...............");
			
			this.orderSerach(1,"",startString,endString,orderState,
					multiChannelOrderBatch);
			
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
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 保存批量执行信息
	 */
	@Transactional
	private void saveChannelOrderBatch(long multiChannelOrderBatch){
		
		MultiChannelOrderBatchModel channelOrderModel = new MultiChannelOrderBatchModel();
		channelOrderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
		channelOrderModel.setMultiChannelId(2);//京东
		channelOrderModel.setImportTime(sdf.format(new Date()));
		channelOrderModel.setOrderAmount(totalAmount);
		channelOrderModel.setAddTime(sdf.format(new Date()));
		channelMapper.saveChannelOrder(channelOrderModel);
		
	}
	
	private void dealPayLogQueue(DataQueue<PaymentLogModel> errorQueue){
		PaymentLogModel model = null;
		try {
			logger.info("开始保存订单支付日志异常数据...........共 "+errorQueue.getLength()+" 条");
			while(!errorQueue.isEmpty()){
				model = errorQueue.deQueue();
				boolean bl = payLogService.isLogByOrderNo(model.getBusinessId());
				if(!bl){
					payLogService.savePaymentLog(model);
					logger.info("异常数据处理订单支付日志保存成功，对应的订单号为：" + model.getBusinessId());
				}
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("从队列中保存京东订单支付日志失败 ： " +e.getMessage());
		}
	}
	
	private void dealOrderItemQueue(DataQueue<OrderItemModel> errorQueue){
		OrderItemModel model = null;
		try {
			logger.info("开始保存订单Item异常数据...........共 "+errorQueue.getLength()+" 条");
			while(!errorQueue.isEmpty()){
				model = errorQueue.deQueue();
				boolean bl = orderService.isOrderItemByNo(model.getOrderNo());
				if(!bl){
					orderService.saveOrderItem(model);
					logger.info("异常数据处理订单Item保存成功，对应的订单号为：" + model.getOrderNo());
				}
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("从队列中保存京东订单Item失败 ： " +e.getMessage());
		}
	}
	
	private void dealOrderQueue(DataQueue<OrderModel> errorQueue){
		OrderModel model = null;
		try {
			logger.info("开始保存订单异常数据...........共 "+errorQueue.getLength()+" 条");
			while(!errorQueue.isEmpty()){
				model = errorQueue.deQueue();
				boolean bl = orderService.isOrderByNo(model.getOrderNo());
				if(!bl){
					orderService.saveOrder(model);
					logger.info("异常数据处理订单保存成功，对应的订单号为：" + model.getOrderNo());
				}
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("从队列中保存京东订单失败 ： " +e.getMessage());
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
	 * 查询订单
	 * http://jos.jd.com/api/detail.htm?apiName=360buy.order.search&id=393#a8
	 */
	public Map<String,Object> orderSerach(int pageIndex,String method,
			String startTime,String endTime,String orderState,
			long multiChannelOrderBatch) {
		Map<String,Object> dataMap = null;
		try {
			
			dataMap = this.pageQueryOrder(pageIndex,startTime, endTime, orderState,multiChannelOrderBatch);
			
			List<OrderModel> orderList = (List<OrderModel>) dataMap.get("orderList");
			List<OrderItemModel> orderItemList = (List<OrderItemModel>) dataMap.get("orderItemList");
			List<PaymentLogModel> payLogList = (List<PaymentLogModel>) dataMap.get("payLogList");
			
			//订单处理
			int num = orderService.saveBatchOrder(orderList,orderErrorQueue);
			
			totalAmount = totalAmount + num;
			orderService.saveBatchOrderItem(orderItemList,orderitemErrorQueue);
			payLogService.saveBatchPayLog(payLogList,payLogErrorQueue);
			
			int totalCount = (Integer) dataMap.get("totalCount");
			
			//总页码数
			int pageCount = getPageCount(totalCount);
			pageIndex = getNextPage(pageIndex, pageCount);
			
			if(pageIndex>0){
				System.out.println("pageIndex: "+pageIndex+" totalCount: "+totalCount+" pageCount: "+pageCount);
				this.orderSerach(pageIndex,method,startTime,endTime,orderState,
						multiChannelOrderBatch);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return dataMap;
	}
	
	/*
	 * 订单查询接口
	 */
	public Map<String,Object> pageQueryOrder(int pageIndex,
			String startTime,String endTime,String orderState,long multiChannelOrderBatch) throws JdException{
		
		OrderSearchRequest request=new OrderSearchRequest();
		//格式：2010-12-20 17:15:00
		request.setStartDate(startTime);
		request.setEndDate(endTime);
		/*
		 * 多订单状态可以用英文逗号隔开
		 *  1）WAIT_SELLER_STOCK_OUT 等待出库 
		 *  2）SEND_TO_DISTRIBUTION_CENER 发往配送中心（只适用于LBP，SOPL商家） 
		 *  3）DISTRIBUTION_CENTER_RECEIVED 配送中心已收货（只适用于LBP，SOPL商家） 
		 *  4）WAIT_GOODS_RECEIVE_CONFIRM 等待确认收货 
		 *  5）RECEIPTS_CONFIRM 收款确认（服务完成）（只适用于LBP，SOPL商家）
		 *   6）WAIT_SELLER_DELIVERY等待发货（只适用于海外购商家，等待境内发货 标签下的订单） 
		 *   7）FINISHED_L 完成 
		 *   8）TRADE_CANCELED 取消 
		 *   9）LOCKED 已锁定 
		 *   10）PAUSE 暂停  
		 */
		request.setOrderState(orderState);//必填
		request.setPage(new Long(pageIndex).toString());//必填
		request.setPageSize(new Long(pageSize).toString());//必填每页的条数（最大page_size 100条）  
//		request.setOptionalFields("payment_confirm_time"); //TODO 需要加入相关信息 付款确认时间/物流公司ID/售后订单标记
//		request.setOptionalFields("logistics_id");
//		request.setSortType("jingdong");//排序方式，默认升序,1是降序,其它数字都是升序
		request.setDateType("1");//查询时间类型，默认按修改时间查询。 1为按订单创建时间查询；其它数字为按订单（订单状态、修改运单号）修改时间 
		OrderSearchResponse response = client.execute(request);
		String code = response.getCode();
		Map<String,Object> map = new HashMap<String, Object>();
		if("0".equals(code)){
			OrderResult result = response.getOrderInfoResult();
			List<OrderSearchInfo> orderJdlist = result.getOrderInfoList();
			if(null!=orderJdlist&&orderJdlist.size()>0){
				map = json2Order(orderJdlist,multiChannelOrderBatch);
			}
			int orderTotal = result.getOrderTotal();
			map.put("totalCount", orderTotal);
		}else{
			//异常
//			System.out.println(response.getCode()+response.getMsg());
		}
		return map;
	}
	
	
	public Map<String,Object> json2Order(List<OrderSearchInfo> orderJdlist,long multiChannelOrderBatch){
		
		Map<String,Object> tempMap = new HashMap<String, Object>();
		List<OrderModel> orderList = new ArrayList<OrderModel>();
		List<OrderItemModel> orderItemList = new ArrayList<OrderItemModel>();
		
		List<PaymentLogModel> payLogList = new ArrayList<PaymentLogModel>();
		
		if(null!=orderJdlist&&orderJdlist.size()>0){
			
			OrderModel orderModel = null; //订单信息
			OrderItemModel itemModel = null;//订单明细信息
			PaymentLogModel paymentLog = null;//支付log
			
			for(OrderSearchInfo searchInfo : orderJdlist){
				
				orderModel = new OrderModel();
				//订单id 
				orderModel.setOrderNo(ChannelConstant.CHANNEL_JD+searchInfo.getOrderId());
				orderModel.setMultiChannelOrderBatchId(multiChannelOrderBatch);
				orderModel.setMultiChannelId(2);
				orderModel.setFromMedia(searchInfo.getOrderSource());
				orderModel.setMultiChannelOrderNo(searchInfo.getOrderId());
				orderModel.setOrderType(0);
				String orderState = searchInfo.getOrderState();
				if(orderState.equals("TRADE_CANCELED")){//已取消
					orderModel.setOrderStatus(7);
				}else{
					orderModel.setOrderStatus(1);
				}
				orderModel.setHaveCfy(Constant.NO);
				//支付方式（1货到付款, 2邮局汇款, 3自提, 4在线支付, 5公司转账, 6银行卡转账
				String payType = searchInfo.getPayType();
				if("1".equals(payType)){
					orderModel.setIsPay(Constant.NO);
					orderModel.setPaymentType(Constant.PAYMENT_TYPE_HDFK);
				}else{
					orderModel.setIsPay(Constant.YES);
					orderModel.setPaymentType(Constant.PAYMENT_TYPE_KDFH);
				}
				
				//订单总金额
				String  order_total_price = searchInfo.getOrderTotalPrice();
				
				orderModel.setSkuFee(new BigDecimal(order_total_price));
				
				
				//商家优惠金额
				orderModel.setActivityDiscountFee(new BigDecimal(searchInfo.getSellerDiscount()));
				
				//商品的运费
				String freight_price = searchInfo.getFreightPrice();
				if(!StringUtil.isBlank(freight_price)){
					orderModel.setDeliveryFee(new BigDecimal(freight_price));
				}
				//订单货款金额（订单总金额-商家优惠金额)
				String order_payment = searchInfo.getOrderPayment();
				if(!StringUtil.isBlank(order_payment)){
					orderModel.setPaidFee(new BigDecimal(order_payment));
					orderModel.setOrderFee(orderModel.getPaidFee());
				}else{
					order_payment = searchInfo.getOrderSellerPrice();
					if(!StringUtil.isBlank(order_payment)){
						orderModel.setPaidFee(new BigDecimal(order_payment));
						orderModel.setOrderFee(orderModel.getPaidFee());
					}
				}
				
				orderModel.setOrderRealFee(BigDecimal.ZERO);
				//订单状态说明（中文）  
				searchInfo.getOrderStateRemark();
				orderModel.setValid(Constant.YES);
				//买家下单时订单备注 
				orderModel.setOrderMsg(searchInfo.getOrderRemark());
				//下单时间
				orderModel.setCommitTime(searchInfo.getOrderStartTime());
				orderModel.setAddTime(sdf.format(new Date()));
				//商品明细信息
				List<ItemInfo> itemJdList = searchInfo.getItemInfoList();
				if(null!=itemJdList&&itemJdList.size()>0){
					for(ItemInfo jd_item :itemJdList){
						itemModel = new OrderItemModel();
						itemModel.setOrderNo(orderModel.getOrderNo());
						itemModel.setAddTime(sdf.format(new Date()));
						//SKU外部ID（极端情况下不保证返回，建议从商品接口获取）
						itemModel.setGoodsNo_69(jd_item.getOuterSkuId());
						//明细类型
						if(!StringUtil.isBlank(itemModel.getGoodsNo_69())&&itemModel.getGoodsNo_69().contains("TM")){
							itemModel.setGoodsListType(Constant.GOODS_TYPE_GROUP);
						}else{
							itemModel.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
						}
						String itemTotal = jd_item.getItemTotal();
						if(!StringUtil.isBlank(itemTotal)){
							itemModel.setAmount(new Long(itemTotal));
						}
						itemModel.setGoodsType(Constant.GOODS_TYPE_OTHER);
						//京东内部SKU的ID
						String skuId = jd_item.getSkuId();
						//商品的名称+SKU规格 
						itemModel.setName(jd_item.getSkuName());
						//SKU的京东价
						itemModel.setOldPrice(new BigDecimal(jd_item.getJdPrice()));
						itemModel.setPrice(new BigDecimal(0));
						itemModel.setPriceDis(new BigDecimal(0));
						itemModel.setGoodsSumFee(new BigDecimal(0));
						//数量
						String item_total = jd_item.getItemTotal();
						itemModel.setAmount(new Long(item_total));
						//优惠信息
						//保存优惠信息
						List<CouponDetail> couponJdList = searchInfo.getCouponDetailList();
						if(null!=couponJdList&&couponJdList.size()>0){
							BigDecimal disAcountFee = new BigDecimal(0);
							for(CouponDetail jdDetail : couponJdList){
								String couponPrice = jdDetail.getCouponPrice();
								String couponSkuId = jdDetail.getSkuId();
								if(!StringUtil.isBlank(skuId)&&
										!StringUtil.isBlank(couponSkuId)){
									if(couponSkuId.equals(skuId)){
										itemModel.setPriceDis(new BigDecimal(couponPrice));
									}
								}
								//累加
								if(!StringUtil.isBlank(couponPrice)){
									disAcountFee = MathUtil.add(disAcountFee, new BigDecimal(couponPrice));
								}
							}
							orderModel.setActivityDiscountFee(disAcountFee);
						}
						orderItemList.add(itemModel);
					}
				}
				
				//在保存一条支付实例,货到付款方式的不保存支付信息
				//付款确认时间
				String paymentConfirmTime = searchInfo.getPaymentConfirmTime();
				
				if(Constant.PAYMENT_TYPE_KDFH.equals(orderModel.getPaymentType())){
					paymentLog = new PaymentLogModel();
					paymentLog.setBusinessType("ADD");
					paymentLog.setBusinessId(orderModel.getOrderNo());
					getPayMethod(payType, paymentLog);
					//实付金额
					if(!StringUtil.isBlank(order_payment)){
						paymentLog.setPaidFee(new BigDecimal(order_payment));
					}
					//支付时间
					if(!StringUtil.isBlank(paymentConfirmTime)){
						paymentLog.setPaymentTime(searchInfo.getPaymentConfirmTime());
					}else{
						paymentLog.setPaymentTime(searchInfo.getModified());
					}
					paymentLog.setAddTime(sdf.format(new Date()));
					
					payLogList.add(paymentLog);
					
					//保存支付的优惠信息
					List<CouponDetail> couponJdList = searchInfo.getCouponDetailList();
					if(null!=couponJdList&&couponJdList.size()>0){
						for(CouponDetail jdDetail : couponJdList){
							String couponPrice = jdDetail.getCouponPrice();
							String coupon_type = jdDetail.getCouponType();
							if(!StringUtil.isBlank(coupon_type)&&
									!StringUtil.isBlank(couponPrice)){
								paymentLog = new PaymentLogModel();
								paymentLog.setBusinessType("ADD");
								paymentLog.setBusinessId(orderModel.getOrderNo());
								paymentLog.setOrderPaymentId(PayCode.DKQ_ALI);
								paymentLog.setOrderPaymentName("抵扣券 ("+coupon_type+")");
								//实付金额
								paymentLog.setPaidFee(new BigDecimal(couponPrice));
								//支付时间
								if(!StringUtil.isBlank(paymentConfirmTime)){
									paymentLog.setPaymentTime(searchInfo.getPaymentConfirmTime());
								}else{
									paymentLog.setPaymentTime(searchInfo.getModified());
								}
								paymentLog.setAddTime(sdf.format(new Date()));
								payLogList.add(paymentLog);
							}
						}
					}
				}
				
				if(!StringUtil.isBlank(paymentConfirmTime)){
					orderModel.setPlatformCreateTime(searchInfo.getPaymentConfirmTime());
				}else{
					orderModel.setPlatformCreateTime(searchInfo.getModified());
				}
				
				//-------------发票信息--------------
				String invoiceInfo = searchInfo.getInvoiceInfo();
				if(!"不需要开具发票".equals(invoiceInfo)){
					orderModel.setInvoiceTitle("开票");
					orderModel.setInvoiceContent(invoiceInfo);
				}
				
				//--------------收货人相关信息------------
				UserInfo Jd_User = searchInfo.getConsigneeInfo();
				if(null!=Jd_User){
					//姓名
					orderModel.setReceiveUser(Jd_User.getFullname());
					//固定电话
					orderModel.setReceiveTel(Jd_User.getTelephone());
					//手机
					orderModel.setReceiveMobile(Jd_User.getMobile());
					//地址
					orderModel.setReceiveFullAddress(Jd_User.getFullAddress());
					//省
					String province = Jd_User.getProvince();
					orderModel.setProvinceName(province);
					orderModel.setProvinceId(areaService.findArea(0, province));
					//市
					String city = Jd_User.getCity();
					orderModel.setCityName(city);
					orderModel.setCityId(areaService.findArea(orderModel.getProvinceId(), city));
					//县
					String county = Jd_User.getCounty();
					
					orderModel.setAreaName(county);
					orderModel.setAreaId(areaService.findArea(orderModel.getCityId(), county));
					orderModel.setReceiveAddress(province+city+county);
				}
				orderList.add(orderModel);
			}
		}
		tempMap.put("orderList", orderList);
		tempMap.put("orderItemList", orderItemList);
		tempMap.put("payLogList", payLogList);
		return tempMap;
	}
	/**
	 * 京东的支付方式
	 * @param pay_type
	 */
	private static void getPayMethod(String pay_type,PaymentLogModel logModel){
		String[] pays = pay_type.split("-");
//		1货到付款, 2邮局汇款, 3自提, 4在线支付, 5公司转账, 6银行卡转账
		String payid = pays[0];
		if("1".equals(payid)){
			logModel.setOrderPaymentId(PayCode.KDFH_ALI);
			logModel.setOrderPaymentName("虚拟支付，后结算模式");
		}else if("2".equals(payid)||"4".equals(payid)
				||"5".equals(payid)||"6".equals(payid)){
			logModel.setOrderPaymentId(PayCode.WS_ALI);
			logModel.setOrderPaymentName("网上支付");
		}else if("3".equals(payid)){
			logModel.setOrderPaymentId(PayCode.XX_ALI);
			logModel.setOrderPaymentName("线下支付");
		}
		
	}
	
}
