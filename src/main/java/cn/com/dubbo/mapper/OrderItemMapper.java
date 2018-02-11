package cn.com.dubbo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.bean.OrderItem;
import cn.com.dubbo.model.OrderItemModel;

@Repository
public interface OrderItemMapper {
	int deleteByPrimaryKey(Long orderItemId);

	int insert(OrderItem record);

	int insertSelective(OrderItem record);

	OrderItem selectByPrimaryKey(Long orderItemId);

	int updateByPrimaryKeySelective(OrderItem record);

	int updateByPrimaryKey(OrderItem record);

	public List<OrderItem> getOrderItem(String orderDeliveryNo);
	
	public int updateOrderItemByOrderNo(OrderItemModel orderItem);
	
	List<OrderItem> checkNoSaleWares(OrderItem orderItem);
	
	public int checkNosaleWaresCount(OrderItem orderItem);
	
	List<OrderItem> checkNoSaleReissueWares(OrderItem orderItem);
	
	public int checkNosaleReissueWaresCount(OrderItem orderItem);

    int saveNoSaleWares(List<OrderItem> orderItem);

	public void deleteExamineFailOrder();

	public void updateFailItemsData();

}