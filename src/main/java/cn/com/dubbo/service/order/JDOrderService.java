package cn.com.dubbo.service.order;

/**
 * 京东获取订单接口
 */
public interface JDOrderService {
	
	/**
	 * @param startTime 开始时间
	 * @param endTime  结束时间
	 * @param orderState 订单状态
	 */
	public void dealOrder(String startTime,String endTime,String orderState);

}
