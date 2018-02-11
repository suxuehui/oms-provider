package cn.com.dubbo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.service.inter.OrderPackageService;


@Service
public class OrderPackageServiceImpl implements OrderPackageService{

//	private static final Logger logger = Logger.getLogger(OrderPackageServiceImpl.class);

	@Resource
	private OrderPackageMapper packageMapper;
	
	@Override
	public List<OrderPackage> getOrderPackage(String delivery_status) {
		List<OrderPackage> packageList = packageMapper.getOrderPackage(delivery_status);
		return packageList;
	}

	@Override
	public void updateOrderPackage(String order_no) {
		packageMapper.updateOrderPackage(order_no);
	}
	
	
	@Override
	public List<OrderPackage> queryPackageByChannelId(int mutilChannelId){
		List<OrderPackage> list = packageMapper.queryPackageByChannelId(mutilChannelId);
		return list;
	}
	
	@Override
	public OrderPackage findPackageByOrderNo(String orderNo){
		
		OrderPackage pack = packageMapper.findPackageByOrderNo(orderNo);
		return pack;
	}
	
	
}
