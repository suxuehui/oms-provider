package cn.com.dubbo.action;

import cn.com.dubbo.constant.Constant;

import com.galaxy.pop.api.client.ApiException;
import com.galaxy.pop.api.client.DefaultPopApiClient;
import com.galaxy.pop.api.client.PopApiClient;
import com.galaxy.pop.api.client.request.TradesSoldGetRequest;
import com.galaxy.pop.api.client.response.TradesSoldGetResponse;

public class HhrTest {

	public static void main(String[] args) {
		try {
			PopApiClient client = new DefaultPopApiClient("http//api.360haoyao.com/service/check", Constant.key360,Constant.secretkey360);
		    TradesSoldGetRequest request = new TradesSoldGetRequest();
			request.setStartCreated("2016-09-08 00:00:00");
			request.setEndCreated("2016-09-08 23:59:59");
			request.setStatus("TRADE_FINISHED");
			request.setTradeFrom("PC");
			request.setHaveCFY("0");
			request.setHaveCFY("0");
			request.setPdState("ALL");
			request.setPageNo(1);
			request.setPageSize(10);
			
			request.setFields("tid,receiver_province,receiver_province_code,receiver_city,receiver_city_code,receiver_district,receiver_district_code,receiver_address");
			TradesSoldGetResponse response = client.execute(request);
			System.out.println(response);
		} catch (ApiException e) {
			e.printStackTrace();
		}catch(Exception e1 ){
			e1.printStackTrace();
		}

	}
}
