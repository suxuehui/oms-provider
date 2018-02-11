package cn.com.dubbo.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.AllocationTask;
import cn.com.dubbo.model.AllocationTaskOrder;

/**
 * 处方药订单审核批量执行
 */
@Repository
public interface TaskOrderMapper extends BaseMapper<AllocationTask, Integer>{
	
	//保存批次信息
	public void saveAllocationTask(AllocationTask task);
	
	//批次明细信息保存
	public void saveTaskOrder(AllocationTaskOrder taskOrder);
	
	//批次明细信息批量保存
	public void bataSaveTaskOrder(List<AllocationTaskOrder> taskOrders);
	
	//查询订单
	public AllocationTaskOrder findTaskOrderByNo(String orderNo);
	
}
