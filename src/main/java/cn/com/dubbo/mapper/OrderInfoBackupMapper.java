package cn.com.dubbo.mapper;

import cn.com.dubbo.bean.OrderInfoBackup;
import cn.com.dubbo.model.OrderModel;

public interface OrderInfoBackupMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(OrderInfoBackup record);

    int insertSelective(OrderInfoBackup record);

    OrderInfoBackup selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(OrderInfoBackup record);

    int updateByPrimaryKey(OrderInfoBackup record);
	
	public Integer isExistByTidAndOrderNO (OrderInfoBackup orderInfoBackup);
}