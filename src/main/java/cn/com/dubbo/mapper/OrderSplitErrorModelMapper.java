package cn.com.dubbo.mapper;

import cn.com.dubbo.bean.OrderSplitErrorModel;
import cn.com.dubbo.bean.OrderSplitErrorModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderSplitErrorModelMapper {
    int countByExample(OrderSplitErrorModelExample example);

    int deleteByExample(OrderSplitErrorModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderSplitErrorModel record);

    int insertSelective(OrderSplitErrorModel record);

    List<OrderSplitErrorModel> selectByExample(OrderSplitErrorModelExample example);

    OrderSplitErrorModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderSplitErrorModel record, @Param("example") OrderSplitErrorModelExample example);

    int updateByExample(@Param("record") OrderSplitErrorModel record, @Param("example") OrderSplitErrorModelExample example);

    int updateByPrimaryKeySelective(OrderSplitErrorModel record);

    int updateByPrimaryKey(OrderSplitErrorModel record);
}