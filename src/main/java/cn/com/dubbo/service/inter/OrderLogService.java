package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.util.DataQueue;

public interface OrderLogService {
	
	//订单日志
	public void saveOrderLog(OrderLogModel log);
		
	//批量保存订单日志
	public void saveBatchOrderLog(List<OrderLogModel> logList,DataQueue<OrderLogModel> logModel);
	
}