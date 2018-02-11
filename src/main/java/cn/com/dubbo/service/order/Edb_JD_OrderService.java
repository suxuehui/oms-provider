package cn.com.dubbo.service.order;


//从e店宝同步JD的订单
public interface Edb_JD_OrderService {
	
	public void dealOrder_TradesSoldGetResponse(String[] str,Long multiChannelOrderBatch);	
	
	
//	public Map<String,Object> updateJDOrderState(int pageNo,int pageSize,String startTime,String endTime);
}