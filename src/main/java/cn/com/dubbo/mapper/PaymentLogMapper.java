package cn.com.dubbo.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.bean.OrderItem;
import cn.com.dubbo.model.PaymentLogModel;

@Repository
public interface PaymentLogMapper extends BaseMapper<PaymentLogModel, Integer>{
	
	//订单日志
	public void savePaymentLog(PaymentLogModel log);
	
	//批量保存订单日志
	public void saveBatchPayLog(List<PaymentLogModel> logList);
	
	/**
	 * 查询记录是否存在
	 * @param orderNo
	 * @return 0 不存在，1 存在
	 */
	public int isLogByOrderNo(String bussinessId);
	
	/**
	 * 查询某订单的支付详情
	 * @param bussinessId
	 * @return
	 */
	public List<PaymentLogModel> queryLogsByOrderNo(String bussinessId);

	/**
	 * 批量查询订单支付
	 * @param bussinessId
	 * @return
	 */
	public List<String> batchQueryOrderPayLog(List<PaymentLogModel> orderNos);
	
	/**
	 * @param paymentLogModel
	 * @return
	 */
	public int updatePaymentLog_orderNo(PaymentLogModel paymentLogModel);
	
}
