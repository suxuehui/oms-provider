package cn.com.dubbo.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.CheckSplitOrderService;

/**
 * 
 */
@Component
public class CheckSplitOrderTask {

	private static final Logger logger = Logger.getLogger(CheckSplitOrderTask.class);

	@Resource
	private CheckSplitOrderService checkSplitOrderService;

	/**
	 * 方法注释
	 */
	public void executeTimeTask() {

		try {
			System.out.println("检查订单拆分开始--------------------");
			checkSplitOrderService.checkSplitOrder();
			System.out.println("检查订单拆分结束--------------------");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

	}

}
