package cn.com.dubbo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.bean.OrderInfoBackup;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPaymentLog;

@Repository
public interface OrderMapper extends BaseMapper<OrderModel, Integer> {

	// 订单
	public void saveOrder(OrderModel orderModel);

	// 批量保存订单updateOrderStatus
	public void saveBatchOrder(List<OrderModel> orderList);

	// 订单明细保存
	public void saveOrderItem(OrderItemModel itemModel);

	// 批量保存订单明细
	public void saveBatchOrderItem(List<OrderItemModel> orderItemList);

	// 订单支付日志表
	public void saveOrderPaymentLog(OrderPaymentLog orderLog);

	// 订单支付日志表
	public void saveBatchOrderPaymentLog(List<OrderPaymentLog> orderLog);

	// 根据主键查询订单信息
	public OrderModel getByPrimaryKey(String order_no);

	/**
	 * 查询订单最近批量导入的时间
	 * 
	 * @param channelId
	 * @return
	 */
	public String lastTime(int channelId);

	/**
	 * 查询订单数据
	 * 
	 * @param orderNo
	 * @return
	 */
	public OrderModel findOrderByNo(String orderNo);
	
	/**
	 * 根据原始订单号查询所有的订单信息
	 */
	public List<OrderModel> findOrderByMultiOrderNo(String multiChannelNo);

	/**
	 * 查询订单是否存在
	 * 
	 * @param orderNo
	 * @return 0 不存在，1 存在
	 */
	public int isOrderByNo(String orderNo);
	/**
	 * 查询订单是否存在
	 * @return 0 不存在，1 存在
	 */
	public int isOrderByMulti_channel_order_no(String multi_channel_order_no);

	/**
	 * 批量查询订单
	 */
	public List<String> batchQueryOrder(List<OrderModel> orderNos);
	
	
	/**
	 * 批量查询订单
	 */
	public List<OrderModel> queryOrders(List<String> orderNos);

	/**
	 * 查询订单是否存在
	 * 
	 * @param orderNo
	 *            ,orderStatus=1 1:待审核
	 * @return 0 不存在，1 存在
	 */
	public int isOrderByNoANDOrderStatus(String orderNo);

	/**
	 * 查询订单明细是否存在
	 * 
	 * @param orderNo
	 * @return 0 不存在，1 存在
	 */
	public int isOrderItemByNo(String orderNo);

	/**
	 * 更新订单
	 * 
	 * @param orderModel
	 */
	public int updateOrder(OrderModel orderModel);

	/**
	 * 更新订单状态
	 * 
	 * @param orderModel
	 */
	public void updateOrderStatus(OrderModel orderModel);

	/**
	 * 批量更新订单
	 * 
	 * @param orderModel
	 */
	public void updateBatchOrder(List<OrderModel> orderList);

	/**
	 * @param order_status
	 * @return
	 */
	public List<OrderModel> getOrderNoListByOrderStatus(int order_status);

	public List<OrderItemModel> getOrderItemModelList(String order_no);

	public List<OrderItemModel> getOrderItemModelList_NotTM(String order_no);

	/**
	 * 
	 * @param orderNo
	 * @return
	 */
	public List<OrderItemModel> queryOrderItems(String orderNo);

	/**
	 * 分页查询订单数据
	 * 
	 * @param map
	 * @return
	 */
	public List<OrderModel> pageQueryOrderList(Map<String, Object> map);

	/**
	 * 分页查询总条目
	 * 
	 * @param orderState
	 * @return
	 */
	public int pageQueryOrderListCount(Map<String, Object> params);

	/**
	 * 查询订单明细是否存在
	 * 
	 * @param orderNo
	 *            ,goodsNo;//商品编码
	 * @return 0 不存在，1 存在
	 */
	public int isOrderItemByNoANDGoodsNo(String orderNo, String goodsNo);

	// 订单
	public void deleteOrder(String orderNo);

	// 更新订单明细信息
	public void updateOrderItem(OrderItemModel itemModel);

	// 更新订单明细表的order_delivery_no：字段回写
	public void updateOrderDeliveryNo_OrderItem(OrderItemModel itemModel);// order_delivery_no--发货单编号:隐患问题

	// 批量更新商品明细
	public void updateBatchOrderItem(List<OrderItemModel> itemList);

	// 批量查询订单明细
	public List<String> batchQueryOrderItem(List<OrderItemModel> orderNos);

	// 查询待发货
	public List<OrderModel> queryByMap(Map<String, Object> map);

	// 查询发货，包括已发和部分发货
	public List<OrderModel> querySendByMap(Map<String, Object> map);

	// 查询更新订单状态
	public List<OrderModel> queryUpdateOrderByMap(Map<String, Object> map);

	// 查询退款中和已经退款订单
	public List<OrderModel> queryRefundByMap(Map<String, Object> map);

	// 更新订单明细，根据订单编号和商品编号
	public void updateOrderItemBymap(Map<String, Object> mapUpdate);

	// 根据订单编号和商品编号查询订单明细
	public OrderItemModel queryItemBymap(Map<String, Object> mapUpdate);

	// 查询退款中和已退款订单
	public List<OrderItemModel> queryOrderItemsStatus(String orderNo);

	public List<OrderItemModel> queryOrderItemsOutGroup(String orderNo);
 

	//查询最大的时间
	public String queryMaxDay();

	//
	public void createOrderStatistic(Map<String, Object> map);
 
	
	/**
	 * @param tid
	 * @return 0 不存在，1 存在
	 */
	public int isTid(String tid);
	/**
	 * 更新订单主表所有字段
	 * 
	 * @param orderModel
	 */
	public void updateTotalOrder(OrderModel orderModel);
	
	List<OrderInfoBackup> selectByOrderNO (String orderNo);
	
	int deleteByPrimaryKey(String orderNo);		
	
	public List<OrderModel> selectTidByOrderNO (String orderNo);
	
	public Integer selectTid (String tid);
	
	public Integer isExistByTidAndOrderNO (OrderModel orderModel);
	
	public int updateOrderNoByTid(OrderModel orderModel);	
	
	public int deleteByTid(String tid);	
	
	public int updateOrderModel_orderNo(OrderModel orderModel);
	
	/**
	 * 根据原始订单号查询所有的订单信息
	 */
//	public List<OrderModel> getTidByOrderNo(String orderNo);
	
	public int updateTid(OrderModel orderModel);
	
	/**
	 * 更新天猫订单状态
	 * 
	 * @param orderModel
	 */
	public void updateOrderStatus_TM(OrderModel orderModel);
	// 订单
	public int saveOrder_TM(OrderModel orderModel);
	//作废订单,将订单状态变为7
	public void updateOrderCancleStatus(String orderNo);
	
	//查询原始订单号
	public int queyBFOrderByMultiOrderNo(String multiChannelNo);
}
