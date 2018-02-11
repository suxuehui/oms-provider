package cn.com.dubbo.service.order;

import cn.com.dubbo.model.ToSendModel;

/**
 * 更新订单状态
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-14 上午9:26:47
 */

public interface UpdateOrderStatusService {

	public ToSendModel getUpdateOrder(String pageNo, String pageSize, String venderId);

}