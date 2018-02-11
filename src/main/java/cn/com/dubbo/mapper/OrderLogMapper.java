package cn.com.dubbo.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.OrderLogModel;

@Repository
public interface OrderLogMapper extends BaseMapper<OrderLogModel, Integer>{

	public void saveOrderLog(OrderLogModel orderLog);
	
	public void saveBatchOrderLog(List<OrderLogModel> logList);
}
