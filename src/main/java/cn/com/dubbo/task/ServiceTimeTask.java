package cn.com.dubbo.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.mapper.GoodsStockMapper;
import cn.com.dubbo.mapper.OrderDeliveryMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.service.inter.Employee666Service;
import cn.com.dubbo.service.order.Health360OrderService;

/**
 * 
 */
@Component
public class ServiceTimeTask {

	private static final Logger logger = Logger.getLogger(ServiceTimeTask.class);

	@Resource
	private Employee666Service employee666Service;

	/**
	 * 方法注释
	 */
	public void testTimeTask() {

		try {
			System.out.println("方法testTimeTask--------开始--------------------");
			employee666Service.updateGoodsChannelPriceList(0);
			System.out.println("方法testTimeTask---------结束--------------------");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public void testTimeTaskMethod() {
		try {
			System.out.println("定时器的测试");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

}
