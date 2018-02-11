package cn.com.dubbo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.model.OrderPackage205;
import cn.com.dubbo.model.OrderPackage205Example;

public interface OrderPackage205Mapper {
	int countByExample(OrderPackage205Example example);

	int deleteByExample(OrderPackage205Example example);

	int deleteByPrimaryKey(Long packageId);

	int insert(OrderPackage205 record);

	int insertSelective(OrderPackage205 record);

	List<OrderPackage205> selectByExample(OrderPackage205Example example);

	OrderPackage205 selectByPrimaryKey(Long packageId);

	int updateByExampleSelective(@Param("record") OrderPackage205 record, @Param("example") OrderPackage205Example example);

	int updateByExample(@Param("record") OrderPackage205 record, @Param("example") OrderPackage205Example example);

	int updateByPrimaryKeySelective(OrderPackage205 record);

	int updateByPrimaryKey(OrderPackage205 record);
}