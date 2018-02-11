package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品销售库存和成本价信息
 */
public class GoodsSaleInfoModel implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private String goodsNo;//编码

	private String goodsNo_69;//69码
	
	private BigDecimal costPriceAverage;//平均成本价
	
	private String costPriceTime;//成本同步时间
	
	private int availableStock;//可用库存

	private String availableStockTime;//可用库存更新时间

	
	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsNo_69() {
		return goodsNo_69;
	}

	public void setGoodsNo_69(String goodsNo_69) {
		this.goodsNo_69 = goodsNo_69;
	}

	public BigDecimal getCostPriceAverage() {
		return costPriceAverage;
	}

	public void setCostPriceAverage(BigDecimal costPriceAverage) {
		this.costPriceAverage = costPriceAverage;
	}

	public String getCostPriceTime() {
		return costPriceTime;
	}

	public void setCostPriceTime(String costPriceTime) {
		this.costPriceTime = costPriceTime;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public String getAvailableStockTime() {
		return availableStockTime;
	}

	public void setAvailableStockTime(String availableStockTime) {
		this.availableStockTime = availableStockTime;
	}
	
	
}