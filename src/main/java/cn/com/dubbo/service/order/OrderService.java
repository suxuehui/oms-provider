package cn.com.dubbo.service.order;

import java.util.List;
import java.util.Map;

import cn.com.dubbo.model.LogisticChannelModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.util.DataQueue;

public interface OrderService {
	
	/**
	 * 查询订单
	 * @param orderNo 订单号
	 */
	public OrderModel findOrderByNo(String orderNo);
	
	/**
	 * 根据原始订单号查询所有的订单信息
	 */
	public List<OrderModel> findOrderByMultiOrderNo(String multiChannelNo);
	
	/**
	 * 查询订单是否存在
	 */
	public boolean isOrderByNo(String orderNo);
	
	/**
	 * 查询订单明细是否存在
	 */
	public boolean isOrderItemByNo(String orderNo);
	
	
	//订单
	public void saveOrder(OrderModel orderModel);
	
	
	//订单明细保存
	public void saveOrderItem(OrderItemModel itemModel);
	
	/**
	 * 包含校验
	 * @param orderList
	 */
	public int saveBatchOrder(List<OrderModel> orderList,DataQueue<OrderModel> queue);
	
	/**
	 * 包含校验
	 * @param orderItemList
	 */
	public void saveBatchOrderItem(List<OrderItemModel> orderItemList,DataQueue<OrderItemModel> queue);
	
	/**
	 * 更新订单
	 * @param orderModel
	 */
	public void updateOrder(OrderModel orderModel);
	
	/**
	 * 批量更新订单
	 * @param orderModel
	 */
	public void updateBatchOrder(List<OrderModel> orderList);
	
	/**
	 * @param orderItemList
	 */
	public void saveBatchOrderItem(List<OrderItemModel> orderItemList);
	
	public void savePaymentLogModelList(List<PaymentLogModel> list);
	

	/**
	 * 查询明细
	 * @param orderNo
	 * @return
	 */
	public List<OrderItemModel>  queryOrderItems(String orderNo);
	
	public int pageQueryOrderListCount(Map<String,Object> params);
	
	public List<OrderModel> pageQueryOrderList(Map<String,Object> params);
	

	public void updateBatchOrder666(List<OrderModel> orderModelList);
	
	public void saveTrade(OrderModel orderModel,List<OrderItemModel> orderItemModelList,List<PaymentLogModel> paymentLogList,LogisticChannelModel logisticChannelModel);
	
	public void saveBatchOrder(List<OrderModel> orderList);

	//更新订单明细
	public void updateOrderItem(OrderItemModel itemModel);
	
	//批量更新商品明细
	public void updateBatchOrderItem(List<OrderItemModel> itemList);
	
	
	/**
	 * 批量查询订单
	 */
	public List<String> batchQueryOrder(List<OrderModel> orderNos);
	
	/**
	 * 批量查询订单
	 */
	public List<OrderModel> queryOrders(List<String> orderNos);
	
	/**
	 * 批量查询订单明细
	 */
	public List<String> batchQueryOrderItem(List<OrderItemModel> orderNos);
 

	/**
	 * 插入统计的数量
	 */
	public void createOrderStatistic();
	
	
	/**
	 * 更新订单明细
	 */
	public void updateOrderItemByOrderNo(OrderItemModel itemModel);
	
	/**
	 * 360订单导入：保存订单信息
	 * @param orderModel
	 * @param orderItemModelList
	 * @param paymentLogList
	 * @param logisticChannelModel
	 */
	public void saveTrade_360(OrderModel orderModel,List<OrderItemModel> orderItemModelList,List<PaymentLogModel> paymentLogList,LogisticChannelModel logisticChannelModel);

	//更换69码
//	public void updateOrder69ByOrderNo(String orderNo, String old_order_69,String new_order_69,String orderNotes);

	//运营更新69码接口,添加的数量 
	public boolean updateOrder69ByOrderNo(String orderNo, String oldOrder69,String newOrder69, String notes, String amount,Integer addUserId);

	//运营由TM码替换成单品
	public boolean updateOrderTmTo69(String orderNo, String oldTmNo,String newOrder69, String orderNotes, String amount, Integer addUserId);

	//运营由单品替换TM码
	public boolean excSingleToTM(String orderNo, String oldGoods69,String newOrderTM, String string, String amount, Integer addUserId, String orderNoHead);

	//运营由TM替换TM码
	public boolean excTMToTM(String orderNo, String oldOrderTM,String newOrderTM, String string, String amount, Integer addUserId);

	/**
	 * 取消订单接口
	 * @param orderNo
	 * @return  
	 */
	public String orderCannel(String orderNo,int userId,String notes);
	/**
	 * 删除审核失败的订单
	 */
	public void deleteExamineFailOrder();
	
	//查询原始订单号
	public int queyBFOrderByMultiOrderNo(String multiChannelNo);
	
}
