package cn.com.dubbo.service.order;

import java.util.List;

import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;

public interface TransactionalOrderService {
	
//	public void saveTrade(OrderModel orderModel,List<OrderItemModel> orderItemModelList,List<PaymentLogModel> paymentLogList,LogisticChannelModel logisticChannelModel);
	
	public void saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus(List<OrderDelivery> list_OrderDelivery,List<OrderItemModel> list_OrderItemModel,OrderModel orderModel);
	
	public void saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus66();
}
