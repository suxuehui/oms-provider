package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderNo;// 订单编号
	private String tid;
	private String get_time;
	private int logLogisticCompanyId;// 物流公司id
	private long multiChannelOrderBatchId;// 渠道订单批次号
	private int multiChannelId;// 来源渠道id
	private String multiChannelOrderNo;// 渠道订单号
	private int orderType;// 订单类型(0.正常订单 1.补发订单)
	private String fromMedia;// 来源媒体,存储ios/android/第三方渠道url
	private int orderStatus;// 订单状态（sysCode）
	private String sendStatus;//发送状态
	private String orderDesc;// 订单状态描述
	private String memberId;// 会员唯一标示
	private String memberNick;// 买家昵称
	private String isPay;// 支付状态y/n
	private String valid;// 是否有效（y/n）
	private String paymentType;// 支付类型hdfk/kdfh/other
	private BigDecimal deliveryFeeOld;// 原运费金额
	private BigDecimal deliveryFee;// 实际运费金额
	private BigDecimal skuFee;// 货款金额（不含优惠）
	private BigDecimal orderPoints;// 积分抵扣
	private BigDecimal orderVouchers;// 优惠券抵扣
	private String couponNo;// 优惠券号
	private BigDecimal activityDiscountFee;// 活动优惠
	private BigDecimal paidFee;// 支付金额
	private BigDecimal orderPayFee;// 待支付金额
	private BigDecimal orderFee;// 订单金额
	private BigDecimal orderReturnFee;// 退款金额
	private BigDecimal orderRealFee;// 入账金额
	private BigDecimal supplyPrice;//结算价
	private String receiveUser;// 收货人
	private Date receiveTime;// 配送时间
	private int provinceId;// 省id
	private String provinceName;// 省名称
	private int cityId;// 市id
	private String cityName;// 市名称
	private int areaId;// 县id
	private String areaName;// 县名称
	private String receiveAddress;// 收货地址
	private String receiveFullAddress;// 完整收货地址
	private String longitude;// 收件人经度
	private String latitude;// 收件人维度
	private int receivePost;// 邮编
	private String receiveTel;// 收货电话
	private String receiveMobile;// 收货手机
	private String invoiceTitle;// 发票抬头
	private String invoiceContent;// 发票内容
	private String orderMsg;// 订单留言（用户）
	private String orderNotes;// 订单备注（客服）
	private int auditUserId;// 审核人
	private String auditTime;// 审核时间
	private String isLock;// 是否锁定（Y/N）
	private String lockTime;// 锁定时间
	private int lockUserId;// 锁定人
	private String commitTime;// 提交时间
	private String finishTime;// 订单完成时间
	private String cancelTime;// 订单取消时间
	private String platformCreateTime;// 平台创建时间
	private String platformEditTime;// 平台修改时间
	private int cancelUserId;// 取消人id
	private String cancelNotes;// 取消原因描述
	private String isDelete;// 是否删除（Y/N）
	private int addUserId;// 添加人
	private String addTime;// 添加时间
	private int editUserId;// 修改人
	private String editTime;// 修改时间
	private String haveCfy;// 是否包含处方药,N：是不包含，Y：是包含
	List<OrderItemModel> itemList;
	
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	
	public String getGet_time() {
		return get_time;
	}

	public void setGet_time(String get_time) {
		this.get_time = get_time;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getLogLogisticCompanyId() {
		return logLogisticCompanyId;
	}

	public void setLogLogisticCompanyId(int logLogisticCompanyId) {
		this.logLogisticCompanyId = logLogisticCompanyId;
	}

	public long getMultiChannelOrderBatchId() {
		return multiChannelOrderBatchId;
	}

	public void setMultiChannelOrderBatchId(long multiChannelOrderBatchId) {
		this.multiChannelOrderBatchId = multiChannelOrderBatchId;
	}

	public int getMultiChannelId() {
		return multiChannelId;
	}

	public void setMultiChannelId(int multiChannelId) {
		this.multiChannelId = multiChannelId;
	}

	public String getMultiChannelOrderNo() {
		return multiChannelOrderNo;
	}

	public void setMultiChannelOrderNo(String multiChannelOrderNo) {
		this.multiChannelOrderNo = multiChannelOrderNo;
	}

	public String getFromMedia() {
		return fromMedia;
	}

	public void setFromMedia(String fromMedia) {
		this.fromMedia = fromMedia;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
		this.couponNo = couponNo;
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
		this.receiveUser = receiveUser;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceiveFullAddress() {
		return receiveFullAddress;
	}

	public void setReceiveFullAddress(String receiveFullAddress) {
		this.receiveFullAddress = receiveFullAddress;
	}

	public int getReceivePost() {
		return receivePost;
	}

	public void setReceivePost(int receivePost) {
		this.receivePost = receivePost;
	}

	public String getReceiveTel() {
		return receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getOrderMsg() {
		return orderMsg;
	}

	public void setOrderMsg(String orderMsg) {
		this.orderMsg = orderMsg;
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}

	public int getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(int auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	public int getLockUserId() {
		return lockUserId;
	}

	public void setLockUserId(int lockUserId) {
		this.lockUserId = lockUserId;
	}

	public String getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getPlatformCreateTime() {
		return platformCreateTime;
	}

	public void setPlatformCreateTime(String platformCreateTime) {
		this.platformCreateTime = platformCreateTime;
	}

	public String getPlatformEditTime() {
		return platformEditTime;
	}

	public void setPlatformEditTime(String platformEditTime) {
		this.platformEditTime = platformEditTime;
	}

	public int getCancelUserId() {
		return cancelUserId;
	}

	public void setCancelUserId(int cancelUserId) {
		this.cancelUserId = cancelUserId;
	}

	public String getCancelNotes() {
		return cancelNotes;
	}

	public void setCancelNotes(String cancelNotes) {
		this.cancelNotes = cancelNotes;
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

	public String getHaveCfy() {
		return haveCfy;
	}

	public void setHaveCfy(String haveCfy) {
		this.haveCfy = haveCfy;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public BigDecimal getActivityDiscountFee() {
		return activityDiscountFee;
	}

	public void setActivityDiscountFee(BigDecimal activityDiscountFee) {
		this.activityDiscountFee = activityDiscountFee;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public BigDecimal getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(BigDecimal supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	public List<OrderItemModel> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItemModel> itemList) {
		this.itemList = itemList;
	}
	
}
