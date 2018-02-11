package cn.com.dubbo.service.order;

/**
 * 京东相关业务
 */
public interface JDBusinessService {
	
	/**
	 * 更新物流发货
	 * @return
	 */
	public void sopEMSUpdate();
	
	/**
	 * 更新京东库存数量
	 * @param orderNo
	 * @param quantity
	 * @return
	 */
	public void stockUpdate();

}
