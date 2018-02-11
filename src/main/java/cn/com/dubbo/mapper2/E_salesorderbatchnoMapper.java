package cn.com.dubbo.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.model2.E_salesorderbatchno;
import cn.com.dubbo.model2.E_salesorderbatchnoExample;

public interface E_salesorderbatchnoMapper  extends BaseDaoMapper2<E_salesorderbatchno>{
	int countByExample(E_salesorderbatchnoExample example);

	int deleteByExample(E_salesorderbatchnoExample example);

	int insert(E_salesorderbatchno record);
	
	//批量保存数据
	void batchInsertNo(List<E_salesorderbatchno> recordList);

	int insertSelective(E_salesorderbatchno record);

	List<E_salesorderbatchno> selectByExample(E_salesorderbatchnoExample example);

	int updateByExampleSelective(@Param("record") E_salesorderbatchno record, @Param("example") E_salesorderbatchnoExample example);

	int updateByExample(@Param("record") E_salesorderbatchno record, @Param("example") E_salesorderbatchnoExample example);

}