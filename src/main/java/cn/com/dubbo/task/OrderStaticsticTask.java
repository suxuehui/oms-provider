package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.OrderService;

/**
 * 
 */
@Component
public class OrderStaticsticTask {
	
//	private static final Logger logger = Logger.getLogger(OrderStaticsticTask.class);
	
	@Resource
	private OrderService OrderService;
	
	/**
	 * 方法注释
	 */
	public void executeTimeTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println("当前时间:"+sdf.format(new Date())+"----------------------统计开始---------------");
			OrderService.createOrderStatistic();
			System.out.println("当前时间:"+sdf.format(new Date())+"----------------------统计结束---------------");
		} catch (Exception e) {
//			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
