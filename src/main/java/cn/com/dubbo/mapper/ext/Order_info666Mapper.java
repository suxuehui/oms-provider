package cn.com.dubbo.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.bean.ext.Order_info666;
import cn.com.dubbo.bean.ext.Order_info666Example;

public interface Order_info666Mapper {
	int countByExample(Order_info666Example example);

	int deleteByExample(Order_info666Example example);

	int deleteByPrimaryKey(String orderNo);

	int insert(Order_info666 record);

	int insertSelective(Order_info666 record);

	List<Order_info666> selectByExample(Order_info666Example example);

	Order_info666 selectByPrimaryKey(String orderNo);

	int updateByExampleSelective(@Param("record") Order_info666 record, @Param("example") Order_info666Example example);

	int updateByExample(@Param("record") Order_info666 record, @Param("example") Order_info666Example example);

	int updateByPrimaryKeySelective(Order_info666 record);

	int updateByPrimaryKey(Order_info666 record);

	List<Order_info666> getList();
}