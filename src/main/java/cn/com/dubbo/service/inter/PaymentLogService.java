package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.util.DataQueue;

public interface PaymentLogService {
	
	//订单日志
	public void savePaymentLog(PaymentLogModel log);
		
	//批量保存订单日志
	public void saveBatchPayLog(List<PaymentLogModel> logList,DataQueue<PaymentLogModel> logModel);
	
	//批量保存订单日志
	public void saveBatchPayLog(List<PaymentLogModel> logList);
	
	//订单日志中是否含有某订单
	public boolean isLogByOrderNo(String bussinessId);
	
	/**
	 * 查询某订单的支付详情
	 * @param bussinessId
	 * @return
	 */
	public List<PaymentLogModel> queryLogsByOrderNo(String bussinessId);
	
	/**
	 * 批量查询订单支付明细
	 */
	public List<String> batchQueryOrderPayLog(List<PaymentLogModel> orderNos);
	
}