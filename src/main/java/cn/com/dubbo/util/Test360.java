package cn.com.dubbo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.galaxy.pop.api.client.ApiException;
import com.galaxy.pop.api.client.DefaultPopApiClient;
import com.galaxy.pop.api.client.PopApiClient;
import com.galaxy.pop.api.client.request.OfflineSendRequest;
import com.galaxy.pop.api.client.response.OfflineSendResponse;

import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.mapper.OrderLogMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;

public class Test360 {
	@Resource
	private static OrderLogMapper orderLogMapper;
	@Resource
	private static OrderPackageMapper orderPackageMapper;
	@Autowired
	private static OrderMapper orderMapper;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OrderPackage temp_OrderPackage=null;
		Boolean success=deliveryInterface(temp_OrderPackage);
	}
	private static Boolean deliveryInterface(OrderPackage temp_OrderPackage){
		Boolean isSuccess=false;
		try {
			String temp_addTime=sdf.format(new Date());//添加时间
			PopApiClient client = new DefaultPopApiClient(Constant.URL360,  Constant.key360,Constant.secretkey360);
			OfflineSendRequest request = new OfflineSendRequest();
			request.setTid("A2142631125218784101");
			request.setLogisticsNo("3311985871042");//运单号.具体一个物流公司的真实运单号码
			request.setVenderId("241");//国泰永康的商家编码
			request.setCompanyCode("shentong");//shentong  shentong1
			OfflineSendResponse response = client.execute(request);
			String errorCode=response.getErrorCode();
			if (errorCode.equalsIgnoreCase("REPEAT_DELIVERY")) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>YYYYYYYYYYYYYYYYYYY");
				return true;
			}
			isSuccess=response.isSuccess();
			if(response.isSuccess()){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>NNNNNNNNNNNNNNNNNNNNNNNNNN");
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

}
