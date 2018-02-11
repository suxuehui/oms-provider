package cn.com.dubbo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.service.order.Health360OrderService;
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.service.order.OrderSupplementService;
import cn.com.dubbo.util.BussinessUtil;
import cn.com.dubbo.util.DateUtilTool;
import cn.com.dubbo.util.PropertiesUtil;
import cn.com.dubbo.util.StringUtil;


/**
 * 
 */
@Component
public class Health360OrderTimeTask {
	
	private static final Logger logger = Logger.getLogger(Health360OrderTimeTask.class);
	
	@Resource
	private Health360OrderService health360OrderService;
	
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
			String startTime = propertiesUtil.getProperty("Health360OrderTimeTask_startTime");
			
		    String endTime = DateUtilTool.getEnd_Minus_Minute(90);
			String endTime_temp = propertiesUtil.getProperty("Health360OrderTimeTask_endTime");
			if (StringUtil.isNotNull_String(endTime_temp)) {
				endTime =endTime_temp;
			}
			String split_minute = propertiesUtil.getProperty("Health360OrderTimeTask_split_minute");
			String minus_minute = propertiesUtil.getProperty("Health360OrderTimeTask_minus_minute");
			String health360TimeTask_flag = propertiesUtil.getProperty("Health360OrderTimeTask_flag");
			//工具类中的小时数配置
			String health360TimeTask_hour = propertiesUtil.getProperty("Health360OrderTimeTask_hour");
			
			Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("3");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			if("1".equals(health360TimeTask_flag)){
				System.out.println("Health360OrderTimeTask---------某段时间开始--------------------"); 
				final String[] strTmp =  DateUtilTool.getStartEnd_Add(Integer.valueOf(health360TimeTask_hour)); 
				System.out.println("E_DeliveryInfoTimeTask---当前时间是：" + sdf.format(new Date()) + "---str[0]:" + strTmp[0] + "--str[1]:" + strTmp[1] );
				//查询卖家交易数据
				health360OrderService.dealOrder_TradesSoldGetResponse(strTmp,multiChannelOrderBatch);
				Thread.sleep(10000);//30秒
				//增量查询卖家交易
				health360OrderService.dealOrder_TradesSoldIncrementGetResponse(strTmp,multiChannelOrderBatch);
				
				System.out.println("Health360OrderTimeTask---------某段时间结束--------------------"); 	
			}else{
				String[] str_date=DateUtilTool.dateDiff(startTime,endTime,new Integer(split_minute).intValue());
				System.out.println("Health360OrderTimeTask---------整个循环开始--------------------");
				String[] str= new String[2]; 
				for (int i = 0; i < str_date.length; i++) {
					str=DateUtilTool.getStartEnd_Minus_Minute(str_date[i], new Integer(minus_minute).intValue());
					//查询卖家交易数据
					health360OrderService.dealOrder_TradesSoldGetResponse(str,multiChannelOrderBatch);
					Thread.sleep(10000);//30秒
					//增量查询卖家交易
					health360OrderService.dealOrder_TradesSoldIncrementGetResponse(str,multiChannelOrderBatch);
				}
				System.out.println("Health360OrderTimeTask---------整个循环结束--------------------"); 	
			}			
			
/*			System.out.println("Health360OrderTimeTask--------开始--------------------");
			//3:是360健康
			Long multiChannelOrderBatch = BussinessUtil.multiChannelOrderBatch("3");
			String[] str=DateUtil.getStartEnd();
			//查询卖家交易数据
			health360OrderService.dealOrder_TradesSoldGetResponse(str,multiChannelOrderBatch);
			Thread.sleep(10000);//30秒
			//增量查询卖家交易
			health360OrderService.dealOrder_TradesSoldIncrementGetResponse(str,multiChannelOrderBatch);
			//TODO
			Thread.sleep(10000);//30秒
			//商家发货接口
			health360OrderService.deliveryInterface();
			//补充数据信息
			orderSupplementService.orderSupplement(ChannelConstant.CHANNEL_36,OrderState.STATE_1.getCode());
			//进行审单的
			orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_36,OrderState.STATE_1.getCode());
			System.out.println("Health360OrderTimeTask---------结束--------------------");*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
