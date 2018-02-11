package cn.com.dubbo.bean;

public class Order {
	//getOrder_no getLogistic_no getLogistic_company_name getLogistic_company_no getDelivery_time
	
	private String order_no;
	private String logistic_no;
	private String logistic_company_name;
	private String logistic_company_no;
	private String delivery_time;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getLogistic_no() {
		return logistic_no;
	}
	public void setLogistic_no(String logistic_no) {
		this.logistic_no = logistic_no;
	}
	public String getLogistic_company_name() {
		return logistic_company_name;
	}
	public void setLogistic_company_name(String logistic_company_name) {
		this.logistic_company_name = logistic_company_name;
	}
	public String getLogistic_company_no() {
		return logistic_company_no;
	}
	public void setLogistic_company_no(String logistic_company_no) {
		this.logistic_company_no = logistic_company_no;
	}
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	
	
	
}
