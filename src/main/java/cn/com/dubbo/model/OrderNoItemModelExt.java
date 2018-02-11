package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderNoItemModelExt implements Serializable {

	private static final long serialVersionUID = 1L;

	private String goodsNo;// 商品编码
	private String name;// 商品名称
	private BigDecimal price;// 商品成交价
	private Long amount; // 商品数量
	private BigDecimal priceDis;// 优惠金额
	private int goodsStatus;// 商品状态

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public BigDecimal getPriceDis() {
		return priceDis;
	}

	public void setPriceDis(BigDecimal priceDis) {
		this.priceDis = priceDis;
	}

	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

}
