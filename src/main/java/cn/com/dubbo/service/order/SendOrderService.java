package cn.com.dubbo.service.order;

import cn.com.dubbo.model.ToSendModel;

/**
 * 订单合并
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-14 上午9:26:47
 */

public interface SendOrderService {

	public ToSendModel getLogistics(String startCreated, String endCreated, String pageNo, String pageSize, String venderId);

}