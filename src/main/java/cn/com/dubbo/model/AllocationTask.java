package cn.com.dubbo.model;

import java.io.Serializable;

/**
 * 处方药的批次审核
 */
public class AllocationTask implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int preTaskNo;//批次id

    private int taskStatus;//调拨状态  1-待调拨，2-已调拨，3-开单中，4-已开单）

    private String taskAddTime;
    
    private int addUserId;

    private String addTime;
    
    private int editUserId;
    
    private String editTime;

	public int getPreTaskNo() {
		return preTaskNo;
	}

	public void setPreTaskNo(int preTaskNo) {
		this.preTaskNo = preTaskNo;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskAddTime() {
		return taskAddTime;
	}

	public void setTaskAddTime(String taskAddTime) {
		this.taskAddTime = taskAddTime;
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