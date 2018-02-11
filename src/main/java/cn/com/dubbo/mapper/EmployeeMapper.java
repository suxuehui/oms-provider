package cn.com.dubbo.mapper;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.Employee;

@Repository
public interface EmployeeMapper extends BaseMapper<Employee, Integer>{

	Employee login(Employee emp);
}