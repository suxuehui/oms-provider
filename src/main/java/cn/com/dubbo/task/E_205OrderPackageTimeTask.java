package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.E_205OrderPackageTimeService;

/**
 * 
 */
@Component
public class E_205OrderPackageTimeTask {

	private static final Logger logger = Logger.getLogger(E_205OrderPackageTimeTask.class);

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private E_205OrderPackageTimeService e_205OrderPackageTimeService;

	/**
	 * 方法注释
	 */
	public void executeTimeTask() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar currentDate = new GregorianCalendar();
			// currentDate.set(Calendar.HOUR_OF_DAY, 0);
			// currentDate.set(Calendar.MINUTE, 0);
			// currentDate.set(Calendar.SECOND, 0);
			// currentDate.set(Calendar.HOUR, -1);
			currentDate.set(Calendar.HOUR_OF_DAY, currentDate.get(Calendar.HOUR_OF_DAY) - 2);// 前一个小时
			// currentDate.add(Calendar.DAY_OF_MONTH, -2);// 前3天
			// 前5天 86400000 毫秒
			String begin = sdf.format(currentDate.getTime());
			String end = sdf.format(new Date());
			final String[] strTmp = { begin, end };
			System.out.println("E_205OrderPackageTimeTask---当前时间是：" + sdf.format(new Date()) + "---str[0]:" + strTmp[0] + "--str[1]:" + strTmp[1]);
			// 后两个参数没用到
			executeTimeTask66(strTmp, 1l, "1");
			System.out.println("E_205OrderPackageTimeTask---------整个循环结束--------------------");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public void executeTimeTask66(String[] str_date, Long multiChannelOrderBatch, String shopid) {
		// System.out.println("E_205OrderPackageTimeTask--------开始--------------------"+str_date[0]+"  --  "+str_date[1]);
		e_205OrderPackageTimeService.dealOrderStatus_TradesSoldGetResponse(str_date, multiChannelOrderBatch, shopid);
		// System.out.println("E_205OrderPackageTimeTask--------结束--------------------"+str_date[0]+"  --  "+str_date[1]);
	}
}
