package cn.com.dubbo.service.order;

public interface Health360OrderService {

	public void dealOrder_TradesSoldGetResponse(String[] str,Long multiChannelOrderBatch);	
	
	public void dealOrder_TradesSoldIncrementGetResponse(String[] str,Long multiChannelOrderBatch);	
	
//	public void test6666();

	/**
	 * 商家发货接口
	 */
	public void deliveryInterface();
}