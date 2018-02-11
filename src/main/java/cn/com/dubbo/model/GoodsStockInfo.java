package cn.com.dubbo.model;

import java.io.Serializable;

/**
 * 商品库存（作为备用表操作，后期切换成正式的）
 */
public class GoodsStockInfo implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

    private String goodsNo;//
    private String goodsNo_69;//
    private int stockInventory;//盘点库存值
    private int totalStock;//入库的总库存数
    private int usableStock;//可用的库存值
    private int soldTotalNum;//卖出的总数量
    private int deliverNum;//已发货数量
    private String lastTime;//最近更新时间
    
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
	public int getStockInventory() {
		return stockInventory;
	}
	public void setStockInventory(int stockInventory) {
		this.stockInventory = stockInventory;
	}
	public int getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}
	public int getUsableStock() {
		return usableStock;
	}
	public void setUsableStock(int usableStock) {
		this.usableStock = usableStock;
	}
	public int getSoldTotalNum() {
		return soldTotalNum;
	}
	public void setSoldTotalNum(int soldTotalNum) {
		this.soldTotalNum = soldTotalNum;
	}
	public int getDeliverNum() {
		return deliverNum;
	}
	public void setDeliverNum(int deliverNum) {
		this.deliverNum = deliverNum;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
    
}