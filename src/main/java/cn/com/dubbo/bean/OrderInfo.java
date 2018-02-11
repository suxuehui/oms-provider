package cn.com.dubbo.bean;

import java.math.BigDecimal;

public class OrderInfo {
	private String orderNo;

	private Integer logLogisticCompanyId;

	private Long multiChannelOrderBatchId;

	private Integer multiChannelId;

	private String fromMedia;

	private String multiChannelOrderNo;

	private Integer orderType;

	private Integer orderStatus;

	private String valid;

	private String memberId;

	private String memberNick;

	private String isPay;

	private String paymentType;

	private BigDecimal deliveryFeeOld;

	private BigDecimal deliveryFee;

	private BigDecimal skuFee;

	private BigDecimal orderPoints;

	private BigDecimal orderVouchers;

	private String couponNo;

	private BigDecimal activityDiscountFee;

	private BigDecimal paidFee;

	private BigDecimal orderPayFee;

	private BigDecimal orderFee;

	private BigDecimal orderReturnFee;

	private BigDecimal orderRealFee;

	private BigDecimal supplyPrice;

	private String receiveUser;

	private String receiveTime;

	private Integer provinceId;

	private String provinceName;

	private Integer cityId;

	private String cityName;

	private Integer areaId;

	private String areaName;

	private String receiveAddress;

	private String receiveFullAddress;

	private String receiveLongitude;

	private String receiveLatitude;

	private Integer receivePost;

	private String receiveTel;

	private String receiveMobile;

	private String invoiceTitle;

	private String invoiceContent;

	private String orderMsg;

	private String orderNotes;

	private Integer auditUserId;

	private String auditTime;

	private String isLock;

	private String lockTime;

	private Integer lockUserId;

	private String commitTime;

	private String finishTime;

	private String cancelTime;

	private String platformCreateTime;

	private String platformEditTime;

	private Integer cancelUserId;

	private String cancelNotes;

	private String isDelete;

	private Integer addUserId;

	private String addTime;

	private Integer editUserId;

	private String editTime;

	private String haveCfy;

	private String sendStatus;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	public Integer getLogLogisticCompanyId() {
		return logLogisticCompanyId;
	}

	public void setLogLogisticCompanyId(Integer logLogisticCompanyId) {
		this.logLogisticCompanyId = logLogisticCompanyId;
	}

	public Long getMultiChannelOrderBatchId() {
		return multiChannelOrderBatchId;
	}

	public void setMultiChannelOrderBatchId(Long multiChannelOrderBatchId) {
		this.multiChannelOrderBatchId = multiChannelOrderBatchId;
	}

	public Integer getMultiChannelId() {
		return multiChannelId;
	}

	public void setMultiChannelId(Integer multiChannelId) {
		this.multiChannelId = multiChannelId;
	}

	public String getFromMedia() {
		return fromMedia;
	}

	public void setFromMedia(String fromMedia) {
		this.fromMedia = fromMedia == null ? null : fromMedia.trim();
	}

	public String getMultiChannelOrderNo() {
		return multiChannelOrderNo;
	}

	public void setMultiChannelOrderNo(String multiChannelOrderNo) {
		this.multiChannelOrderNo = multiChannelOrderNo == null ? null : multiChannelOrderNo.trim();
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid == null ? null : valid.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId == null ? null : memberId.trim();
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick == null ? null : memberNick.trim();
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay == null ? null : isPay.trim();
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType == null ? null : paymentType.trim();
	}

	public BigDecimal getDeliveryFeeOld() {
		return deliveryFeeOld;
	}

	public void setDeliveryFeeOld(BigDecimal deliveryFeeOld) {
		this.deliveryFeeOld = deliveryFeeOld;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getSkuFee() {
		return skuFee;
	}

	public void setSkuFee(BigDecimal skuFee) {
		this.skuFee = skuFee;
	}

	public BigDecimal getOrderPoints() {
		return orderPoints;
	}

	public void setOrderPoints(BigDecimal orderPoints) {
		this.orderPoints = orderPoints;
	}

	public BigDecimal getOrderVouchers() {
		return orderVouchers;
	}

	public void setOrderVouchers(BigDecimal orderVouchers) {
		this.orderVouchers = orderVouchers;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo == null ? null : couponNo.trim();
	}

	public BigDecimal getActivityDiscountFee() {
		return activityDiscountFee;
	}

	public void setActivityDiscountFee(BigDecimal activityDiscountFee) {
		this.activityDiscountFee = activityDiscountFee;
	}

	public BigDecimal getPaidFee() {
		return paidFee;
	}

	public void setPaidFee(BigDecimal paidFee) {
		this.paidFee = paidFee;
	}

	public BigDecimal getOrderPayFee() {
		return orderPayFee;
	}

	public void setOrderPayFee(BigDecimal orderPayFee) {
		this.orderPayFee = orderPayFee;
	}

	public BigDecimal getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(BigDecimal orderFee) {
		this.orderFee = orderFee;
	}

	public BigDecimal getOrderReturnFee() {
		return orderReturnFee;
	}

	public void setOrderReturnFee(BigDecimal orderReturnFee) {
		this.orderReturnFee = orderReturnFee;
	}

	public BigDecimal getOrderRealFee() {
		return orderRealFee;
	}

	public void setOrderRealFee(BigDecimal orderRealFee) {
		this.orderRealFee = orderRealFee;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser == null ? null : receiveUser.trim();
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime == null ? null : receiveTime.trim();
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName == null ? null : provinceName.trim();
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
	}

	public String getReceiveFullAddress() {
		return receiveFullAddress;
	}

	public void setReceiveFullAddress(String receiveFullAddress) {
		this.receiveFullAddress = receiveFullAddress == null ? null : receiveFullAddress.trim();
	}

	public String getReceiveLongitude() {
		return receiveLongitude;
	}

	public void setReceiveLongitude(String receiveLongitude) {
		this.receiveLongitude = receiveLongitude == null ? null : receiveLongitude.trim();
	}

	public String getReceiveLatitude() {
		return receiveLatitude;
	}

	public void setReceiveLatitude(String receiveLatitude) {
		this.receiveLatitude = receiveLatitude == null ? null : receiveLatitude.trim();
	}

	public Integer getReceivePost() {
		return receivePost;
	}

	public void setReceivePost(Integer receivePost) {
		this.receivePost = receivePost;
	}

	public String getReceiveTel() {
		return receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel == null ? null : receiveTel.trim();
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile == null ? null : receiveMobile.trim();
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
	}

	public String getOrderMsg() {
		return orderMsg;
	}

	public void setOrderMsg(String orderMsg) {
		this.orderMsg = orderMsg == null ? null : orderMsg.trim();
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes == null ? null : orderNotes.trim();
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime == null ? null : auditTime.trim();
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock == null ? null : isLock.trim();
	}

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime == null ? null : lockTime.trim();
	}

	public Integer getLockUserId() {
		return lockUserId;
	}

	public void setLockUserId(Integer lockUserId) {
		this.lockUserId = lockUserId;
	}

	public String getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime == null ? null : commitTime.trim();
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime == null ? null : finishTime.trim();
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime == null ? null : cancelTime.trim();
	}

	public String getPlatformCreateTime() {
		return platformCreateTime;
	}

	public void setPlatformCreateTime(String platformCreateTime) {
		this.platformCreateTime = platformCreateTime == null ? null : platformCreateTime.trim();
	}

	public String getPlatformEditTime() {
		return platformEditTime;
	}

	public void setPlatformEditTime(String platformEditTime) {
		this.platformEditTime = platformEditTime == null ? null : platformEditTime.trim();
	}

	public Integer getCancelUserId() {
		return cancelUserId;
	}

	public void setCancelUserId(Integer cancelUserId) {
		this.cancelUserId = cancelUserId;
	}

	public String getCancelNotes() {
		return cancelNotes;
	}

	public void setCancelNotes(String cancelNotes) {
		this.cancelNotes = cancelNotes == null ? null : cancelNotes.trim();
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

	public String getHaveCfy() {
		return haveCfy;
	}

	public void setHaveCfy(String haveCfy) {
		this.haveCfy = haveCfy == null ? null : haveCfy.trim();
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public BigDecimal getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(BigDecimal supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

}