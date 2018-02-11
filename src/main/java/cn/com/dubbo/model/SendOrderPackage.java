package cn.com.dubbo.model;

import java.io.Serializable;

public class SendOrderPackage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String order_no;// 订单编号

	private String logistic_company_name;// 物流公司名称

	private String logistic_no;// 物流公司快递单号

	private String add_time;// 添加时间

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getLogistic_company_name() {
		return logistic_company_name;
	}

	public void setLogistic_company_name(String logistic_company_name) {
		this.logistic_company_name = logistic_company_name;
	}

	public String getLogistic_no() {
		return logistic_no;
	}

	public void setLogistic_no(String logistic_no) {
		this.logistic_no = logistic_no;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

}
