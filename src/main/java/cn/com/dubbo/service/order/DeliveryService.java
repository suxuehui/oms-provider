package cn.com.dubbo.service.order;

import cn.com.dubbo.model.RefundModel;

/**
 * 订单合并
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-14 上午9:26:47
 */

public interface DeliveryService {

	public RefundModel deliveryUpdate(String str) throws Exception;

}