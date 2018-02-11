package cn.com.dubbo.model2;

import java.io.Serializable;
import java.math.BigDecimal;

public class E_Pstock implements Serializable {
	private String productId;

	private BigDecimal price1;

	private BigDecimal price2;

	private BigDecimal price3;

	private String batchno;

	private Long quantity;

	private String makedate;

	private String validdate;

	private String instoretime;

	private String supplier;

	private Integer status;

	private Integer reduceAmount;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId == null ? null : productId.trim();
	}

	public BigDecimal getPrice1() {
		return price1;
	}

	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}

	public BigDecimal getPrice2() {
		return price2;
	}

	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}

	public BigDecimal getPrice3() {
		return price3;
	}

	public void setPrice3(BigDecimal price3) {
		this.price3 = price3;
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

	public String getMakedate() {
		return makedate;
	}

	public void setMakedate(String makedate) {
		this.makedate = makedate == null ? null : makedate.trim();
	}

	public String getValiddate() {
		return validdate;
	}

	public void setValiddate(String validdate) {
		this.validdate = validdate == null ? null : validdate.trim();
	}

	public String getInstoretime() {
		return instoretime;
	}

	public void setInstoretime(String instoretime) {
		this.instoretime = instoretime == null ? null : instoretime.trim();
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier == null ? null : supplier.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(Integer reduceAmount) {
		this.reduceAmount = reduceAmount;
	}
}