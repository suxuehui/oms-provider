package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemModelExt implements Serializable {

	private static final long serialVersionUID = 1L;

	private String goodsNo;// 商品编码
	private String name;// 商品名称
	private BigDecimal price;// 商品成交价
	private Long amount; // 商品数量
	private BigDecimal priceDis;// 优惠金额
	private String cid;// 交易商品对应的类目
	private String des;// 是否发货描述，已发、未发
	private String logistic_company_name;// 物流公司名称
	private String logistic_no;// 物流公司快递单号
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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getLogistic_company_name() {
		return logistic_company_name;
	}

	public void setLogistic_company_name(String logistic_company_name) {
		this.logistic_company_name = logistic_company_name;
	}

	public String getLogistic_no() {
		return logistic_no;
	}

	public void setLogistic_no(String logistic_no) {
		this.logistic_no = logistic_no;
	}

	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

}
