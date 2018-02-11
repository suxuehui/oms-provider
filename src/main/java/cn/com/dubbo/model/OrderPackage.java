package cn.com.dubbo.model;

import java.io.Serializable;

public class OrderPackage implements Serializable {

	private static final long serialVersionUID = 1L;
	// package_id bigint(15) NOT NULL AUTO_INCREMENT
	private int package_id;// AUTO_INCREMENT
	private int stock_id;// 仓库id
	private String order_no;// 订单编号
	private String package_no;// 包裹编号默认的发货单号
	private int logistic_company_id;// 物流公司ID
	private String logistic_company_name;// 物流公司名称
	private String logistic_company_no;// 物流公司编码
	private String logistic_no;// 物流公司快递单号
	private int multi_channel_id;// 多渠道门店ID
	private String delivery_status;// 发货状态(N未发货 Y已发货)

	private int add_user_id;// 添加人
	private String add_time;// 添加时间
	private int edit_user_id;// 修改人
	private String edit_time;// 修改时间
	private String deal_status;// 处理状态(N未处理 Y已处理)
	
	private String delivery_time;//发货时间
	private String sync_time;//物流同步给第三方的时间

	public int getPackage_id() {
		return package_id;
	}

	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getPackage_no() {
		return package_no;
	}

	public void setPackage_no(String package_no) {
		this.package_no = package_no;
	}

	public int getLogistic_company_id() {
		return logistic_company_id;
	}

	public void setLogistic_company_id(int logistic_company_id) {
		this.logistic_company_id = logistic_company_id;
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

	public String getLogistic_no() {
		return logistic_no;
	}

	public void setLogistic_no(String logistic_no) {
		this.logistic_no = logistic_no;
	}

	public int getMulti_channel_id() {
		return multi_channel_id;
	}

	public void setMulti_channel_id(int multi_channel_id) {
		this.multi_channel_id = multi_channel_id;
	}

	public String getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
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

	public String getDeal_status() {
		return deal_status;
	}

	public void setDeal_status(String deal_status) {
		this.deal_status = deal_status;
	}

	public String getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}

	public String getSync_time() {
		return sync_time;
	}

	public void setSync_time(String sync_time) {
		this.sync_time = sync_time;
	}

}
