package cn.com.dubbo.service.order;

import java.util.List;

import cn.com.dubbo.model.OrderModel;


public interface WinRecordService {

	//导入中奖数据的方法
	void saveWinRecord(List<OrderModel> list, Long multiChannelOrderBatch);
	
	//保存拼多多手动导入的订单
	public void savePDOrder(String pathName);
	
}
