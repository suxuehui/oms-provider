package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品和渠道对应关系，包含商品在第三方的结算价
 */
public class GoodsChannelPrice implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int gcpId;
	private int goodsId;//商品ID
    private String goodsNo;//
    private String goodsNo_69;//
    private int multiChannelId;//
    private String channelName;
    private BigDecimal ecPrice;//售价
    private BigDecimal costPrice;//成本价
    private BigDecimal supplyPrice;//结算价
    private String isDelete;
    private int addUserId;
    private String addTime;
    private int editUserId;
    private String editTime;
    
    private int availableStock;//可以库存，关联查询
    
	public int getGcpId() {
		return gcpId;
	}
	public void setGcpId(int gcpId) {
		this.gcpId = gcpId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
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
	public int getMultiChannelId() {
		return multiChannelId;
	}
	public void setMultiChannelId(int multiChannelId) {
		this.multiChannelId = multiChannelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public BigDecimal getEcPrice() {
		return ecPrice;
	}
	public void setEcPrice(BigDecimal ecPrice) {
		this.ecPrice = ecPrice;
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
	public int getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(BigDecimal supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
    
}