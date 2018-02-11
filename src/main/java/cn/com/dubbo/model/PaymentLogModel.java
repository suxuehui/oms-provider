package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付日志
 */
public class PaymentLogModel implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int orderPaymentLogId;//id
    private String businessType;//业务类型
    private String businessId;//业务单据号(订单号)
    private int orderPaymentId;//支付方式ID
    private String orderPaymentName;//支付方式名称
    private String paymentNo;//支付流水
    private BigDecimal paidFee;//实付金额
    private String paymentTime;//支付时间
    private String isDelete;//是否删除(Y/N)
    private int addUserId;//添加人
    private String addTime;//添加时间
    private int editUserId;//修改人
    private String editTime;//修改时间
    
    
	public int getOrderPaymentLogId() {
		return orderPaymentLogId;
	}
	public void setOrderPaymentLogId(int orderPaymentLogId) {
		this.orderPaymentLogId = orderPaymentLogId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public int getOrderPaymentId() {
		return orderPaymentId;
	}
	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}
	public String getOrderPaymentName() {
		return orderPaymentName;
	}
	public void setOrderPaymentName(String orderPaymentName) {
		this.orderPaymentName = orderPaymentName;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public BigDecimal getPaidFee() {
		return paidFee;
	}
	public void setPaidFee(BigDecimal paidFee) {
		this.paidFee = paidFee;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
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