package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.JDOrderService;
import cn.com.dubbo.service.order.PaOrderService;


/**
 * 
 */
@Component
public class JdAPaOrderTimeTask {
	
	private static final Logger logger = Logger.getLogger(JdAPaOrderTimeTask.class);
	
	@Resource
	private PaOrderService paOrderService;
	
	@Resource
	private JDOrderService jdOrderService;
	
	/**
	 * 方法注释
	 */
	public void execute() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			System.out.println("调用平安获取订单定时器开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			Calendar calendar = Calendar.getInstance();
			String endString = sdf.format(calendar.getTime());
			String endTime = new Long(calendar.getTimeInMillis()/1000).toString();
			calendar.add(Calendar.MINUTE, -40);//抓取40分钟以内的数据
			String startString = sdf.format(calendar.getTime());
			String startTime = new Long(calendar.getTimeInMillis()/1000).toString();
			System.out.print("开始时间："+startString +" 对应的utc时间："+ startTime );
			System.out.println(" 结束时间："+endString+" 对应的utc时间："+endTime);
			
			paOrderService.dealOrder(startTime,endTime,true);
			
			System.out.println("调用平安获取订单定时器结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 方法注释
	 */
	public void jdExecute() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			System.out.println("调用京东获取订单定时器开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			Calendar calendar = Calendar.getInstance();
			String endString = sdf.format(calendar.getTime());
			String endTime = new Long(calendar.getTimeInMillis()).toString();
			calendar.add(Calendar.MINUTE, -40);//抓取40分钟以内的数据
			String startString = sdf.format(calendar.getTime());
			String startTime = new Long(calendar.getTimeInMillis()).toString();
			System.out.print("开始时间："+startString +" 对应的utc时间："+ startTime );
			System.out.println(" 结束时间："+endString+" 对应的utc时间："+endTime);
			
			jdOrderService.dealOrder(startString,endString,"WAIT_SELLER_STOCK_OUT");//等待出库 
			
			System.out.println("调用京东获取订单定时器结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
