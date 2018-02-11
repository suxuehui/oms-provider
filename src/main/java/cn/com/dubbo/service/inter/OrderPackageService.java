package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.OrderPackage;

/**
 * 包裹
 */
public interface OrderPackageService {

	public List<OrderPackage> getOrderPackage(String delivery_status);

	public void updateOrderPackage(String order_no);

	/**
	 * 根据渠道id查询未同步的物流信息
	 * 
	 * @param mutilChannelId
	 * @return
	 */
	public List<OrderPackage> queryPackageByChannelId(int mutilChannelId);
	
	/**
	 * 根据渠道id查询未同步的物流信息
	 * 
	 * @param mutilChannelId
	 * @return
	 */
	public OrderPackage findPackageByOrderNo(String orderNo);
	
	

}