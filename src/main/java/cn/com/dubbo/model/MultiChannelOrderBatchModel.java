package cn.com.dubbo.model;

import java.io.Serializable;

public class MultiChannelOrderBatchModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private	long multiChannelOrderBatchId;//渠道订单批次号
	private	int	multiChannelId;//多渠道门店id
	private int importUserId;//订单导入人ID
	private String importTime;//导入开始时间
	private int orderAmount;//订单数量
	private String paraLog;//参数日志
	private String isDelete;//是否删除
	private int addUserId;//添加人ID
	private String addTime;//添加时间
	private int editUserId;//修改人ID
	private String editTime;//修改时间
	
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
	public int getImportUserId() {
		return importUserId;
	}
	public void setImportUserId(int importUserId) {
		this.importUserId = importUserId;
	}
	public String getImportTime() {
		return importTime;
	}
	public void setImportTime(String importTime) {
		this.importTime = importTime;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getParaLog() {
		return paraLog;
	}
	public void setParaLog(String paraLog) {
		this.paraLog = paraLog;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
