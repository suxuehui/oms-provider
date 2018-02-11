package cn.com.dubbo.mapper.ext;

import cn.com.dubbo.bean.ext.Order_item666;
import cn.com.dubbo.bean.ext.Order_item666Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Order_item666Mapper {
    int countByExample(Order_item666Example example);

    int deleteByExample(Order_item666Example example);

    int deleteByPrimaryKey(Long orderItemId);

    int insert(Order_item666 record);

    int insertSelective(Order_item666 record);

    List<Order_item666> selectByExample(Order_item666Example example);

    Order_item666 selectByPrimaryKey(Long orderItemId);

    int updateByExampleSelective(@Param("record") Order_item666 record, @Param("example") Order_item666Example example);

    int updateByExample(@Param("record") Order_item666 record, @Param("example") Order_item666Example example);

    int updateByPrimaryKeySelective(Order_item666 record);

    int updateByPrimaryKey(Order_item666 record);
}