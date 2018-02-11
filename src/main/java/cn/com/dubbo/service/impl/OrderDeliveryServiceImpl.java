package cn.com.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.OrderDeliveryMapper;
import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.service.inter.OrderDeliveryService;

@Service
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

	// private static final Logger logger = Logger.getLogger(OrderDeliveryServiceImpl.class);

	@Resource
	private OrderDeliveryMapper orderDeliveryMapper;

	@Override
	public OrderDelivery queryOrderDeliveryByNo(String orderNo) {
		List<OrderDelivery> list = orderDeliveryMapper.queryOrderDeliveryByNo(orderNo);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
