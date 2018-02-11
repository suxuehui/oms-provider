package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.PsOrderService;


/**
 * 平安商城
 */
@Component
public class PSOrderTimeTask {
	
	private static final Logger logger = Logger.getLogger(PSOrderTimeTask.class);
	
	@Resource
	private PsOrderService psOrderService;
	
	
	/**
	 * 方法注释
	 */
	public void execute() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			logger.info("调用平安商城获取订单定时器开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			Calendar calendar = Calendar.getInstance();
			String endString = sdf.format(calendar.getTime());
			String endTime = new Long(calendar.getTimeInMillis()/1000).toString();
			calendar.add(Calendar.MINUTE, -40);//抓取40分钟以内的数据
			String startString = sdf.format(calendar.getTime());
			String startTime = new Long(calendar.getTimeInMillis()/1000).toString();
			System.out.print("开始时间："+startString +" 对应的utc时间："+ startTime );
			System.out.println(" 结束时间："+endString+" 对应的utc时间："+endTime);
			
			psOrderService.dealOrder(startTime,endTime,true);
			
			logger.info("调用平安商城获取订单定时器结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
