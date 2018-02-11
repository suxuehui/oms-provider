package cn.com.dubbo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.bean.OrderDeliveryModel;
import cn.com.dubbo.bean.OrderDeliveryModelExample;

public interface OrderDeliveryModelMapper {
	int countByExample(OrderDeliveryModelExample example);

	int deleteByExample(OrderDeliveryModelExample example);

	int deleteByPrimaryKey(String orderDeliveryNo);

	int insert(OrderDeliveryModel record);

	int insertSelective(OrderDeliveryModel record);

	List<OrderDeliveryModel> selectByExample(OrderDeliveryModelExample example);

	List<OrderDeliveryModel> selectByExampleForPaging(OrderDeliveryModelExample example);

	OrderDeliveryModel selectByPrimaryKey(String orderDeliveryNo);

	int updateByExampleSelective(@Param("record") OrderDeliveryModel record, @Param("example") OrderDeliveryModelExample example);

	int updateByExample(@Param("record") OrderDeliveryModel record, @Param("example") OrderDeliveryModelExample example);

	int updateByPrimaryKeySelective(OrderDeliveryModel record);

	int updateByPrimaryKey(OrderDeliveryModel record);

	List<OrderDeliveryModel> selectByExampleGroup(String order_no);
}