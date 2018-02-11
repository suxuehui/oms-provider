package cn.com.dubbo.model;

import java.io.Serializable;

/**
 * 处方药的批次审核对应的订单
 */
public class AllocationTaskOrder implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int atOrderId;//

    private String orderNo;//

    private int preTaskNo;
    
    private String orderStatus;//处方药订单门店处理状态（1-待下单，2-已下单）

    private String orderAddTime;
    
    private int addUserId;
    
    private String addTime;
    
    private int editUserId;
    
    private String editTime;

	public int getAtOrderId() {
		return atOrderId;
	}

	public void setAtOrderId(int atOrderId) {
		this.atOrderId = atOrderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPreTaskNo() {
		return preTaskNo;
	}

	public void setPreTaskNo(int preTaskNo) {
		this.preTaskNo = preTaskNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderAddTime() {
		return orderAddTime;
	}

	public void setOrderAddTime(String orderAddTime) {
		this.orderAddTime = orderAddTime;
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