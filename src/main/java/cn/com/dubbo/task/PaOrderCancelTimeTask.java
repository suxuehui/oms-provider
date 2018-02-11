package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.service.order.JDOrderCancelService;
import cn.com.dubbo.service.order.PaOrderCancelService;


/**
 * 平安订单取消定时器
 */
@Component
public class PaOrderCancelTimeTask {
	
	private static final Logger logger = Logger.getLogger(PaOrderCancelTimeTask.class);
	
	@Resource
	private PaOrderCancelService paOrderCancelService;
	
	@Resource
	private JDOrderCancelService jDOrderCancelService;
	
	/**
	 * 方法注释
	 */
	public void execute() {
		try {
			if(!logger.isDebugEnabled()){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				System.out.println("调用平安取消订单定时器开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
				
				Calendar calendar = Calendar.getInstance();
				String endString = sdf.format(calendar.getTime());
				String endTime = new Long(calendar.getTimeInMillis()/1000).toString();
				calendar.add(Calendar.MINUTE, -60*24*4);//抓取4天内的数据
				String startString = sdf.format(calendar.getTime());
				String startTime = new Long(calendar.getTimeInMillis()/1000).toString();
				System.out.print("开始时间："+startString +" 对应的utc时间："+ startTime );
				System.out.println(" 结束时间："+endString+" 对应的utc时间："+endTime);
				
				paOrderCancelService.dealCannelOrder(startTime,endTime,ChannelConstant.CHANNEL_PA);
				
				paOrderCancelService.dealCannelOrder(startTime,endTime,ChannelConstant.CHANNEL_PS);
				
				
				paOrderCancelService.dealCannelOrder(startString,endString,ChannelConstant.CHANNEL_TM);
				
				System.out.println("调用平安取消订单定时器结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
				
				//调用京东取消的接口
//				jDOrderCancelService.dealCannelOrder(startString, endString, "TRADE_CANCELED");
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
