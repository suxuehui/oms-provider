package cn.com.dubbo.bean;

public class OrderDeliveryModel {
	private String orderDeliveryNo;

	private String packageNo;

	private String orderNo;

	private Integer stockId;

	private Integer logisticCompanyId;

	private String logisticNo;

	private String deliveryStatus;

	private String isDelete;

	private Integer addUserId;

	private String addTime;

	private Integer editUserId;

	private String editTime;

	private int count;

	public String getOrderDeliveryNo() {
		return orderDeliveryNo;
	}

	public void setOrderDeliveryNo(String orderDeliveryNo) {
		this.orderDeliveryNo = orderDeliveryNo == null ? null : orderDeliveryNo.trim();
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo == null ? null : packageNo.trim();
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Integer getLogisticCompanyId() {
		return logisticCompanyId;
	}

	public void setLogisticCompanyId(Integer logisticCompanyId) {
		this.logisticCompanyId = logisticCompanyId;
	}

	public String getLogisticNo() {
		return logisticNo;
	}

	public void setLogisticNo(String logisticNo) {
		this.logisticNo = logisticNo == null ? null : logisticNo.trim();
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus == null ? null : deliveryStatus.trim();
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime == null ? null : addTime.trim();
	}

	public Integer getEditUserId() {
		return editUserId;
	}

	public void setEditUserId(Integer editUserId) {
		this.editUserId = editUserId;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime == null ? null : editTime.trim();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}