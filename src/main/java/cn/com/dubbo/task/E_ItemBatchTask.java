package cn.com.dubbo.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.dubbo.service.order.E_orderstateService;

/**
 * 
 */
@Component
public class E_ItemBatchTask {

	private static final Logger logger = Logger.getLogger(E_ItemBatchTask.class);

	@Resource
	private E_orderstateService e_orderstateService;

	/**
	 * 方法注释
	 */
	public void executeTimeTask() {

		try {
			System.out.println("更新药易通数据----------开始-----------");
			e_orderstateService.get();
			System.out.println("更新药易通数据----------结束-----------");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

	}

}
