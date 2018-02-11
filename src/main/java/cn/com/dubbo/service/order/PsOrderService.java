package cn.com.dubbo.service.order;

/**
 * 平安商城
 */
public interface PsOrderService {

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param flag 
	 * utc时间戳：单位 秒
	 */
	public void dealOrder(String startTime,String endTime,boolean flag);

}