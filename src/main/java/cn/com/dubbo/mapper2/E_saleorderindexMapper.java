package cn.com.dubbo.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.model2.E_saleorderindex;
import cn.com.dubbo.model2.E_saleorderindexExample;

public interface E_saleorderindexMapper extends BaseDaoMapper2<E_saleorderindex>{
    int countByExample(E_saleorderindexExample example);

    int deleteByExample(E_saleorderindexExample example);

    int deleteByPrimaryKey(String eOrdernumber);

    int insert(E_saleorderindex record);

    int insertSelective(E_saleorderindex record);

    List<E_saleorderindex> selectByExample(E_saleorderindexExample example);

    E_saleorderindex selectByPrimaryKey(String eOrdernumber);

    int updateByExampleSelective(@Param("record") E_saleorderindex record, @Param("example") E_saleorderindexExample example);

    int updateByExample(@Param("record") E_saleorderindex record, @Param("example") E_saleorderindexExample example);

    int updateByPrimaryKeySelective(E_saleorderindex record);

    int updateByPrimaryKey(E_saleorderindex record);
}