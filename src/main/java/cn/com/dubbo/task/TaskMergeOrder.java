package cn.com.dubbo.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.MergeOrderService;

/**
 * 
 */
@Component
public class TaskMergeOrder {

	private static final Logger logger = Logger.getLogger(TaskMergeOrder.class);

	@Resource
	private MergeOrderService mergeOrderService;

	/**
	 * 方法注释
	 */
	public void executeTimeTask() {

		try {
			System.out.println("方法合并--------开始--------------------");
			mergeOrderService.mergeOrder();
			System.out.println("方法合并---------结束--------------------");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

	}

}
