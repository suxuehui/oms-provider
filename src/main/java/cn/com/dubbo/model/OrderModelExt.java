package cn.com.dubbo.model;

import java.io.Serializable;
import java.util.List;

public class OrderModelExt implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderNo;// 订单编号
	private String receiveUser;// 收货人
	private int provinceId;// 省id
	private String provinceName;// 省名称
	private int cityId;// 市id
	private String cityName;// 市名称
	private int areaId;// 县id
	private String areaName;// 县名称
	private String receiveFullAddress;// 完整收货地址
	private int receivePost;// 邮编
	private String receiveTel;// 收货电话
	private String receiveMobile;// 收货手机
	private String invoiceTitle;// 发票抬头
	private String invoiceContent;// 发票内容
	private String orderDesc;// 订单状态描述
	private int orderStatus;// 订单状态（sysCode）
	private List<OrderItemModelExt> goods;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public List<OrderItemModelExt> getGoods() {
		return goods;
	}

	public void setGoods(List<OrderItemModelExt> goods) {
		this.goods = goods;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

}
