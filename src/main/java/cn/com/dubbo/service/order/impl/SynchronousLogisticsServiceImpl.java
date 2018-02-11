package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.service.inter.OrderPackageService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.SynchronousLogisticsService;
import cn.com.dubbo.util.HttpClientUtils;

/**
 * 向微信和官网同步物流信息
 * @author hhr
 */
@Service
public class SynchronousLogisticsServiceImpl implements SynchronousLogisticsService {
	
	private static final Logger logger = Logger.getLogger(SynchronousLogisticsServiceImpl.class);

	private   final String GW_URL = BaseData.GW_URL;
	
	private   final String WX_URL = BaseData.WX_URL;
	
	@Resource
	private OrderPackageService packageService;
	
	@Resource
	private OrderService orderService;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//主要的执行方法
	public void orderSendLogistics(String multiChannel){
		
		try {
			//查询数据参数(微信)
//			if(multiChannel.equals(ChannelConstant.CHANNEL_WX)){
//				List<OrderPackage> packageList = packageService.queryPackageByChannelId(6);
//				if(null!=packageList&&packageList.size()>0){
//					int i=0;
//					List<OrderLogModel> errorLog = new ArrayList<OrderLogModel>();
//					for (OrderPackage pa : packageList) {
//						this.sendLogisticsInfoWX(pa,errorLog);
//						logger.info("向微信同步物流信息，一共："+ packageList.size()+" 条,正在同步第："+i+" 条");
//						i++;
//					}
//				}
//				
//			}
			//查询数据参数(官网)
			if(multiChannel.equals(ChannelConstant.CHANNEL_GW)){
				List<OrderPackage> packageList = packageService.queryPackageByChannelId(7);
				if(null!=packageList&&packageList.size()>0){
					int i=0;
					List<OrderLogModel> errorLog = new ArrayList<OrderLogModel>();
					for (OrderPackage pa : packageList) {
						this.sendLogisticsInfoGW(pa,errorLog);
						logger.info("向微信同步物流信息，一共："+ packageList.size()+" 条,正在同步第："+i+" 条");
						i++;
					}
				}
				
			}
		} catch (Exception e) {
			logger.error("抓取订单后，拆分组合类型的订单明细，补充相关的信息错误： " + e.getMessage(),e);
		}
	}
	
	
	

	/**********************************************微信部分**************************************************************************
	 * 微信
	 * @author hhr
	 * @param orderPackage
	 * @param errorLog
	 */
	@Transactional
	private void sendLogisticsInfoWX(OrderPackage orderPackage,List<OrderLogModel> errorLog){
		try {
			if(null!=orderPackage){
				
				Integer logLogisticCompanyId = Integer.valueOf(orderPackage.getLogistic_company_no());
				String logLogisticCompanyName= orderPackage.getLogistic_company_name();
				String trackingNumber = orderPackage.getLogistic_no();
				String receiveTime = orderPackage.getDelivery_time();
				
				String retMsg = null;
				if(orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_WX)){
					String order_no = orderPackage.getOrder_no().replace(ChannelConstant.CHANNEL_WX, "");
					retMsg = this.updateLogisticsWX(logLogisticCompanyId, logLogisticCompanyName, trackingNumber, receiveTime, order_no);
				}else if(orderPackage.getOrder_no().contains("BF")){//补发单，不需要调用
					retMsg="0";
				}else if(orderPackage.getOrder_no().contains("TH")){
					String order_no = orderPackage.getOrder_no().replace("TH", "");
					retMsg = this.updateLogisticsWX(logLogisticCompanyId, logLogisticCompanyName, trackingNumber, receiveTime, order_no);
				}else{
					String order_no =orderPackage.getOrder_no();
					retMsg = this.updateLogisticsWX(logLogisticCompanyId, logLogisticCompanyName, trackingNumber, receiveTime, order_no);
				}
				
				if("0".equals(retMsg)){
					//更新订单状态
					OrderModel orderModel = new OrderModel();
					if(orderPackage.getOrder_no().startsWith("BF")){
						orderModel.setOrderNo(orderPackage.getOrder_no());
					}else if(orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_WX)){
						orderModel.setOrderNo(orderPackage.getOrder_no());
					}else{
						orderModel.setOrderNo(ChannelConstant.CHANNEL_WX+orderPackage.getOrder_no());
					}
					orderModel.setOrderStatus(OrderState.STATE_6.getCode());
					orderModel.setReceiveTime(new Date());
					orderModel.setLogLogisticCompanyId(orderPackage.getLogistic_company_id());
					orderService.updateOrder(orderModel);
					
					//更新商品状态
					OrderItemModel itemModel = new OrderItemModel();
					itemModel.setOrderNo(orderModel.getOrderNo());
					itemModel.setGoodsStatus(OrderState.STATE_6.getCode());
					orderService.updateOrderItemByOrderNo(itemModel);
					
					//更新物流状态
					packageService.updateOrderPackage(orderPackage.getOrder_no());
					
				}else{
					//记录错误日志
					OrderLogModel orderLog = new OrderLogModel();
					orderLog.setOrderNo(ChannelConstant.CHANNEL_WX+orderPackage.getOrder_no());
					orderLog.setOrderStateId(OrderState.STATE_6.getCode());//待审核
					orderLog.setLogContent(retMsg);
					orderLog.setLogTime(sdf.format(new Date()));
					orderLog.setAddTime(orderLog.getLogTime());
					errorLog.add(orderLog);
				}
			}
		} catch (Exception e) {
			logger.error("确认发货，同步给平安失败,订单号："+orderPackage.getOrder_no()+" ,错误信息："+e.getMessage(),e);
		}
		
	}
	
	/**
	 * 同步物流到微信平台 
	 * @param tradeId
	 * @param carrierId
	 * @param trackingNumber
	 * @param operator
	 * @return
	 */
	public String updateLogisticsWX(Integer logLogisticCompanyId,String logLogisticCompanyName,String trackingNumber,String receiveTime,String order_no){
		try {
			
			String pamaras = "logLogisticCompanyId="+logLogisticCompanyId+"&logLogisticCompanyName="+logLogisticCompanyName+"" +
					"&trackingNumber="+trackingNumber+"&receiveTime="+receiveTime+"&order_no="+order_no;
			String text = HttpClientUtils.sendPost(WX_URL, pamaras);
			// 解析返回值(此处使用FASTJSON 1.2.4解析JSON字符串，也可使用其他JSON解析类库)
			Map obj = JSONObject.fromObject(text);
			String objectStr = obj.get("result").toString();
//			Integer code =  (Integer)obj.get("code");
			//解码返回结果 
			if("success".equals(objectStr)){
			   return "0";
			}else{
				return "调用微信接口失败："+objectStr;
			}
		} catch (Exception e) {
			logger.error("同步物流给微信报错，订单号："+order_no+" ,异常"+e.getMessage(),e);
		}
		return "系统异常";
	}
	
	
	/********************************************************官网部分****************************************************************************
	 * 同步物流到官网
	 * @param orderPackage
	 * @param errorLog
	 */
	private void sendLogisticsInfoGW(OrderPackage orderPackage,List<OrderLogModel> errorLog) {
		try {
			if(null!=orderPackage){
				
				Integer logistic_company_id = Integer.valueOf(orderPackage.getLogistic_company_no());
				String logistic_company_name= orderPackage.getLogistic_company_name();
				String logistic_no = orderPackage.getLogistic_no();
				String delivery_time = orderPackage.getDelivery_time();
				Integer stock_id = orderPackage.getStock_id();
				Integer package_id = orderPackage.getPackage_id();
				String delivery_status = orderPackage.getDelivery_status();
				String logistic_company_no = orderPackage.getLogistic_company_no();
				String package_no = orderPackage.getPackage_no();
				
				String retMsg = null;
				if(orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_GW)){
					String order_no =orderPackage.getOrder_no().replace(ChannelConstant.CHANNEL_GW, "");
					retMsg = this.updateLogisticsGW(order_no, package_id, stock_id, package_no, logistic_company_id, logistic_company_name, logistic_company_no, logistic_no, delivery_status, delivery_time);
				}else if(orderPackage.getOrder_no().contains("BF")){//补发单，不需要调用
					retMsg="0";
				}else if(orderPackage.getOrder_no().contains("TH")){
					String order_no =  orderPackage.getOrder_no().replace("TH", "");
					retMsg = this.updateLogisticsGW(order_no, package_id, stock_id, package_no, logistic_company_id, logistic_company_name, logistic_company_no, logistic_no, delivery_status, delivery_time);
				}else{
					String order_no =  (orderPackage.getOrder_no());
					retMsg = this.updateLogisticsGW(order_no, package_id, stock_id, package_no, logistic_company_id, logistic_company_name, logistic_company_no, logistic_no, delivery_status, delivery_time);
				}
				
				if("0".equals(retMsg)){
					//更新订单状态
					OrderModel orderModel = new OrderModel();
					if(orderPackage.getOrder_no().startsWith("BF")){
						orderModel.setOrderNo(orderPackage.getOrder_no());
					}else if(orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_GW)){
						orderModel.setOrderNo(orderPackage.getOrder_no());
					}else{
						orderModel.setOrderNo(ChannelConstant.CHANNEL_GW+orderPackage.getOrder_no());
					}
					orderModel.setOrderStatus(OrderState.STATE_6.getCode());
					orderModel.setReceiveTime(new Date());
					orderModel.setLogLogisticCompanyId(orderPackage.getLogistic_company_id());
					orderService.updateOrder(orderModel);
					
					//更新商品状态
					OrderItemModel itemModel = new OrderItemModel();
					itemModel.setOrderNo(orderModel.getOrderNo());
					itemModel.setGoodsStatus(OrderState.STATE_6.getCode());
					orderService.updateOrderItemByOrderNo(itemModel);
					
					//更新物流状态
					packageService.updateOrderPackage(orderPackage.getOrder_no());
					
				}else{
					//记录错误日志
					OrderLogModel orderLog = new OrderLogModel();
					orderLog.setOrderNo(ChannelConstant.CHANNEL_GW+orderPackage.getOrder_no());
					orderLog.setOrderStateId(OrderState.STATE_6.getCode());//待审核
					orderLog.setLogContent(retMsg);
					orderLog.setLogTime(sdf.format(new Date()));
					orderLog.setAddTime(orderLog.getLogTime());
					errorLog.add(orderLog);
				}
			}
		} catch (Exception e) {
			logger.error("确认发货，同步给平安失败,订单号："+orderPackage.getOrder_no()+" ,错误信息："+e.getMessage(),e);
		}
		
	}


 
	//logLogisticCompanyId, logLogisticCompanyName, trackingNumber, receiveTime, order_no
	private String updateLogisticsGW(String order_no, Integer package_id,Integer stock_id, String package_no,Integer logistic_company_id,
			String logistic_company_name,String logistic_company_no,String 	logistic_no,String delivery_status,String delivery_time) {
		try {
			String pamaras = "orderNo="+order_no+"&package_id="+package_id+"&stock_id="+stock_id+"&package_no="+package_no
					         +"&logistic_company_id="+logistic_company_id+"&logistic_company_name="+logistic_company_name+"&logistic_company_no="+logistic_company_no+"&logistic_no="+logistic_no
					         +"&delivery_status="+delivery_status+"&delivery_time="+delivery_time;
			String text = HttpClientUtils.do_post(GW_URL, pamaras);
			// 解析返回值(此处使用FASTJSON 1.2.4解析JSON字符串，也可使用其他JSON解析类库)
			Map obj = JSONObject.fromObject(text);
			String objectStr = obj.get("code").toString();
//			Integer code =  (Integer)obj.get("code");
			//解码返回结果 
			if("0".equals(objectStr)){
			   return "0";
			}else{
				return "调用微信接口失败："+objectStr;
			}
		} catch (Exception e) {
			logger.error("同步物流给微信报错，订单号 orderNo："+order_no+" ,异常"+e.getMessage(),e);
		}
		return "系统异常";
	}
	
	
}
