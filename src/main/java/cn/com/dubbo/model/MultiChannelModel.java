package cn.com.dubbo.model;

import java.io.Serializable;

public class MultiChannelModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int multiChannelId;//度渠道门店ID
	private String channelCode;//渠道编号
	private String channelName;//渠道名称
	private String channelUrl;//店铺URL
	private String channelNotes;//门店描述
	private String sessionKey;//SESSION凭证
	private String appKey;//APP凭证
	private String appPwd;//APP密码
	private String appKey1;//接口备用参数1
	private String appKey2;//接口备用参数2
	private String appKey3;//接口备用参数3
	private String isEnable;//启用状态(Y/N)
	private String isDelete;//是否删除(Y/N)
	private int addUserId;//添加人
	private String addTime;//添加时间
	private int editUserId;//修改人
	private String editTime;//修改时间
	
	public int getMultiChannelId() {
		return multiChannelId;
	}
	public void setMultiChannelId(int multiChannelId) {
		this.multiChannelId = multiChannelId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public String getChannelNotes() {
		return channelNotes;
	}
	public void setChannelNotes(String channelNotes) {
		this.channelNotes = channelNotes;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppPwd() {
		return appPwd;
	}
	public void setAppPwd(String appPwd) {
		this.appPwd = appPwd;
	}
	public String getAppKey1() {
		return appKey1;
	}
	public void setAppKey1(String appKey1) {
		this.appKey1 = appKey1;
	}
	public String getAppKey2() {
		return appKey2;
	}
	public void setAppKey2(String appKey2) {
		this.appKey2 = appKey2;
	}
	public String getAppKey3() {
		return appKey3;
	}
	public void setAppKey3(String appKey3) {
		this.appKey3 = appKey3;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
