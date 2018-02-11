package cn.com.dubbo.service.order;

public interface PaOrderService {

	/**
	 * 平安抓取订单接口调用
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param flag 是否进行拆分
	 * utc时间戳：单位 秒
	 */
	public void dealOrder(String startTime,String endTime,boolean falg);

}