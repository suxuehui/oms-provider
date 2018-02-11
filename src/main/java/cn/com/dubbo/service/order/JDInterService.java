package cn.com.dubbo.service.order;

/**
 * 京东相关接口服务
 */
public interface JDInterService {
	
	/*
	 * 获取物流公司接口
	 */
	public void logisticsGet();
	
	/**
	 * 更新物流发货
	 * @param orderNo 订单号
	 * @param waybill 运单号
	 * @param logisticsId 物流公司Id
	 * @return
	 */
	public String jdSopEMS(String orderNo,String waybill,String logisticsId);
	
	/**
	 * 更新京东库存数量
	 * @param orderNo
	 * @param quantity
	 * @return
	 */
	public String stockUpdate(String goodsno,String quantity);

}
