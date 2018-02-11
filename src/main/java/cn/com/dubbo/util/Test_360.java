package cn.com.dubbo.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.com.dubbo.constant.Constant;

import com.galaxy.pop.api.client.ApiException;
import com.galaxy.pop.api.client.DefaultPopApiClient;
import com.galaxy.pop.api.client.PopApiClient;
import com.galaxy.pop.api.client.model.Trade;
import com.galaxy.pop.api.client.model.TradeDetail;
import com.galaxy.pop.api.client.request.TradesSoldGetRequest;
import com.galaxy.pop.api.client.request.TradesSoldIncrementGetRequest;
import com.galaxy.pop.api.client.response.TradesSoldGetResponse;

public class Test_360 {

	/**
	 * 查询卖家交易数据,按照页码查询
	 * @param pageNo
	 * @return    
	 */
	public static TradesSoldGetResponse getTradesSoldGetResponse(int pageNo,int pageSize,String[] str) {
		// key: D0000293   测试密钥: 6F5559D4CB82833323A235DF24F0B6E75E725DD20EF5D1B839A9C677D01F9120    13FC726AF3C77D5A5DC7D7E0682C1623EB10F8597559CC0104A4CE90BCF4F149
		PopApiClient client = new DefaultPopApiClient(Constant.URL360,  Constant.key360,Constant.secretkey360);
//		start_create;
//		end_create;
		TradesSoldGetRequest request = new TradesSoldGetRequest();
		
//		request.setStartCreated("2016-07-07 09:00:00");
//		request.setEndCreated("2016-07-07 10:49:00");
		request.setStartCreated(str[0]);
		request.setEndCreated(str[1]);
		
/*	    TradesSoldIncrementGetRequest request = new TradesSoldIncrementGetRequest();
		request.setStartModified("2016-06-07 11:50:00");
		request.setEndModified("2016-06-07 13:30:00");*/
		
		request.setHaveCFY("1");//是否包含处方药(1-包含,0-普通,””和null-普通订单)   haveCFY  request.setHaveCFY("0"):只查询不包含处方药的；request.setHaveCFY("1")：包含和不包含处方药的都查询
		request.setVenderId("241");//商家编号(不能为空)
		request.setPdState("ALL");
		request.setPageNo(pageNo);
		request.setPageSize(pageSize);
		
		//pay_time,end_time,delivery_time,logistics_no,total_fee,discount_fee,merchants_receivable_amount,refund_id,payment
		request.setFields("tid,create_time,receiver_province,receiver_province_code,receiver_city,receiver_city_code,receiver_district,receiver_district_code,receiver_address,buyer_nick,receiver_zip,receiver_mobile" +
				",receiver_name,receiver_phone,logistics_company_code,logistics_company,modify_time,status,haveCFY,invoice,payment_type,post_fee,trade_details,vender_remark,customer_remark,trade_from" +
				",pay_time,end_time,delivery_time,logistics_no,total_fee,discount_fee,merchants_receivable_amount,refund_id,payment");
//		request.check();
		TradesSoldGetResponse response=null;
		try {
			response = client.execute(request);
		} catch (ApiException e){
			e.printStackTrace();
		}catch(Exception e1 ){
			e1.printStackTrace();
		}
//		System.out.println(response.getBody().toString());
		return response;
	}	
	
	/**
	 * 增量查询卖家交易,按照页码查询
	 * @param pageNo
	 * @return    
	 */
	public TradesSoldGetResponse getTradesSoldIncrementGetResponse(int pageNo,int pageSize,String[] str) {
		// key: D0000293   测试密钥: 6F5559D4CB82833323A235DF24F0B6E75E725DD20EF5D1B839A9C677D01F9120    13FC726AF3C77D5A5DC7D7E0682C1623EB10F8597559CC0104A4CE90BCF4F149
		PopApiClient client = new DefaultPopApiClient(Constant.URL360,  Constant.key360,Constant.secretkey360);
//		start_create;
//		end_create;
/*		TradesSoldGetRequest request = new TradesSoldGetRequest();
		request.setStartCreated("2016-06-05 11:50:00");
		request.setEndCreated("2016-06-05 13:30:00");*/
		
	    TradesSoldIncrementGetRequest request = new TradesSoldIncrementGetRequest();
//		request.setStartModified("2016-06-07 11:50:00");
//		request.setEndModified("2016-06-07 13:30:00");
		request.setStartModified(str[0]);
		request.setEndModified(str[1]);
		
		request.setHaveCFY("1");
		request.setVenderId("241");
		request.setPdState("ALL");
		request.setPageNo(pageNo);
		request.setPageSize(pageSize);
		
		//pay_time  end_time   delivery_time  logistics_no  total_fee  discount_fee  merchants_receivable_amount  refund_id  payment
		request.setFields("tid,create_time,receiver_province,receiver_province_code,receiver_city,receiver_city_code,receiver_district,receiver_district_code,receiver_address,buyer_nick,receiver_zip,receiver_mobile" +
				",receiver_name,receiver_phone,logistics_company_code,logistics_company,modify_time,status,haveCFY,invoice,payment_type,post_fee,trade_details,vender_remark,customer_remark,trade_from" +
				",pay_time,end_time,delivery_time,logistics_no,total_fee,discount_fee,merchants_receivable_amount,refund_id,payment");
//		request.check();
		TradesSoldGetResponse response=null;
		try {
			response = client.execute(request);
		} catch (ApiException e){
			e.printStackTrace();
		}catch(Exception e1 ){
			e1.printStackTrace();
		}
//		System.out.println(response.getBody().toString());
		return response;
	}	
	
	
	/**
	 * @param args  
	 */
	public static void main(String[] args) {
		String[] str=new String[2];
		str[0]="2016-07-12 15:20:03";
		str[1]="2016-07-12 18:42:00";//18:43
		
		TradesSoldGetResponse tradesSoldGetResponse=getTradesSoldGetResponse(1,40,str);
//		TradesSoldGetResponse tradesSoldGetResponse66=getTradesSoldGetResponse(1,40,str);
		System.out.println(tradesSoldGetResponse.getBody().toString());
//		System.out.println(tradesSoldGetResponse66.getBody().toString());
	}
}
