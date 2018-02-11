package cn.com.dubbo.service.order;

import java.util.Map;

public interface TmOrderService {
	
	public void dealOrder_TradesSoldGetResponse(String[] str,Long multiChannelOrderBatch);	
	
	
	public Map<String,Object> updateTmOrderState(int pageNo,int pageSize,String startTime,String endTime);
}