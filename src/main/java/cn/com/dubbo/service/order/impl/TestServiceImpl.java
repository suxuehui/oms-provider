package cn.com.dubbo.service.order.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.dubbo.bean.ext.Order_info666;
import cn.com.dubbo.bean.ext.Order_item666;
import cn.com.dubbo.mapper.ext.Order_info666Mapper;
import cn.com.dubbo.mapper.ext.Order_item666Mapper;
import cn.com.dubbo.service.order.TestService;

public class TestServiceImpl implements TestService {
	@Autowired
	Order_info666Mapper order_info666Mapper;
	@Autowired
	Order_item666Mapper Order_item666Mapper;

	@Override
	public void get() {
		try {
			Order_item666 order_item666 = Order_item666Mapper.selectByPrimaryKey(27524239L);
			List<Order_info666> order_info666s = order_info666Mapper.getList();
			for (int i = 0; i < order_info666s.size(); i++) {
				Order_info666 info = order_info666s.get(i);
				Order_item666 order_item = new Order_item666();
				BeanUtils.copyProperties(order_item666, order_item);
				order_item.setOrderNo(order_info666s.get(i).getOrderNo());
				Order_item666Mapper.insertSelective(order_item);
				info.setOrderStatus(2);
				order_info666Mapper.updateByPrimaryKeySelective(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
