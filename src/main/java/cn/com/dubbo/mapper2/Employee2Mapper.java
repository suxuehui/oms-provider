package cn.com.dubbo.mapper2;

import org.springframework.stereotype.Repository;
import cn.com.dubbo.model2.Employee;

@Repository  
//@Transactional
public interface Employee2Mapper extends BaseDaoMapper2<Employee> {

	Employee login2(Employee emp);
	
	public void insertSelective2(Employee emp);
	
	public void insertSelective777(Employee emp);
	
	public void insertSelective888(Employee emp);
}