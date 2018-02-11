package cn.com.dubbo.model;

import java.io.Serializable;

public class OrderDelivery implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private	String	order_delivery_no;//发货单编号，如未拆单则默认订单号
	private	String	package_no;//包裹编号，合单回传
	private String	order_no;//订单编号
	private	int		stock_id;//仓库id
	
	private	int	logistic_company_id;//物流公司ID
	private	String	logistic_no;//物流快递单号
	private	String		delivery_status;//发货单状态（预留）
	private String 		is_delete;//是否删除(y/n)
	
	private int add_user_id;//添加人
	private String add_time;//添加时间
	private int edit_user_id;//修改人
	private String edit_time;//修改时间
	public String getOrder_delivery_no() {
		return order_delivery_no;
	}
	public void setOrder_delivery_no(String order_delivery_no) {
		this.order_delivery_no = order_delivery_no;
	}
	public String getPackage_no() {
		return package_no;
	}
	public void setPackage_no(String package_no) {
		this.package_no = package_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public int getLogistic_company_id() {
		return logistic_company_id;
	}
	public void setLogistic_company_id(int logistic_company_id) {
		this.logistic_company_id = logistic_company_id;
	}
	public String getLogistic_no() {
		return logistic_no;
	}
	public void setLogistic_no(String logistic_no) {
		this.logistic_no = logistic_no;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public int getAdd_user_id() {
		return add_user_id;
	}
	public void setAdd_user_id(int add_user_id) {
		this.add_user_id = add_user_id;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public int getEdit_user_id() {
		return edit_user_id;
	}
	public void setEdit_user_id(int edit_user_id) {
		this.edit_user_id = edit_user_id;
	}
	public String getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(String edit_time) {
		this.edit_time = edit_time;
	}
	
}
