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

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.constant.PaResultCode;
import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.service.inter.OrderLogService;
import cn.com.dubbo.service.inter.OrderPackageService;
import cn.com.dubbo.service.order.EMSUpdateService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.HttpClientUtils;

import com.pajk.openapi.codec.client.RequestEncoder;
import com.pajk.openapi.codec.client.RequestEntity;
import com.pajk.openapi.codec.client.ResponseDecoder;

@Service
public class EMSUpdateServiceImpl implements EMSUpdateService {

	
	//平安正式环境的物流apiId
	private static String apiId_pa = "9d923900e2c909c387efb586090441ad#PROD";
	
	private static final Logger logger = Logger.getLogger(EMSUpdateServiceImpl.class);
	
	@Resource
	private OrderPackageService packageService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderLogService orderLogService;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void orderSendEMS(String multiChannel){
		
		try {
			//查询数据参数
			if(multiChannel.equals(ChannelConstant.CHANNEL_PA)){
				List<OrderPackage> packageList = packageService.queryPackageByChannelId(4);
				if(null!=packageList&&packageList.size()>0){
					int i=0;
					List<OrderLogModel> errorLog = new ArrayList<OrderLogModel>();
					for (OrderPackage pa : packageList) {
						this.sendEmsInfo(pa,errorLog);
						if(i%30==0){
							logger.info("向平安同步物流信息，一共："+ packageList.size()+" 条,正在同步第："+i+" 条");
						}
						i++;
					}
				}
				
			}
		} catch (Exception e) {
			logger.error("抓取订单后，拆分组合类型的订单明细，补充相关的信息错误： " + e.getMessage(),e);
		}
	}
	
	/**
	 * 确认发货/上传物流公司信息
	 */
	public static void main(String[] args) {
		EMSUpdateServiceImpl impl = new EMSUpdateServiceImpl();
		impl.updateEMS("466856710002", "2000122220", "3310342009714", "geng");
	}
	
	@Transactional
	private void sendEmsInfo(OrderPackage orderPackage,List<OrderLogModel> errorLog){
		try {
			if(null!=orderPackage){
				
				String retMsg = null;
				if(orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_PA)){
					retMsg = this.updateEMS(orderPackage.getOrder_no().replace(ChannelConstant.CHANNEL_PA, ""),orderPackage.getLogistic_company_no()
							,orderPackage.getLogistic_no(), 
							 "sys");
				}else if(orderPackage.getOrder_no().contains("BF")){//补发单，不需要调用平安接口
					retMsg="0";
				}else if(orderPackage.getOrder_no().contains("TH")){
					retMsg = this.updateEMS(orderPackage.getOrder_no().replace("TH", ""),orderPackage.getLogistic_company_no()
							,orderPackage.getLogistic_no(), 
							 "sys");
				}else{
					retMsg = this.updateEMS(orderPackage.getOrder_no(),orderPackage.getLogistic_company_no()
							,orderPackage.getLogistic_no(), 
							 "sys");
				}
				
				if("0".equals(retMsg)){
					//更新订单状态
					OrderModel orderModel = new OrderModel();
					if(orderPackage.getOrder_no().startsWith("BF")||orderPackage.getOrder_no().startsWith("TH")
							||orderPackage.getOrder_no().startsWith(ChannelConstant.CHANNEL_PA)){
						orderModel.setOrderNo(orderPackage.getOrder_no());
					}else{
						orderModel.setOrderNo(ChannelConstant.CHANNEL_PA+orderPackage.getOrder_no());
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
					logger.error("确认发货，同步给平安商城失败,订单号："+orderPackage.getOrder_no()+" ,错误信息："+retMsg);
					//记录错误日志
					OrderLogModel orderLog = new OrderLogModel();
					orderLog.setOrderNo(ChannelConstant.CHANNEL_PA+orderPackage.getOrder_no());
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
	 * 确认发货/上传物流公司信息
	 * @param tradeId 订单ID
	 * @param carrierId 物流公司唯一识别id
	 * @param trackingNumber 快递单号
	 * @param operator 操作员
	 * @return
	 */
	public String updateEMS(String tradeId,
			String carrierId,String trackingNumber,String operator){
		
		try {
			RequestEncoder encoder = new RequestEncoder(ThirdConstant.partnerId_pa, ThirdConstant.key_pa, apiId_pa);
			//string 类型参数
			String arg1 = ThirdConstant.sellerId_pa;//卖家id
			
			String arg2 = tradeId;//订单ID
			String arg3 = carrierId;//物流公司唯一标识
			String arg4 = trackingNumber;//快递单号
			String arg5 = operator;//操作员
			//按接口定义的参数顺序放入参数
			encoder.addParameter(arg1);
			encoder.addParameter(arg2);
			encoder.addParameter(arg3);
			encoder.addParameter(arg4);
			encoder.addParameter(arg5);
			
			//进行加密
			RequestEntity e = encoder.encode();
			String apiName = "updateEMS";
			//拼装url
			String url = ThirdConstant.baseUrl_pa + ThirdConstant.apiGroup_pa +"/"+ apiName +"?";
			String postURL = url + e.getQueryParams();
//			System.out.println("url: "+postURL);
			String postData = e.getFormParams();

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
				/**
				 * 不支持重复更新物流公司,只支持更新物流单号
				 * object: {"resultMsg":"查询物流公司失败","model":null,"errorCode":{"desc":"查询物流公司失败","code":309},"resultCode":309,"success":false}
				 */
				int resultCode = object.getInt("resultCode");
				if(0==resultCode){//返回成功
					return "0";
				}else{
					logger.error("错误码："+resultCode + ",错误信息："+ PaResultCode.getName(resultCode)+",订单号PA"+tradeId);
					if(resultCode==PaResultCode.ORDER_OTHER_1.getCode()){
						return "0";
					}
					return resultCode + " : "+PaResultCode.getName(resultCode);
				}
			}else{
				return "调用平安接口失败："+objectStr;
			}
		} catch (Exception e) {
			logger.error("同步物流给平安报错，订单号："+tradeId+" ,异常"+e.getMessage(),e);
		}
		return "系统异常";
	}
	
	
}
