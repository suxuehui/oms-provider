package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 药医通中间库,所有的表都用该对象
 */
public class EOrderBatchNO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String eOrderNumber;

    private int productId;

    private String batchno;

    private int quantity;

    private String goodsNo;

    private String goods69;

    private String createTime;
    
    private int status;
    
    private int reduceAmount;
    
    private BigDecimal salePrice;
    
    private BigDecimal totalPrice;
    
    private int multiChannelId;
    
	public String geteOrderNumber() {
		return eOrderNumber;
	}

	public void seteOrderNumber(String eOrderNumber) {
		this.eOrderNumber = eOrderNumber;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoods69() {
		return goods69;
	}

	public void setGoods69(String goods69) {
		this.goods69 = goods69;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(int reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getMultiChannelId() {
		return multiChannelId;
	}

	public void setMultiChannelId(int multiChannelId) {
		this.multiChannelId = multiChannelId;
	}

}