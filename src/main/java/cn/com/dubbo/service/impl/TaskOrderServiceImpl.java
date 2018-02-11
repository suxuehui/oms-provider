package cn.com.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.TaskOrderMapper;
import cn.com.dubbo.model.AllocationTask;
import cn.com.dubbo.model.AllocationTaskOrder;
import cn.com.dubbo.service.inter.TaskOrderService;


@Service
public class TaskOrderServiceImpl implements TaskOrderService{

	private static final Logger logger = Logger.getLogger(TaskOrderServiceImpl.class);

	@Resource
	private TaskOrderMapper taskOrderMapper;
	
	@Override
	public void saveAllocationTask(AllocationTask task) {
		taskOrderMapper.saveAllocationTask(task);
	}

	@Override
	public void saveTaskOrder(AllocationTaskOrder taskOrder) {
		taskOrderMapper.saveTaskOrder(taskOrder);
	}

	@Override
	public void bataSaveTaskOrder(List<AllocationTaskOrder> taskOrders) {
		taskOrderMapper.bataSaveTaskOrder(taskOrders);
	}
	
	@Override
	public boolean isOrderByNo(String orderNo){
		AllocationTaskOrder taskOrder = taskOrderMapper.findTaskOrderByNo(orderNo);
		if(null!=taskOrder){
			return true;
		}
		return false;
	}
	
}
