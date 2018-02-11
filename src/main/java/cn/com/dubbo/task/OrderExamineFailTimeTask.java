package cn.com.dubbo.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.service.order.OrderSupplementService;


/**
 * 订单审核失败重新审核
 */
@Component
public class OrderExamineFailTimeTask {
	
	private static final Logger logger = Logger.getLogger(OrderExamineFailTimeTask.class);
	
	@Resource
	private OrderSupplementService orderSupplementService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderStepService orderStepService;
	
	/**
	 * 方法注释
	 */
	public void execute(){
		try {
			if(!logger.isDebugEnabled()){
				
				System.out.println("订单审核失败重新审核--------开始--------------------");
				//删除审核失败的订单，重新执行,平安的
//				orderService.deleteExamineFailOrder();
				
				//补充数据信息
//				orderSupplementService.orderSupplement(ChannelConstant.CHANNEL_PA,OrderState.STATE_3.getCode());
//				orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_PA,OrderState.STATE_3.getCode());
				
//				orderSupplementService.orderSupplement(ChannelConstant.CHANNEL_PS,OrderState.STATE_1.getCode());
//				orderStepService.orderNotRXExamine(ChannelConstant.CHANNEL_PS,OrderState.STATE_3.getCode());
				
				System.out.println("订单审核失败重新审核---------结束--------------------");
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
	}
}
