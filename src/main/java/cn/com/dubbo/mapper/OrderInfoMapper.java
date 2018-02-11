package cn.com.dubbo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.bean.OrderInfo;
import cn.com.dubbo.bean.OrderInfoExample;
import cn.com.dubbo.util.temporary.EdbOrderInfoTemp;

public interface OrderInfoMapper {
	int countByExample(OrderInfoExample example);

	int deleteByExample(OrderInfoExample example);

	int deleteByPrimaryKey(String orderNo);

	int insert(OrderInfo record);

	int insertSelective(OrderInfo record);

	List<OrderInfo> selectByExample(OrderInfoExample example);

	List<OrderInfo> selectByExampleForPaging(OrderInfoExample example);

	OrderInfo selectByPrimaryKey(String orderNo);

	int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

	int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

	int updateByPrimaryKeySelective(OrderInfo record);

	int updateByPrimaryKey(OrderInfo record);

	void updateByorderNo(Map<String, Object> map);

	void updateSendStatusByorderNo(Map<String, Object> map);

	List<OrderInfo> selectByMultiChannelOrderNo(String multiChannelOrderNo);

	List<EdbOrderInfoTemp> example_Where_EDB(Map<String, Object> map);

	int count(Map<String, Object> map);

	void updateByorderNo12(Map<String, Object> map);
}