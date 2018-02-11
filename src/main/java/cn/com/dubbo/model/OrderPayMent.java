package cn.com.dubbo.model;

import java.io.Serializable;

public class OrderPayMent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int orderPaymentId;//支付方式ID
	private String paymentNo;//支付编码
	private String paymentName;//支付名称
	private String logoUrl;//支付方式 logo
	private String accountCycle;//结算账期
	private String rate;//手续费率
	private String payUrl;//支付接口地址
	private String queryUrl;//查询接口地址
	
	private String returnUrl;//退款接口地址
	private String adminEmail;//管理员邮箱
	private String isEnable;//启用状态(Y/N)
	private String isDelete;//是否删除(Y/N)
	private int addUserId;//添加人
	private String addTime;//添加时间
	private int editUserId;//修改人
	private String editTime;//修改时间
	
	public int getOrderPaymentId() {
		return orderPaymentId;
	}
	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getAccountCycle() {
		return accountCycle;
	}
	public void setAccountCycle(String accountCycle) {
		this.accountCycle = accountCycle;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getPayUrl() {
		return payUrl;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	public String getQueryUrl() {
		return queryUrl;
	}
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public int getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(int addUserId) {
		this.addUserId = addUserId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getEditUserId() {
		return editUserId;
	}
	public void setEditUserId(int editUserId) {
		this.editUserId = editUserId;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	
}
