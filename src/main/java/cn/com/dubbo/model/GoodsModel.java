package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品表
 */
public class GoodsModel implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int goodsId;//id
	private String goodsNo;//商品编码
	private String goodsNo_69;//商品69码
	private String goodsClassId;//商品分类id
	private String goodsType;//商品类型(general/group/gift)
	private String name;//商品名称
	private String standard;//规格
	private String medType;//剂型
	private String permitcode;//批准文号
	private String addUser;//录入人
	private String addTime;//录入时间
	private String otcType;//非处方药 分甲类、乙类
	private String otcFlag;//是否处方药 0不是 1是
	private String producer;//生产厂家
	private String goodsStatus;//商品状态 y=正常 n=停用
	
	//商品的成本和库存
	private BigDecimal costPriceAverage;//商品成本价格
	private int availableStock;//可用库存
	
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
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
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
	public String getMedType() {
		return medType;
	}
	public void setMedType(String medType) {
		this.medType = medType;
	}
	public String getPermitcode() {
		return permitcode;
	}
	public void setPermitcode(String permitcode) {
		this.permitcode = permitcode;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getOtcType() {
		return otcType;
	}
	public void setOtcType(String otcType) {
		this.otcType = otcType;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(String goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	public String getOtcFlag() {
		return otcFlag;
	}
	public void setOtcFlag(String otcFlag) {
		this.otcFlag = otcFlag;
	}
	public BigDecimal getCostPriceAverage() {
		return costPriceAverage;
	}
	public void setCostPriceAverage(BigDecimal costPriceAverage) {
		this.costPriceAverage = costPriceAverage;
	}
	public int getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

}