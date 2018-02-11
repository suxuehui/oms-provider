package cn.com.dubbo.service.inter;

import java.util.Map;


/**
 * 订单补发service
 */
public interface OrderReissuedService {
	
	//添加补发订单
	public Map<String,Object> addOrder(String orders);
	
	public Map<String,Object> examine(String orders);
		
}