package cn.com.dubbo.model;

import java.io.Serializable;

public class ExceptionInfo implements Serializable {
  
//	private static final long serialVersionUID = 591193130278760068L;

	private Integer id;
	private String order_no;
	private String Logistic_no;//订单明细id
    private String Logistic_company_name;
    private Integer Logistic_company_id;
    private String Delivery_time;
    private String add_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getLogistic_no() {
		return Logistic_no;
	}
	public void setLogistic_no(String logistic_no) {
		Logistic_no = logistic_no;
	}
	public String getLogistic_company_name() {
		return Logistic_company_name;
	}
	public void setLogistic_company_name(String logistic_company_name) {
		Logistic_company_name = logistic_company_name;
	}
	
	public Integer getLogistic_company_id() {
		return Logistic_company_id;
	}
	public void setLogistic_company_id(Integer logistic_company_id) {
		Logistic_company_id = logistic_company_id;
	}
	public String getDelivery_time() {
		return Delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		Delivery_time = delivery_time;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}


}