package cn.com.dubbo.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.model2.E_salesorderdetail;
import cn.com.dubbo.model2.E_salesorderdetailExample;

public interface E_salesorderdetailMapper extends BaseDaoMapper2<E_salesorderdetail>{
    int countByExample(E_salesorderdetailExample example);

    int deleteByExample(E_salesorderdetailExample example);

    int insert(E_salesorderdetail record);
    
    void batchInsert(List<E_salesorderdetail> recordList);

    int insertSelective(E_salesorderdetail record);

    List<E_salesorderdetail> selectByExample(E_salesorderdetailExample example);

    int updateByExampleSelective(@Param("record") E_salesorderdetail record, @Param("example") E_salesorderdetailExample example);

    int updateByExample(@Param("record") E_salesorderdetail record, @Param("example") E_salesorderdetailExample example);
}