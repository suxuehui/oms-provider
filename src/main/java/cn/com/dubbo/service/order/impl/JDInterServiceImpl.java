package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.service.order.JDInterService;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.domain.delivery.LogisticsCompanies;
import com.jd.open.api.sdk.domain.delivery.LogisticsCompany;
import com.jd.open.api.sdk.request.delivery.DeliveryLogisticsGetRequest;
import com.jd.open.api.sdk.request.order.OrderSopOutstorageRequest;
import com.jd.open.api.sdk.request.ware.WareSkuStockUpdateRequest;
import com.jd.open.api.sdk.response.delivery.DeliveryLogisticsGetResponse;
import com.jd.open.api.sdk.response.order.OrderSopOutstorageResponse;
import com.jd.open.api.sdk.response.ware.WareSkuStockUpdateResponse;

@Service
public class JDInterServiceImpl implements JDInterService {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger logger = Logger.getLogger(JDInterServiceImpl.class);
	
	public JdClient client = new DefaultJdClient(ThirdConstant.SERVER_URL,
			ThirdConstant.accessToken,ThirdConstant.appKey,ThirdConstant.appSecret);
	
	public static void main(String[] args) {
		JDInterServiceImpl impl = new JDInterServiceImpl();
//		impl.stockUpdate("ddd","20");
	}
	
	
	@Override
	public void logisticsGet() {
		DeliveryLogisticsGetResponse response = null;
		try {
			DeliveryLogisticsGetRequest request = new DeliveryLogisticsGetRequest();
			request.setOptionalFields("");
			response = client.execute(request);
			String code = response.getCode();
			if("0".equals(code)){
				LogisticsCompanies compans = response.getLogisticsCompanies();
				if(null!=compans){
					List<LogisticsCompany> list = compans.getLogisticsList();
					for(LogisticsCompany logis : list){
						System.out.println(logis.getLogisticsId()+","+logis.getLogisticsName()+","+logis.getAgreeFlag()+","+logis.getLogisticsRemark());
					}
				}
			}else{
				//异常
				
			}
			
		} catch (Exception e) {
			
		}
		

	}
	
	@Override
	public String stockUpdate(String goodsno,String quantity){
		WareSkuStockUpdateResponse response = null;
		try {
			WareSkuStockUpdateRequest request=new WareSkuStockUpdateRequest();
			request.setOuterId(goodsno);
			//需要更新的库存数量  
			request.setQuantity(quantity);
			//流水号
			response = client.execute(request);
			String code = response.getCode();
			if("0".equals(code)){
				return "0";
			}else{
				//异常
				logger.error("更新京东商品库存接口返回信息：skuId:"+goodsno+" ,失败信息"+response.getMsg());
				return response.getMsg();
			}
		} catch (Exception e) {
			logger.error("更新京东商品库存接口失败："+e.getMessage(),e);
			return response.getCode()+response.getMsg();
		}
	}
	

	/**
	 * 京东发货
	 * @param orderNo 单号
	 * @param waybill 运单号
	 * @param logisticsId 物流公司Id
	 */
	@Override
	public String jdSopEMS(String orderNo,String waybill,String logisticsId){
		OrderSopOutstorageResponse response = null;
		try {
			OrderSopOutstorageRequest outStorage = new OrderSopOutstorageRequest();
			/**
			 * 物流公司ID(只可通过获取商家物流公司接口获得),多个物流公司以|分隔  
			 */
			outStorage.setLogisticsId(logisticsId);
			outStorage.setOrderId(orderNo);
			outStorage.setTimestamp(sdf.format(new Date()));
			/**
			 * 运单号(当厂家自送时运单号可为空，不同物流公司的运单号用|分隔，如果同一物流公司有多个运单号，则用英文逗号分隔)
			 */
			outStorage.setWaybill(waybill);
			
			response = client.execute(outStorage);
			System.out.println(response.getCode()+response.getMsg());
			String code = response.getCode();
			if("0".equals(code)){
				return "0";
			}else{
				//异常
				return response.getMsg();
			}
		} catch (Exception e) {
			logger.error("京东推送物流信息失败,订单号："+orderNo+" ,原因："+e.getMessage(),e);
			return response.getCode()+response.getMsg();
		}
	}
	
}
