package cn.com.dubbo.task;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import cn.com.dubbo.service.order.OutStockUpdateService;


/**
 * 平安和平安商城同步库存为一个接口
 */
@Component
public class OutStockUpdateServiceTimeTask {
	
	private static final Logger logger = Logger.getLogger(OutStockUpdateServiceTimeTask.class);
	
	@Resource
	private OutStockUpdateService outStockUpdateService;
	
	/**
	 * 方法注释
	 */
	public void testTimeTask(){
		try {
			if(!logger.isDebugEnabled()){
				System.out.println("OutStockUpdateServiceTimeTask--------开始--------------------");
				outStockUpdateService.paStockUpdate();
				System.out.println("OutStockUpdateServiceTimeTask---------结束--------------------");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
	}
}
