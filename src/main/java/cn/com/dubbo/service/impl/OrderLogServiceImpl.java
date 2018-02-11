package cn.com.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.OrderLogMapper;
import cn.com.dubbo.model.OrderLogModel;
import cn.com.dubbo.service.inter.OrderLogService;
import cn.com.dubbo.util.DataQueue;


@Service
public class OrderLogServiceImpl implements OrderLogService{

	private static final Logger logger = Logger.getLogger(OrderLogServiceImpl.class);
	
	@Resource
	private OrderLogMapper orderLogMapper;
	
	@Override
	public void saveOrderLog(OrderLogModel log){
		orderLogMapper.saveOrderLog(log);
	}
	
	@Override
	public void saveBatchOrderLog(List<OrderLogModel> logList,DataQueue<OrderLogModel> logQueue){
		
		try {
			if(null!=logList&&logList.size()>0){
				orderLogMapper.saveBatchOrderLog(logList);
			}
		} catch (Exception e) {
			if(null!=logList&&logList.size()>0){
				for(OrderLogModel model : logList){
					logger.error("订单日志保存数据库错误 ，错误内容："+model.getLogContent());
					logQueue.enQueue(model);
				}
			}
			logger.error("订单日志记录批量保存数据库错误 ，错误条目："+logList.size()+" ,错误信息："+e.getMessage(),e);
		}
	}
	
}
