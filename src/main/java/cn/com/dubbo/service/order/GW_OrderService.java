package cn.com.dubbo.service.order;


//从GW同步订单
public interface GW_OrderService {
	
	public String getGwOrderTrades(String[] str,Long pageIndex,String orderStatus);	
	
	
}