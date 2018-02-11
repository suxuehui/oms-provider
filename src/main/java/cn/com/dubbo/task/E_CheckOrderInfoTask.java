package cn.com.dubbo.task;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.E_CheckOrderService;

/**
 * 
 */
@Component
public class E_CheckOrderInfoTask {

	private static final Logger logger = Logger.getLogger(E_CheckOrderInfoTask.class);

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private E_CheckOrderService e_CheckOrderService;

	/**
	 * 方法注释
	 */
	public void executeTimeTask() {
		try {
			logger.info("E_CheckOrderInfoTask---------整个循环开始");
			System.out.println("E_CheckOrderInfoTask---------整个循环开始--------------------");
			e_CheckOrderService.CheckOrderInfo();
			System.out.println("E_CheckOrderInfoTask---------整个循环结束--------------------");
			logger.info("E_CheckOrderInfoTask---------整个循环结束");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
