package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.Employee;


public interface Employee666Service {

	Employee login(Employee emp);
	public void saveEmployee666List(List<Employee> employeeList);
	public <T> void saveList(List<T> employeeList);
	
	public void updateGoodsChannelPriceList(int count);

}
