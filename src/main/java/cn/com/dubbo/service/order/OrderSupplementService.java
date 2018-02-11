package cn.com.dubbo.service.order;

import java.util.List;

import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;


/**
 * 抓取订单后，拆分组合类型的订单明细，补充相关的信息
 */
public interface OrderSupplementService {
	
	/**
	 * 抓取订单后，拆分组合类型的订单明细，补充相关的信息
	 * @param orderNo
	 */
//	public void orderSupplement(String multiChannel,int orderState);
	
	/**
	 * 天猫专用
	 * 拆分组合价格
	 */
	public List<OrderItemModel> tmData(List<OrderItemModel> tempList,OrderItemModel groupItemModel);
	
	//给单个订单补充基础数据
	public void orderDataUpdate(OrderModel orderModel);
	
	//给单个订单拆分数据
	public void dealSupplement(OrderModel orderModel);
	
	/**
	 * 新加方法供系统调用
	 * @param multiChannel
	 * @param orderState
	 * @param pageIndex
	 */
	public String orderSupplement(String multiChannel,int orderState,int pageIndex);
	
}
