package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.service.order.OrderSupplementService;
import cn.com.dubbo.service.order.TmOrderService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.DateUtilTool;
import cn.com.dubbo.util.PropertiesUtil;
import cn.com.dubbo.util.StringUtil;
import cn.com.dubbo.constant.OrderState;

/**
 * 
 */
@Component
public class TmOrderTimeTask {
	
	private static final Logger logger = Logger.getLogger(TmOrderTimeTask.class);
	
	@Resource
	private TmOrderService tmOrderService;
	
	@Resource
	private OrderSupplementService orderSupplementService;
	
	@Resource
	private OrderStepService orderStepService;
	
	/**
	 * 方法注释
	 */
	public void executeTimeTask() {
		try {
			PropertiesUtil propertiesUtil = new PropertiesUtil("/config/system.properties");
			String startTime = propertiesUtil.getProperty("TmOrderTimeTask_startTime");
			
		    String endTime = DateUtilTool.getEnd_Minus_Minute(90);
			String endTime_temp = propertiesUtil.getProperty("TmOrderTimeTask_endTime");
			if (StringUtil.isNotNull_String(endTime_temp)) {
				endTime =endTime_temp;
			}
			String split_minute = propertiesUtil.getProperty("TmOrderTimeTask_split_minute");
			String minus_minute = propertiesUtil.getProperty("TmOrderTimeTask_minus_minute");
			String tmOrderTimeTask_flag = propertiesUtil.getProperty("TmOrderTimeTask_flag");
			//工具类中的小时数配置
			String tmOrderTimeTask_hour = propertiesUtil.getProperty("TmOrderTimeTask_hour");
			
			Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("1");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			if("1".equals(tmOrderTimeTask_flag)){
				
				final String[] strTmp =  DateUtilTool.getStartEnd_Add(Integer.valueOf(tmOrderTimeTask_hour)); 
				System.out.println("E_DeliveryInfoTimeTask---当前时间是：" + sdf.format(new Date()) + "---str[0]:" + strTmp[0] + "--str[1]:" + strTmp[1] );
				tmOrderService.dealOrder_TradesSoldGetResponse(strTmp,multiChannelOrderBatch);
				//进行审单的
//				orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_TM,OrderState.STATE_1.getCode());
				
			}else{
				
				String[] str_date=DateUtilTool.dateDiff(startTime,endTime,new Integer(split_minute).intValue());
				System.out.println("TmOrderTimeTask---------整个循环开始--------------------");
				String[] str= new String[2]; 
				for (int i = 0; i < str_date.length; i++) {
					str=DateUtilTool.getStartEnd_Minus_Minute(str_date[i], new Integer(minus_minute).intValue());
					tmOrderService.dealOrder_TradesSoldGetResponse(str,multiChannelOrderBatch);
				}
				System.out.println("TmOrderTimeTask---------整个循环结束--------------------"); 	
				//进行审单的
//				orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_TM,OrderState.STATE_1.getCode());
			}
			
			
/*			System.out.println("TmOrderTimeTask--------开始--------------------");
			//1:是天猫
			Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("1");
			String[] str=DateUtil.getStartEnd();
			//查询卖家交易数据
			tmOrderService.dealOrder_TradesSoldGetResponse(str,multiChannelOrderBatch);
			
//			Thread.sleep(10000);//30秒
			//增量查询卖家交易
//			tmOrderService.dealOrder_TradesSoldIncrementGetResponse(str,multiChannelOrderBatch);
			
//			Thread.sleep(10000);//30秒
//			//商家发货接口
//			tmOrderService.deliveryInterface();

			//进行审单的
			orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_TM);*/
			System.out.println("TmOrderTimeTask---------结束--------------------");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
