package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.JDOrderService;


/**
 * 订单校对
 * 是否有漏单的情况
 * 执行，校验前一天的数据
 */
@Component
public class JDOrderCheckTask {
	
	private static final Logger logger = Logger.getLogger(JDOrderCheckTask.class);
	
	@Resource
	private JDOrderService jdOrderService;
	
	
	/**
	 * 方法注释
	 */
	public void execute() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");//
			
			System.out.println("调用平安获取订单定时器校验订单数据开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 57);//57分钟
			String endString = sdf.format(calendar.getTime());
			String endTime = new Long(calendar.getTimeInMillis()/1000).toString();
			
			String startString = sdf2.format(calendar.getTime());
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
	        
			String startTime = new Long(calendar.getTimeInMillis()/1000).toString();
			System.out.print("开始时间："+startString +" 对应的utc时间："+ startTime );
			System.out.println(" 结束时间："+endString+" 对应的utc时间："+endTime);
			
			jdOrderService.dealOrder(startTime,endTime,"FINISHED_L");
			
			
			System.out.println("调用平安获取订单定时器校验订单数据结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
