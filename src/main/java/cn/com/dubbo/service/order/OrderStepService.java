package cn.com.dubbo.service.order;

import java.util.List;
import java.util.Map;

import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;

/**
 * 处理订单的几个步骤
 * 1审核    2 订单合并
 */
public interface OrderStepService {
	
	/**
	 * 非处方药订单审核流程
	 * @param orderNo
	 */
	public void orderNotRXExamine(String multiChannel,int orderState);
	
	/**
	 * 处方药订单审核流程
	 * @param orderNo
	 */
	public void orderRXExamine(List<String> orderNos,int auditUserId);
	
	/**
	 * 处方药审核结果更新
	 */
	public void drugExamineUp();
	
	/**
	 * 订单合并
	 */
	public void orderMerge();
	
	//审核库存
	public Map<String,Object> examineNine(List<OrderItemModel> itemList);
	
	/**
	 * 订单审核
	 * @param orderModel
	 */
	public void orderNotRXExamine(List<OrderModel> orderList);
	
}
