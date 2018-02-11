package cn.com.dubbo.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.model2.E_orderstate;
import cn.com.dubbo.model2.E_orderstateExample;

public interface E_orderstateMapper extends BaseDaoMapper2<E_orderstate> {
	int countByExample(E_orderstateExample example);

	int deleteByExample(E_orderstateExample example);

	int deleteByPrimaryKey(String eOrdernumber);

	int insert(E_orderstate record);

	int insertSelective(E_orderstate record);

	List<E_orderstate> selectByExample(E_orderstateExample example);

	E_orderstate selectByPrimaryKey(String eOrdernumber);

	int updateByExampleSelective(@Param("record") E_orderstate record, @Param("example") E_orderstateExample example);

	int updateByExample(@Param("record") E_orderstate record, @Param("example") E_orderstateExample example);

	int updateByPrimaryKeySelective(E_orderstate record);

	int updateByPrimaryKey(E_orderstate record);

	// 查询指定的任务单号
	public String findOrderState(String ordernumber);

}