package cn.com.dubbo.model;

import java.io.Serializable;

/**
 * 订单日志表
 */
public class OrderLogModel implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int orderLogId;
    private String orderNo;
    private int orderStateId;//订单流程节点(代码表)
    private String orderLogType;//日志类型(public/private)
    private String logContent;//日志内容
    private String logTime;//日志时间
    private int logUserId;//操作人
    private String isDelete;//is_delete
    private int addUserId;//添加人
    private String addTime;//添加时间
    private int editUserId;//修改人
    private String editTime;//修改时间
    
	public int getOrderLogId() {
		return orderLogId;
	}
	public void setOrderLogId(int orderLogId) {
		this.orderLogId = orderLogId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderStateId() {
		return orderStateId;
	}
	public void setOrderStateId(int orderStateId) {
		this.orderStateId = orderStateId;
	}
	public String getOrderLogType() {
		return orderLogType;
	}
	public void setOrderLogType(String orderLogType) {
		this.orderLogType = orderLogType;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public int getLogUserId() {
		return logUserId;
	}
	public void setLogUserId(int logUserId) {
		this.logUserId = logUserId;
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