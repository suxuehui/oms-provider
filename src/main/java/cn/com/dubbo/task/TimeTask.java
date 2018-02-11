package cn.com.dubbo.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.dubbo.mapper.Employee666Mapper;
import cn.com.dubbo.mapper2.Employee2Mapper;

/**
 * 
 */
@Component
public class TimeTask {
	
	private static final Logger logger = Logger.getLogger(TimeTask.class);
	
	@Autowired
	private Employee666Mapper employee666Mapper;
	
	@Autowired
	private Employee2Mapper employee2Mapper;
	
	/**
	 * 方法注释
	 */
	public void executeTimeTask() {
		try {
			System.out.println("TimeTask--------开始--------------------");
			
			cn.com.dubbo.model.Employee employee666=new cn.com.dubbo.model.Employee();
			employee666.setEmpid(103);
			employee666.setEmpname("李四");
			employee666Mapper.insertSelective666(employee666);
			
			
			cn.com.dubbo.model2.Employee employee=new cn.com.dubbo.model2.Employee();
			employee.setEmpid(104);
			employee.setEmpname("王五");
			employee2Mapper.insertSelective2(employee);
			System.out.println("TimeTask---------结束--------------------");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
}
