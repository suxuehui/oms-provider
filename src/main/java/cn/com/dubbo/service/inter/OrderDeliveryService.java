package cn.com.dubbo.service.inter;

import cn.com.dubbo.model.OrderDelivery;

/**
 * 
 */
public interface OrderDeliveryService {

	// 查询
	public OrderDelivery queryOrderDeliveryByNo(String orderNo);

}