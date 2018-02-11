package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.GW_OrderService;

/**
 * 从官网同步订单信息到 205
 * @author hhr
 */
@Component
public class GW_Order_SycTask {
	
	private static final Logger logger = Logger.getLogger(GW_Order_SycTask.class);
	
	@Resource
	private GW_OrderService gw_OrderService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 方法注释
	 */
	public void execute() {
		try {
 
			Calendar calendar = Calendar.getInstance();
			String endString = sdf.format(calendar.getTime());
			String endTime = new Long(calendar.getTimeInMillis()/1000).toString();
			calendar.add(Calendar.MINUTE, -40);//抓取40分钟以内的数据
			String startString = sdf.format(calendar.getTime());
			String startTime = new Long(calendar.getTimeInMillis()/1000).toString();
			System.out.print("开始时间："+startString +" 对应的utc时间："+ startTime );
			System.out.println(" 结束时间："+endString+" 对应的utc时间："+endTime);
		    String[] str_date = new String[]{startString,endString};
//		    String[] str_date = new String[]{"2016-10-29 00:00:00","2016-11-01 00:00:00"};
			System.out.println("从官网同步订单信息到 205---------整个循环开始--------------------");
			for (int i = 0; i < str_date.length; i++) {
				gw_OrderService.getGwOrderTrades(str_date, 1L, "1");
			}
			System.out.println("从官网同步订单信息到 205---------整个循环结束--------------------"); 	
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
