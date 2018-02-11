package cn.com.dubbo.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.model2.E_products;
import cn.com.dubbo.model2.E_productsExample;

public interface E_productsMapper extends BaseDaoMapper2<E_products>{
    int countByExample(E_productsExample example);

    int deleteByExample(E_productsExample example);

    int insert(E_products record);

    int insertSelective(E_products record);

    List<E_products> selectByExample(E_productsExample example);

    int updateByExampleSelective(@Param("record") E_products record, @Param("example") E_productsExample example);

    int updateByExample(@Param("record") E_products record, @Param("example") E_productsExample example);
}