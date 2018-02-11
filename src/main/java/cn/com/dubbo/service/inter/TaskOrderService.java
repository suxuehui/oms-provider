package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.AllocationTask;
import cn.com.dubbo.model.AllocationTaskOrder;

/**
 * 处方药审批流程保存批次信息
 *
 */
public interface TaskOrderService {
	
	//保存批次信息
	public void saveAllocationTask(AllocationTask task);
	
	//批次明细信息保存
	public void saveTaskOrder(AllocationTaskOrder taskOrder);
	
	//批次明细信息批量保存
	public void bataSaveTaskOrder(List<AllocationTaskOrder> taskOrders);

	public boolean isOrderByNo(String orderNo);
	
}