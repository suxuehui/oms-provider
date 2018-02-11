package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.service.order.EMSUpdateService;
import cn.com.dubbo.service.order.PsEMSUpdateService;


/**
 * 向平安同步物流信息定时器
 */
@Component
public class PaUpdateEMSTimeTask {
	
	private static final Logger logger = Logger.getLogger(PaUpdateEMSTimeTask.class);
	
	@Resource
	private EMSUpdateService emsUpdateService;
	
	@Resource
	private PsEMSUpdateService psEMSUpdateService;
	
	/**
	 * 方法注释
	 */
	public void execute() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			logger.info("向平安商城同步物流信息定时器开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			psEMSUpdateService.orderSendEMS(ChannelConstant.CHANNEL_PS);
			
			logger.info("向平安商城同步物流信息定时器结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
			
			logger.info("向平安同步物流信息定时器开始 -时间:"+sdf.format(new Date())+"-------开始--------------------");
			
			emsUpdateService.orderSendEMS(ChannelConstant.CHANNEL_PA);
			
			logger.info("向平安同步物流信息定时器结束 -时间:"+sdf.format(new Date())+"--------结束--------------------");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
