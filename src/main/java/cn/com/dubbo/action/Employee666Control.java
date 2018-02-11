package cn.com.dubbo.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.model.Employee;
import cn.com.dubbo.service.inter.Employee666Service;
import cn.com.dubbo.service.order.PsOrderService;


@Controller
@RequestMapping("employee666Control")
public class Employee666Control {
	
	private Logger logger = Logger.getLogger(Employee666Control.class);
	
	@Autowired
	private Employee666Service employee666Service;
	
//	@Autowired
//	private PaOrderService paOrderService;
	
	@Autowired
	private PsOrderService psOrderService;
	
	
	
	
	@RequestMapping(value="test")
	@ResponseBody
	public String test(){
		
		psOrderService.dealOrder("1474104600", "1474104720", false);
		
		return "login";
	}	
	
	
//	@RequestMapping("checkLogin")
//	@ResponseBody
	
	@RequestMapping(value="login666")
	@ResponseBody
	public String login(@RequestParam String empName,@RequestParam String empPass,
			Model model){
		
//		paOrderService.dealOrder();
		
		psOrderService.dealOrder("1474104600", "1474104720", false);
		
		Employee emp = new Employee();
		emp.setEmpage("26");
		emp.setEmpname("root");
		emp.setEmppass("root");
		emp.setEmpsex("1");
		Employee e = employee666Service.login(emp);
		if(e == null){
			return "index";
		}
		model.addAttribute("emp",emp);
		System.out.println(e);
		return "login";
	}	
	
//	
//	@RequestMapping("findEmp")
//	public String findAllEmp(){
//		Employee emp = this.employeeService.selectByPrimaryKey(1);
//		System.out.println(emp);
//		return "index";
//	}
//	
//	@RequestMapping("saveEmp")
//	public String saveEmp(){
//		Employee emp = new Employee();
//		emp.setEmpAge("26");
//		emp.setEmpName("root");
//		emp.setEmpPass("root");
//		emp.setEmpSex("1");
//		int num = this.employeeService.insert(emp);
//		System.out.println(num);
//		return "index";
//	}
}
