package cn.com.dubbo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.bean.OrderInfoModel;
import cn.com.dubbo.bean.OrderInfoModelExample;

public interface OrderInfoModelMapper {
	int countByExample(OrderInfoModelExample example);

	int deleteByExample(OrderInfoModelExample example);

	int deleteByPrimaryKey(String orderNo);

	int insert(OrderInfoModel record);

	int insertSelective(OrderInfoModel record);

	List<OrderInfoModel> selectByExample(OrderInfoModelExample example);

	List<OrderInfoModel> selectByExampleForPaging(OrderInfoModelExample example);

	OrderInfoModel selectByPrimaryKey(String orderNo);

	int updateByExampleSelective(@Param("record") OrderInfoModel record, @Param("example") OrderInfoModelExample example);

	int updateByExample(@Param("record") OrderInfoModel record, @Param("example") OrderInfoModelExample example);

	int updateByPrimaryKeySelective(OrderInfoModel record);

	int updateByPrimaryKey(OrderInfoModel record);
}