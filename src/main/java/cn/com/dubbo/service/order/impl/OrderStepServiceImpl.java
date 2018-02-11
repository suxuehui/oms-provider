package cn.com.dubbo.service.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.constant.PayCode;
import cn.com.dubbo.mapper.PaymentLogMapper;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.GoodsStockInfo;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.service.inter.OrderLogService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.util.ClassInfo;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;

@Service
public class OrderStepServiceImpl implements OrderStepService {

	@Resource
	private PaymentLogMapper payLogMapper;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderLogService orderLogService;
	
	public final static String deviationVal = "0.0021";//审核金额的误差范围
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
	
	@Override
	public void orderNotRXExamine(String multiChannel,int orderState){
		//查询数据参数
		int pageSize=45;
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orderState", orderState);//
		params.put("pageIndex", 0);//从第一页开始查询
		params.put("pageSize", pageSize);
		params.put("haveCfy",Constant.NO);
		if(multiChannel.equals(ChannelConstant.CHANNEL_PA)){
			params.put("multiChannelId", 4);
		}else if(multiChannel.equals(ChannelConstant.CHANNEL_36)){
			params.put("multiChannelId", 3);
		}else if(multiChannel.equals(ChannelConstant.CHANNEL_JD)){
			params.put("multiChannelId", 2);
		}else if(multiChannel.equals(ChannelConstant.CHANNEL_TM)){
			params.put("multiChannelId", 1);
		}else if(multiChannel.equals(ChannelConstant.CHANNEL_PS)){
			params.put("multiChannelId", 5);
		}
		//查询的总条目
		int totalCount = orderService.pageQueryOrderListCount(params);
		if(totalCount>0){
			this.pageQueryOrderExamine(multiChannel,params,1,totalCount,pageSize);
		}
	}
	
	@Transactional
	private void saveData(List<OrderModel> changeList,List<OrderLogModel> errorLogList){
		
		try {
			//更新订单数据
			DataQueue<OrderModel> orderQueue = new DataQueue<OrderModel>();
			
			this.updateBatchOrder(changeList, orderQueue);
			
			if(!orderQueue.isEmpty()){
				this.dealOrderQueue(orderQueue);
			}
			
			//保存日志信息
			DataQueue<OrderLogModel> logModel = new DataQueue<OrderLogModel>();
			orderLogService.saveBatchOrderLog(errorLogList, logModel);
			if(!logModel.isEmpty()){
				this.dealOrderLogQueue(logModel);
			}
		} catch (Exception e) {
			logger.error("审核订单时，更新订单状态失败，"+e.getMessage(),e);
		}
		
	}
	
	private void dealOrderQueue(DataQueue<OrderModel> orderQueue){
		
		OrderModel model = null;
		try {
			Thread.sleep(200);
			logger.info("休息3秒后开始保存订单更新异常数据...........共 "+orderQueue.getLength()+" 条");
			while(!orderQueue.isEmpty()){
				model = orderQueue.deQueue();
				orderService.updateOrder(model);
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("订单审核时，从队列中保存订单异常数据失败 ： " +e.getMessage());
		}
	}
	
	public void updateBatchOrder(List<OrderModel> orderList,DataQueue<OrderModel> orderQueue){
		
		try {
			if(null!=orderList&&orderList.size()>0){
				orderService.updateBatchOrder(orderList);
			}
		} catch (Exception e) {
			if(null!=orderList&&orderList.size()>0){
				for(OrderModel model : orderList){
					logger.error("审核订单时，批量更新错误，错误orderNO："+model.getOrderNo()+",状态："+model.getOrderStatus());
					orderQueue.enQueue(model);
				}
			}
			logger.error("审核订单时，批量更新错误，错误条目："+orderList.size()+" ,错误信息："+e.getMessage(),e);
		}
	}
	
	
	private void dealOrderLogQueue(DataQueue<OrderLogModel> logModel){
		OrderLogModel model = null;
		try {
			logger.info("休息5秒后开始保存订单日志异常数据...........共 "+logModel.getLength()+" 条");
			while(!logModel.isEmpty()){
				model = logModel.deQueue();
				orderLogService.saveOrderLog(model);
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("从队列中保存订单异常数据失败 ： " +e.getMessage());
		}
	}
	
	private void pageQueryOrderExamine(String multiChannel,Map<String,Object> params,int pageIndex,
			int totalCount,int pageSize){
		/*List<OrderModel> orderList = new ArrayList<OrderModel>();
		OrderModel temporder = orderService.findOrderByNo("PS680946060203");
		orderList.add(temporder);*/
		
		List<OrderModel> orderList = orderService.pageQueryOrderList(params);
		//保存需要更新的订单
		List<OrderModel> changeList = new ArrayList<OrderModel>();
		//保存错误的订单日志
		List<OrderLogModel> errorLogList = new ArrayList<OrderLogModel>();
		
		if(null!=orderList&&orderList.size()>0){
			String reason = "";
			OrderModel changeOrder = null;
			OrderLogModel orderLog = null;//错误日志
			for(OrderModel orderModel : orderList){
				
				changeOrder = new OrderModel();
				changeOrder.setOrderNo(orderModel.getOrderNo());
				changeOrder.setAuditTime(sdf.format(new Date()));
				changeOrder.setEditTime(sdf.format(new Date()));
				//审核校验
				reason = this.dealExamine(orderModel);
				if(StringUtil.isBlank(reason)){//订单审核通过
					changeOrder.setOrderStatus(OrderState.STATE_2.getCode());
					//计算结算的总价格
					List<OrderItemModel> itemModel = orderService.queryOrderItems(orderModel.getOrderNo());
					if(itemModel!=null&&itemModel.size()>0){
						BigDecimal temp = BigDecimal.ZERO;
						for(OrderItemModel item : itemModel){
							if(null!=item.getSupplyPrice()){
								temp = MathUtil.add(temp, MathUtil.mul(item.getSupplyPrice(), new BigDecimal(item.getAmount())));
							}
						}
						changeOrder.setSupplyPrice(temp);
					}
				}else{//订单审核不通过
					if(reason.contains("库存信息为空")){//暂时通过，仅记录日志
						changeOrder.setOrderStatus(OrderState.STATE_13.getCode());
					}else{
						changeOrder.setOrderStatus(OrderState.STATE_3.getCode());
					}
					//记录不通过的原因
					orderLog = new OrderLogModel();
					orderLog.setOrderNo(orderModel.getOrderNo());
					orderLog.setOrderStateId(OrderState.STATE_1.getCode());//待审核
					orderLog.setLogContent(reason);
					orderLog.setLogTime(sdf.format(new Date()));
					orderLog.setAddTime(orderLog.getLogTime());
					errorLogList.add(orderLog);
				}
				changeList.add(changeOrder);
			}
		}
		//更新数据
		this.saveData(changeList,errorLogList);
		//总页码数
		pageIndex++;
		int pageCount = getPageCount(totalCount, pageSize);
		if(pageIndex>0&&pageIndex<=pageCount){
			logger.info(multiChannel + " 订单审核：pageIndex: "+pageIndex+" totalCount: "+totalCount+" pageCount: "+pageCount);
			//递归调用自身方法
			params.put("pageIndex", 0);
			this.pageQueryOrderExamine(multiChannel,params,pageIndex,totalCount,pageSize);
		}
	}
	
	@Override
	public void orderNotRXExamine(List<OrderModel> orderList){
		
		if(null!=orderList&&orderList.size()>0){
			//保存需要更新的订单
			List<OrderModel> changeList = new ArrayList<OrderModel>();
			//保存错误的订单日志
			List<OrderLogModel> errorLogList = new ArrayList<OrderLogModel>();
			String reason = "";
			OrderModel changeOrder = null;
			OrderLogModel orderLog = null;//错误日志
			for(OrderModel orderModel : orderList){
				changeOrder = new OrderModel();
				changeOrder.setOrderNo(orderModel.getOrderNo());
				changeOrder.setAuditTime(sdf.format(new Date()));
				changeOrder.setEditTime(sdf.format(new Date()));
				changeOrder.setHaveCfy(orderModel.getHaveCfy());
				//审核校验
				reason = this.dealExamine(orderModel);
				if(StringUtil.isBlank(reason)){//订单审核通过
					changeOrder.setOrderStatus(OrderState.STATE_2.getCode());
					//计算结算的总价格
					List<OrderItemModel> itemModel = orderService.queryOrderItems(orderModel.getOrderNo());
					if(itemModel!=null&&itemModel.size()>0){
						BigDecimal temp = BigDecimal.ZERO;
						for(OrderItemModel item : itemModel){
							if(null!=item.getSupplyPrice()){
								temp = MathUtil.add(temp, MathUtil.mul(item.getSupplyPrice(), new BigDecimal(item.getAmount())));
							}
						}
						changeOrder.setSupplyPrice(temp);
					}
				}else{//订单审核不通过
					if(reason.contains("库存信息为空")){//暂时通过，仅记录日志
						changeOrder.setOrderStatus(OrderState.STATE_13.getCode());
					}else{
						changeOrder.setOrderStatus(OrderState.STATE_3.getCode());
					}
					//记录不通过的原因
					orderLog = new OrderLogModel();
					orderLog.setOrderNo(orderModel.getOrderNo());
					orderLog.setOrderStateId(OrderState.STATE_1.getCode());//待审核
					orderLog.setLogContent(reason);
					orderLog.setLogTime(sdf.format(new Date()));
					orderLog.setAddTime(orderLog.getLogTime());
					errorLogList.add(orderLog);
				}
				changeList.add(changeOrder);
			}
			//更新数据
			this.saveData(changeList,errorLogList);
		}
	}
	

    /*
     * 总页数
     * @params totalCount 信息总条数
     * @return 总页码
     */
    public static int getPageCount(int totalCount,int pageSize) {
    	
        int size = totalCount/pageSize;//总条数/每页显示的条数=总页数
        int mod = totalCount % pageSize;//最后一页的条数
        if(mod != 0)
            size++;
        return totalCount == 0 ? 1 : size;
        
    } 
	
	@Transactional
	public String dealExamine(OrderModel orderModel){
		
		try {
			
			List<OrderItemModel> itemList = orderService.queryOrderItems(orderModel.getOrderNo());
			Map<String,Object> map = this.examineOne(orderModel,itemList);
			int code = (Integer) map.get("code");
			String reason = null;
			if(1==code){
				reason = (String) map.get("msg");
				return reason;
			}
			//2
			boolean bl = this.examineTwo(orderModel.getReceiveUser());
			if(bl){
				reason = "收货人姓名包含‘测试’两个字";
				return reason;
			}
			//3金额
			map = this.examineThree(orderModel,itemList);
			code = (Integer) map.get("code");
			if(1==code){
				reason = (String) map.get("msg");
				return reason;
			}
			//4
			map = this.examineFour(itemList,new Long(orderModel.getMultiChannelId()).intValue());
			code = (Integer) map.get("code");
			if(1==code){
				reason = (String) map.get("msg");
				return reason;
			}
			//5
			bl = this.examineFive(orderModel.getProvinceName());
			if(!bl){
				reason = "省份异常";
				return reason;
			}
			//6
			map = this.examineSix(orderModel);
			code = (Integer) map.get("code");
			if(1==code){
				reason = (String) map.get("msg");
				return reason;
			}
			//7略
//			this.examineSeven(orderModel);
			//8略
//			this.examineEight(orderModel);
			//9审核库存
			map = this.examineNine(itemList);
			code = (Integer) map.get("code");
			if(1==code){
				reason = (String) map.get("msg");
				return reason;
			}
			
		} catch (Exception e) {
			logger.error("订单审核出错，订单号："+orderModel.getOrderNo()+e.getMessage(),e);
			return "校验出错";
		}
		//更新订单状态为已审核
		return null;
	}
	
	
	/**
	 * 订单合并
	 * 1、合并维度：同一渠道、同一收货人（收货人姓名、收货地址、收货人手机号一致）、同一仓库、相同物流公司
		2、合并包裹单号规则：
		a)如未涉及到合并订单，合并包裹号为发货单号。
		b)如有合并订单，合并包裹号为HB+其中任意一个发货单号
		3、触发方式：定期执行
	 */
	@Override
	public void orderMerge(){
		
		
		
		
		
	}
	
	
	/**
	 * 9).审核库存，
	 * @param sellerNum 客户购买的商品数
	 * @param goodsNum 剩余的库存数 
	 * @return
	 */
	@Override
	public Map<String,Object> examineNine(List<OrderItemModel> itemList){
		
		Map<String,Object> retMsg = new HashMap<String, Object>();
		if(null!=itemList&&itemList.size()>0){
//			GoodsSaleInfoModel saleInfoModel = null;
			GoodsStockInfo stockInfo = null;
			int amount = 0;
			for(OrderItemModel item : itemList){
				//校验库存，更新库存
				if(!StringUtil.isBlank(item.getGoodsNo_69())&&!item.getGoodsNo_69().contains("TM")){
					/*
					 * 原来的写法
					 * saleInfoModel = goodsService.findSaleInfoByNo69(item.getGoodsNo_69());
					if(null!=saleInfoModel){
						amount = new Long(item.getAmount()).intValue();
						//减库存操作
						saleInfoModel.setAvailableStock(saleInfoModel.getAvailableStock()-amount);
						goodsService.updateGoodsSaleInfo(saleInfoModel);
					}else{
						logger.error("商品库存信息为空，商品69码为："+ item.getGoodsNo_69() + " ,商品 编号为："+item.getGoodsNo());
						retMsg.put("msg", "商品库存信息为空，商品69码为："+ item.getGoodsNo_69() + " ,商品 编号为："+item.getGoodsNo());
						retMsg.put("code", 1);
						return retMsg;
					}*/
					
					stockInfo = goodsService.findGoodsStock(item.getGoodsNo());
					if(null!=stockInfo){
						amount = new Long(item.getAmount()).intValue();
						//减库存操作
						goodsService.updateGoodsStock(item.getGoodsNo(), -amount, amount, 0);
					}else{
						logger.error("商品库存信息为空，商品69码为："+ item.getGoodsNo_69() + " ,商品 编号为："+item.getGoodsNo());
						retMsg.put("msg", "商品库存信息为空，商品69码为："+ item.getGoodsNo_69() + " ,商品 编号为："+item.getGoodsNo());
						retMsg.put("code", 1);
						return retMsg;
					}
				}
				
			}
		}
		retMsg.put("code", 0);
		return retMsg;
	}
	
	
	/**
	 * 8).快递类型为京东货到付款时，验证是否存在物流单号，不存在时，调用京东接口获得一个物流单号，如调用失败，则审核不通过。
		品种类型为正常的订单所有验证都通过后，审单才能通过。
		品种类型不为正常的订单需要验证1~5点，验证通过后则审单通过。
	 * @return
	 */
	private boolean examineEight(OrderModel orderModel){
		
		return true;
	}
	
	/**
	 * 7).验证付款方式与快递方式是否匹配，既付款方式为款到发货时，快递类型不能为货到付款的快递类型，不匹配时，审核不通过。
	 * @param orderModel
	 * @return
	 */
	private boolean examineSeven(OrderModel orderModel){
		
		
		return true;
	}
	
	/**
	 * 6).验证收货人地址、电话、姓名是否存在黑名单之中，存在时不审单。
	 * @param orderModel
	 * @return
	 */
	private Map<String,Object> examineSix(OrderModel orderModel){
		
		// TODO
		Map<String,Object> retMsg = new HashMap<String, Object>();
		/*retMsg.put("code", 1);
		if(StringUtil.isBlank(orderModel.getReceiveUser())){
			retMsg.put("msg", "收货人姓名在黑名单中");
			return retMsg;
		}
		if(StringUtil.isBlank(orderModel.getReceiveMobile())){
			retMsg.put("msg", "收货人电话在黑名单中");
			return retMsg;
		}
		if(StringUtil.isBlank(orderModel.getReceiveFullAddress())){
			retMsg.put("msg", "收货人地址在黑名单中");
			return retMsg;
		}*/
		retMsg.put("code", 0);
		return retMsg;
	}
	
	/**
	 * 5).验证收货人省份必须在国内三十二个省份之内，否则不审单;
	 * @return true 国内  <br/>false 国外
	 */
	public boolean examineFive(String provinceName){
		if(!StringUtil.isBlank(provinceName)){
			return true;
		}
		return false;
	}
	
	/**
	 * 4).验证商品编码是否在商品表中存在，不存在时，审核不通过。
	 * multiChannelId  渠道Id
	 */
	private Map<String,Object> examineFour(List<OrderItemModel> itemList,int multiChannelId){
		
		Map<String,Object> retMsg = new HashMap<String, Object>();
		if(null!=itemList && itemList.size()>0){
			GoodsModel model = null;
			for(OrderItemModel item : itemList){
				//平安的
				if(4==multiChannelId){
					if(!StringUtil.isBlank(item.getGoodsNo_69())
							&&!Constant.GOODS_TYPE_GROUP.equals(item.getGoodsListType())){
						if(!StringUtil.isBlank(item.getGoodsNo())){
							model = goodsService.findGoodNo(item.getGoodsNo());
						}else{
							model = goodsService.findGood69(item.getGoodsNo_69());
						}
						if(null==model){
							retMsg.put("code", 1);
							logger.error("订单审核过程中，该订单号 ："+item.getOrderNo()+" 对应的商品69码： "+ item.getGoodsNo_69() +"在商品基础信息表中不存在 ");
							retMsg.put("msg", "订单审核过程中，该订单号 ："+item.getOrderNo()+" 对应的商品69码： "+ item.getGoodsNo_69() +"在商品基础信息表中不存在 ");
							return retMsg;
						}
					}
				}
			}
		}
		retMsg.put("code", 0);
		return retMsg;
	}
	
	/**
	 * 3).验证订单价格(sum(购买数量*商品价格-商品优惠)+邮费)是否与支付方式表支付金额一致，不一致时，审核不通过。<br/>
	 * 公式：<br/>
	 * itemModel.getOldPrice()*itemModel.getAmount() 购买数量*商品价格<br/>
	 * 	-orderModel.getActivityDiscountFee()商品优惠<br/>
	 * 	+orderModel.getDeliveryFeeOld()邮费<br/>
	 * 	=payLogModel.getPaidFee();支付表支付金额
	 * @return
	 */
	public Map<String,Object> examineThree(OrderModel orderModel,List<OrderItemModel> itemList){
		
		Map<String,Object> retMsg = new HashMap<String, Object>();
		try {
			
			BigDecimal sumPrice = BigDecimal.ZERO;
			//平安的订单
			if(null!=itemList&&itemList.size()>0){
				for(OrderItemModel item : itemList){
					if(Constant.GOODS_TYPE_GENERAL.equals(item.getGoodsListType())){
						if(null==item.getPriceDis()){//防止数据为空报错
							item.setPriceDis(new BigDecimal(0));
						}
						sumPrice = MathUtil.add(sumPrice,MathUtil.sub(
								MathUtil.mul(item.getOldPrice(), new BigDecimal(item.getAmount())), 
								item.getPriceDis()));
					}
				}
			}
			
			//加上邮费
			sumPrice = MathUtil.add(sumPrice, orderModel.getDeliveryFee());
			
			//查询支付方式表中的金额,必须是先付款的方式
			BigDecimal payPrice = BigDecimal.ZERO;
			if(Constant.PAYMENT_TYPE_KDFH.equals(orderModel.getPaymentType())){
				List<PaymentLogModel> payLogList = payLogMapper.queryLogsByOrderNo(orderModel.getOrderNo());
				if(null!=payLogList && payLogList.size()>0){
					for(PaymentLogModel logModel : payLogList){
						//平安的现金方式
						if(PayCode.XJ_ALI==logModel.getOrderPaymentId()){
							payPrice = MathUtil.add(payPrice, logModel.getPaidFee());
						}
						
						if(PayCode.KDFH_ALI==logModel.getOrderPaymentId()
								||PayCode.WS_ALI==logModel.getOrderPaymentId()){
							payPrice = MathUtil.add(payPrice, logModel.getPaidFee());
						}
						if(PayCode.ZFB_ALI==logModel.getOrderPaymentId()
								||PayCode.WX_ALI==logModel.getOrderPaymentId()){
							payPrice = MathUtil.add(payPrice, logModel.getPaidFee());
						}
					}
				}
			}else{//货到付款
				payPrice = orderModel.getPaidFee();
			}
			int code = MathUtil.compare(MathUtil.sub(sumPrice, payPrice),new BigDecimal(deviationVal));
			if(code>0){
				logger.error("订单审核过程中，该订单号 ："+orderModel.getOrderNo()+"金额 : "+sumPrice+"不匹配支付表金额："+payPrice);
				retMsg.put("msg","订单审核过程中，该订单号 ："+orderModel.getOrderNo()+"金额 : "+sumPrice+"不匹配支付表金额："+payPrice);
				retMsg.put("code", 1);
				return retMsg;
			}
			
		} catch (Exception e) {
			logger.error("订单审核金额校验过程中出错 ,订单号："+ orderModel.getOrderNo() + e.getMessage(),e);
			retMsg.put("msg","订单审核金额校验过程中异常,请查看日志 ，订单号："+orderModel.getOrderNo());
			retMsg.put("code", 1);
			return retMsg;
		}
		retMsg.put("code", 0);
		return retMsg;
	}
	
	
	/**
	 * 是否含有测试两字
	 * 2).如收货人姓名包含‘测试’两个字时，则把订单状态置为“审单前拒绝”。
	 * @param orderModel
	 * @return
	 */
	private boolean examineTwo(String receiveUser){
		if(receiveUser.contains("测试")){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 1).验证收货人姓名、收货人地址、收货人手机与收货人电话必填一个、收货人省、付款类型、商品编码、快递方式不能为空，为空时，审核不通过；
	 * @param orderModel
	 * @return Map<String,String>  0 成功  1 失败
	 */
	private Map<String,Object> examineOne(OrderModel orderModel,List<OrderItemModel>  itemList){
		
		Map<String,Object> retMsg = new HashMap<String, Object>();
		retMsg.put("code", 1);
		if(StringUtil.isBlank(orderModel.getReceiveUser())){
			retMsg.put("msg", "收货人姓名为空");
			return retMsg;
		}
		if(StringUtil.isBlank(orderModel.getReceiveFullAddress())){
			retMsg.put("msg", "收货人地址为空");
			return retMsg;
		}
		if(StringUtil.isBlank(orderModel.getReceiveTel())&&StringUtil.isBlank(orderModel.getReceiveMobile())){
			retMsg.put("msg", "收货人手机与收货人电话必填一个");
			return retMsg;
		}
		if(StringUtil.isBlank(orderModel.getProvinceName())){
			retMsg.put("msg", "收货人省为空");
			return retMsg;
		}
		if(StringUtil.isBlank(orderModel.getPaymentType())){
			retMsg.put("msg", "付款类型为空");
			return retMsg;
		}
		
		if(null!=itemList&&itemList.size()>0){
			Set<String> set = new HashSet<String>();
			String tempStr = "";
			int i=0;
			int j=0;
			for(OrderItemModel itemModel : itemList){
				
				if(Constant.GOODS_TYPE_GENERAL.equals(itemModel.getGoodsListType())){
					if(StringUtil.isBlank(itemModel.getGoodsNo())){
						retMsg.put("msg", "该订单号对应的商品编码为空：需要补充数据");
						return retMsg;
					}
					if(StringUtil.isBlank(itemModel.getGoodsNo_69())){
						retMsg.put("msg", "该订单号对应的商品69编码为空：需要补充数据");
						return retMsg;
					}
				}
				
				if(StringUtil.isBlank(itemModel.getGroupStatus())
						&&itemModel.getGoodsNo_69().contains("TM")){//校验是否拆分
					retMsg.put("msg", "未进行订单明细的拆分,可能系统未录入该组合套装，请 (事业部)同事核实");
					return retMsg;
				}
				//是否重复进行了拆分
				if(Constant.YES.equals(itemModel.getGroupStatus())
						&&Constant.GOODS_TYPE_GENERAL.equals(itemModel.getGoodsListType())){
					tempStr = itemModel.getGoodsNo_69()+itemModel.getTmSource();
					if(set.contains(tempStr)){
						retMsg.put("msg", "重复拆分");
						return retMsg;
					}else{
						set.add(tempStr);
					}
					i=i+1;
				}
				if(Constant.GOODS_TYPE_GROUP.equals(itemModel.getGoodsListType())){
					j=j+goodsService.findGroupNum(itemModel.getGoodsNo_69());
				}
			}
			//校验拆分的条目是否正确
			if(i!=j){
				retMsg.put("msg", "拆分数量错误");
				return retMsg;
			}
			
		}else{
			retMsg.put("msg", "订单明细为空");
			return retMsg;
		}
		
		/*if(orderModel.getLogLogisticCompanyId()<1){
			retMsg.put("msg", "快递方式为空");
			return retMsg;
		}*/
		retMsg.put("code", 0);
		return retMsg;
	}
	
	//=======================处方药的审核流程===============================
	
	@Override
	public void orderRXExamine(List<String> orderNos,int auditUserId){
		//查下订单
		List<OrderModel> orderList = orderService.queryOrders(orderNos);
		
		//保存需要更新的订单
		List<OrderModel> changeList = new ArrayList<OrderModel>();
		List<OrderLogModel> errorLogList = new ArrayList<OrderLogModel>();
		
		if(null!=orderList&&orderList.size()>0){
			String reason = "";
			OrderModel changeOrder = null;
			OrderLogModel orderLog = null;//错误日志
			for(OrderModel orderModel : orderList){
				
				changeOrder = new OrderModel();
				changeOrder.setOrderNo(orderModel.getOrderNo());
				changeOrder.setAuditTime(sdf.format(new Date()));
				changeOrder.setAuditUserId(auditUserId);
				//审核校验
				reason = this.dealExamine(orderModel);
				if(StringUtil.isBlank(reason)){//订单审核通过
					changeOrder.setOrderStatus(OrderState.STATE_2.getCode());
					//计算结算的总价格
					List<OrderItemModel> itemModel = orderService.queryOrderItems(orderModel.getOrderNo());
					if(itemModel!=null&&itemModel.size()>0){
						BigDecimal temp = BigDecimal.ZERO;
						for(OrderItemModel item : itemModel){
							if(null!=item.getSupplyPrice()){
								temp = MathUtil.add(temp, MathUtil.mul(item.getSupplyPrice(), new BigDecimal(item.getAmount())));
							}
						}
						changeOrder.setSupplyPrice(temp);
					}
				}else{//订单审核不通过
					changeOrder.setOrderStatus(OrderState.STATE_3.getCode());
					//记录不通过的原因
					orderLog = new OrderLogModel();
					orderLog.setOrderNo(orderModel.getOrderNo());
					orderLog.setOrderStateId(OrderState.STATE_1.getCode());//待审核
					orderLog.setLogContent(reason);
					orderLog.setLogTime(sdf.format(new Date()));
					orderLog.setAddTime(orderLog.getLogTime());
					errorLogList.add(orderLog);
				}
				changeList.add(changeOrder);
			}
		}
		//更新数据
		if(null!=changeList&&changeList.size()>0){
			this.saveData(changeList,errorLogList);
		}
	}
	
	
	@Override
	public void drugExamineUp(){
		
	}
	
}
