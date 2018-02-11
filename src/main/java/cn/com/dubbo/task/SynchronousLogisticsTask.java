package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.SynchronousLogisticsService;
/**
 * 向微信和官网同步物流信息
 * @author hhr
 */
@Component
public class SynchronousLogisticsTask {
	
	private static final Logger logger = Logger.getLogger(SynchronousLogisticsTask.class);
	
	@Resource
	private SynchronousLogisticsService synchronousLogisticsService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void execute() {
		try {
			
			logger.info("向微信和官网同步物流信息，定时器开始:"+sdf.format(new Date())+"------------开始--------------");
			synchronousLogisticsService.orderSendLogistics("GW");
//			synchronousLogisticsService.orderSendLogistics("WX");
			logger.info("向微信和官网同步物流信息，定时器结束:"+sdf.format(new Date())+"------------结束--------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
