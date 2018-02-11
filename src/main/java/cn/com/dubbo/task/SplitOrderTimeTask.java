package cn.com.dubbo.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.SplitOrderService;


/**
 * 
 */
@Component
public class SplitOrderTimeTask {
	
	private static final Logger logger = Logger.getLogger(SplitOrderTimeTask.class);
	
	@Resource
	private SplitOrderService splitOrderService;
	
	/**
	 * 方法注释
	 */
	public void executeTimeTask() {
		try {
			System.out.println("SplitOrderTimeTask--------开始--------------------");
			splitOrderService.splitOrder();
//			transactionalOrderService.saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus66();
			System.out.println("SplitOrderTimeTask---------结束--------------------");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
