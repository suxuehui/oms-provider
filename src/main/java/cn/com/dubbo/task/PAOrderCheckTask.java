package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.PaOrderService;
import cn.com.dubbo.service.order.PsOrderService;


/**
 * 订单校对
 * 是否有漏单的情况
 * 每天凌晨2点开始执行，校验前一天的数据
 */
@Component
public class PAOrderCheckTask {
	
	private static final Logger logger = Logger.getLogger(PAOrderCheckTask.class);
	
	@Resource
	private PaOrderService paOrderService;
	
	@Resource
	private PsOrderService psOrderService;
	
	/**
	 * 方法注释
	 */
	public void execute() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			System.out.println("调用平安获取订单定时器校验订单数据开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			Calendar calendar = Calendar.getInstance();
			String endString = sdf.format(calendar.getTime());
			String endTime = new Long(calendar.getTimeInMillis()/1000).toString();
			calendar.add(Calendar.MINUTE, -60*24*1);//抓取1天内的数据
			String startString = sdf.format(calendar.getTime());
			String startTime = new Long(calendar.getTimeInMillis()/1000).toString();
	        
			System.out.print("开始时间："+startString +" 对应的utc时间："+ startTime );
			System.out.println(" 结束时间："+endString+" 对应的utc时间："+endTime);
			
			paOrderService.dealOrder(startTime,endTime,false);
			
			
			System.out.println("调用平安获取订单定时器校验订单数据结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
			
			
			System.out.println("调用平安 商城 ps 获取订单定时器校验订单数据开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			
			psOrderService.dealOrder(startTime,endTime,false);
			
			
			System.out.println("调用平安 商城 ps 获取订单定时器校验订单数据结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
