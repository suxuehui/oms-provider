package cn.com.dubbo.model;

import java.io.Serializable;

public class LogisticCompany implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int logisticCompanyId;//物流公司id
	private String companyNo;//公司编码
	private String companyName;//公司名称
	private String logisticType;//物流类型(air/auto/water/railway)
	private String isDefault;//是否默认(y/n)
	private int priority;//优先级(从1开始,不能是0)
	private int sortNo;//显示顺序
	private String companyNotes;//公司描述
	private String website;//公司网址
	private String companyAddress;//公司地址
	private String queryUrl;//货单跟踪url
	private String contactUser;//联系人
	private String contactTel;//联系电话
	private String isDelete;//是否删除(y/n)
	private int addUserId;//添加人
	private String addTime;//添加时间
	private int editUserId;//修改人
	private String editTime;//修改时间
	
	
	public int getLogisticCompanyId() {
		return logisticCompanyId;
	}
	public void setLogisticCompanyId(int logisticCompanyId) {
		this.logisticCompanyId = logisticCompanyId;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLogisticType() {
		return logisticType;
	}
	public void setLogisticType(String logisticType) {
		this.logisticType = logisticType;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public String getCompanyNotes() {
		return companyNotes;
	}
	public void setCompanyNotes(String companyNotes) {
		this.companyNotes = companyNotes;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getQueryUrl() {
		return queryUrl;
	}
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	public String getContactUser() {
		return contactUser;
	}
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
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
