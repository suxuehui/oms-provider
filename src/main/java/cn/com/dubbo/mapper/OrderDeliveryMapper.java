package cn.com.dubbo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.MultiChannelModel;
import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.model.OrderPackageItem;

@Repository
public interface OrderDeliveryMapper extends BaseMapper<OrderDelivery, Integer> {
	// BaseDaoMapper<Employee> {

	public void saveOrderDelivery(OrderDelivery orderDelivery);

	/**
	 * @param state
	 * @return
	 */
	public List<OrderDelivery> queryOrderDeliveryByNo(String orderNo);

	/**
	 * @param state
	 * @return
	 */
	public Integer isExistOrderDelivery(String order_delivery_no);

	// 根据发货单号查询selectOrderDeliveryBydeliveryNo
	public OrderDelivery selectOrderDeliveryBydeliveryNo(OrderDelivery orderDelivery);

	public OrderDelivery selectOrderDeliveryByStr(String order_delivery_no);

	// /**
	// */
	// public void updateOrderDelivery(String order_no);

	// ----------------------------------------------
	// 根据订单编号查询
	public List<OrderDelivery> selectOrderDeliveryByNo(String order_no);

	// public OrderModel selectOrderInfoOne(String orderNo);
	// 渠道查询
	public List<MultiChannelModel> queryMultiChannel();

	public List<OrderModel> queryOrderInfoByGroup(long multiChannelId);

	// 根据receive_user和receiveFullAddress查询
	public List<OrderModel> queryOrderInfoByModel(OrderModel orderModel);

	public void updateByMap(Map<String, Object> map);

	public void insertPackageSelective(OrderPackage orderpk);

	// 根据包裹编号查询发货单
	public List<OrderDelivery> getDeliveryByPackageNo(String package_no);

	public void insertPackageItemSelective(OrderPackageItem item);

	public List<OrderDelivery> getList();

	// 更新发货单stock_id,logistic_company_id,logistic_no,delivery_status根据主键
	public void updateDeliveryByMap(OrderDelivery orderDelivery);

}
