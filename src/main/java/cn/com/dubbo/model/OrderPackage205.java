package cn.com.dubbo.model;

public class OrderPackage205 {
	private Long packageId;

	private Integer stockId;

	private String orderNo;

	private String packageNo;

	private Integer logisticCompanyId;

	private String logisticCompanyName;

	private String logisticCompanyNo;

	private String logisticNo;

	private Integer multiChannelId;

	private String deliveryStatus;

	private Integer addUserId;

	private String addTime;

	private Integer editUserId;

	private String editTime;

	private String deliveryTime;

	private String syncTime;

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo == null ? null : packageNo.trim();
	}

	public Integer getLogisticCompanyId() {
		return logisticCompanyId;
	}

	public void setLogisticCompanyId(Integer logisticCompanyId) {
		this.logisticCompanyId = logisticCompanyId;
	}

	public String getLogisticCompanyName() {
		return logisticCompanyName;
	}

	public void setLogisticCompanyName(String logisticCompanyName) {
		this.logisticCompanyName = logisticCompanyName == null ? null : logisticCompanyName.trim();
	}

	public String getLogisticCompanyNo() {
		return logisticCompanyNo;
	}

	public void setLogisticCompanyNo(String logisticCompanyNo) {
		this.logisticCompanyNo = logisticCompanyNo == null ? null : logisticCompanyNo.trim();
	}

	public String getLogisticNo() {
		return logisticNo;
	}

	public void setLogisticNo(String logisticNo) {
		this.logisticNo = logisticNo == null ? null : logisticNo.trim();
	}

	public Integer getMultiChannelId() {
		return multiChannelId;
	}

	public void setMultiChannelId(Integer multiChannelId) {
		this.multiChannelId = multiChannelId;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus == null ? null : deliveryStatus.trim();
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

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime == null ? null : deliveryTime.trim();
	}

	public String getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime == null ? null : syncTime.trim();
	}
}