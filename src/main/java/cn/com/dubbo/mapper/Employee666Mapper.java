package cn.com.dubbo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.Employee;
//
//@Repository
//public interface Employee666Mapper extends BaseMapper<Employee, Integer>{
//
//	Employee login(Employee emp);
//}

@Repository  
//@Transactional
public interface Employee666Mapper extends BaseDaoMapper<Employee> {

	Employee login666(Employee emp);
	
	public void insertSelective666(Employee emp);
	
	public void insertSelective777(Employee emp);
	
	public void insertSelective888(Employee emp);
}