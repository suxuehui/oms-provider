package cn.com.dubbo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.bean.OrderItemModel;
import cn.com.dubbo.bean.OrderItemModelExample;
import cn.com.dubbo.bean.ext.ItemBatchDetail;

public interface OrderItemModelMapper {
	int countByExample(OrderItemModelExample example);

	int deleteByExample(OrderItemModelExample example);

	int deleteByPrimaryKey(Long orderItemId);

	int insert(OrderItemModel record);

	int insertSelective(OrderItemModel record);

	List<OrderItemModel> selectByExampleGroup(OrderItemModelExample example);

	OrderItemModel selectByPrimaryKey(Long orderItemId);

	int updateByExampleSelective(@Param("record") OrderItemModel record, @Param("example") OrderItemModelExample example);

	int updateByExample(@Param("record") OrderItemModel record, @Param("example") OrderItemModelExample example);

	int updateByPrimaryKeySelective(OrderItemModel record);

	int updateByPrimaryKey(OrderItemModel record);

	int updateList(List<ItemBatchDetail> itemBatchDetail);

	void updateItem(ItemBatchDetail itemBatchDetail);

	void updateOne(ItemBatchDetail detail);
}