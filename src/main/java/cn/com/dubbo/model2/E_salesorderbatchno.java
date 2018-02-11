package cn.com.dubbo.model2;

import java.io.Serializable;

public class E_salesorderbatchno implements Serializable{
	private String eOrdernumber; // ************要操作的字段*********

	private String pId; // ***********要操作的字段***********

	private String batchno;

	protected Long quantity;

	private String comment;

	private Integer deleted;

	public String geteOrdernumber() {
		return eOrdernumber;
	}

	public void seteOrdernumber(String eOrdernumber) {
		this.eOrdernumber = eOrdernumber == null ? null : eOrdernumber.trim();
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId == null ? null : pId.trim();
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno == null ? null : batchno.trim();
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}