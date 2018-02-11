package cn.com.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.mapper.PaymentLogMapper;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.PaymentLogService;
import cn.com.dubbo.util.DataQueue;


@Service
public class PaymentLogServiceImpl implements PaymentLogService{

	private static final Logger logger = Logger.getLogger(PaymentLogServiceImpl.class);
	
	@Resource
	private PaymentLogMapper payLogMapper;
	
	@Override
	public void savePaymentLog(PaymentLogModel log){
		payLogMapper.savePaymentLog(log);
	}
	
	@Override
	public void saveBatchPayLog(List<PaymentLogModel> logList){
		payLogMapper.saveBatchPayLog(logList);;
	}
	
	@Override
	public boolean isLogByOrderNo(String bussinessId){
		int retCode = 0;
		retCode = payLogMapper.isLogByOrderNo(bussinessId);
		if(0==retCode){
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public void saveBatchPayLog(List<PaymentLogModel> logList,DataQueue<PaymentLogModel> logQueue){
		
		List<PaymentLogModel> batchList = new ArrayList<PaymentLogModel>();
		try {
			if(null!=logList&&logList.size()>0){
				
				batchList = addAddBatchOrderPayLogListTemp(logList);
				/*
				 * 最好能控制批量保存时的最大数量
				 * 批量保存
				 */
				if(null!=batchList&&batchList.size()>0){
					payLogMapper.saveBatchPayLog(batchList);
				}
			}
		} catch (Exception e) {
			if(null!=batchList&&batchList.size()>0){
				for(PaymentLogModel model : batchList){
					logger.error("订单支付日志保存数据库错误 ，错误订单号："+model.getBusinessId());
					logQueue.enQueue(model);
				}
			}
			logger.error("订单日志批量保存数据库错误 ，错误条目："+batchList.size()+" ,错误信息："+e.getMessage(),e);
		}
	}
	
	/*@Transactional
	private void addAddBatchLogList(PaymentLogModel model,List<PaymentLogModel> batchList){
		try {
			boolean bl = this.isLogByOrderNo(model.getBusinessId());
			if(!bl){
				batchList.add(model);
			}
		} catch (Exception e) {
			logger.error("校验订单支付日志是否存入数据出错，订单号："+ model.getBusinessId() +" 错误信息："+e.getMessage(),e);
		}
	}*/
	
	private List<PaymentLogModel> addAddBatchOrderPayLogListTemp(List<PaymentLogModel> orderPayLogList){
		//已存在的数据
		List<String> orderPayLogNos = this.batchQueryOrderPayLog(orderPayLogList);
		List<PaymentLogModel> tempList = new ArrayList<PaymentLogModel>();
		if(null!=orderPayLogNos && orderPayLogNos.size()>0){
			for(PaymentLogModel paylog : orderPayLogList){
				if(!orderPayLogNos.contains(paylog.getBusinessId())){
					tempList.add(paylog);
				}
			}
			return tempList;
		}
		return orderPayLogList;
	}
	
	public List<PaymentLogModel> queryLogsByOrderNo(String bussinessId){
		
		List<PaymentLogModel> list = payLogMapper.queryLogsByOrderNo(bussinessId);
		
		return list;
	}
	
	@Override
	public List<String> batchQueryOrderPayLog(List<PaymentLogModel> orderNos){
		List<String> list = payLogMapper.batchQueryOrderPayLog(orderNos);
		return list;
	}
	
}
