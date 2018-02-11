package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品组合信息表
 */
public class GoodsGroupItemModel implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int groupItemId;
    private String goodsNo;//父商品编号
    private String goodsNo_69;//父商品69码
    private String subGoodsNo;//子商品编号
    private String subGoodsNo_69;//子商品69码
    private int amount;//子商品数量
    private BigDecimal proportion;
    private Date validateDate;
    private String isDelete;
    private int addUserId;
    private String addTime;//添加时间
    private int editUserId;
    private String editTime;
    private String goodsName;
    
    //查询字段
    private int otcFlag;//是否是处方药  0不是  1是
    private String name;//商品名称
    private String standard;//商品规格
    private BigDecimal costPriceAverage;//商品成本价
    private BigDecimal supplyPrice;//结算价
    
    
	public BigDecimal getCostPriceAverage() {
		return costPriceAverage;
	}
	public void setCostPriceAverage(BigDecimal costPriceAverage) {
		this.costPriceAverage = costPriceAverage;
	}
	public int getGroupItemId() {
		return groupItemId;
	}
	public void setGroupItemId(int groupItemId) {
		this.groupItemId = groupItemId;
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
	public String getSubGoodsNo() {
		return subGoodsNo;
	}
	public void setSubGoodsNo(String subGoodsNo) {
		this.subGoodsNo = subGoodsNo;
	}
	public String getSubGoodsNo_69() {
		return subGoodsNo_69;
	}
	public void setSubGoodsNo_69(String subGoodsNo_69) {
		this.subGoodsNo_69 = subGoodsNo_69;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BigDecimal getProportion() {
		return proportion;
	}
	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}
	public Date getValidateDate() {
		return validateDate;
	}
	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
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
	public int getOtcFlag() {
		return otcFlag;
	}
	public void setOtcFlag(int otcFlag) {
		this.otcFlag = otcFlag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public BigDecimal getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(BigDecimal supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
    
}