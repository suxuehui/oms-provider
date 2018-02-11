package cn.com.dubbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemModel implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;

	private long orderItemId;// 订单明细id
	private String orderNo;// 订单编号
	private String orderDeliveryNo;// 发货单编号
	private String goodsType; // 商品类型（other/rx(处方药))
	private String goodsListType;// 明细类型(general/group/gift)

	private String goodsNo;// 商品编码
	private String goodsNo_69;// 商品69码
	private String name;// 商品名称
	private String unit;// 商品单位
	private String standard;// 商品规格
	private BigDecimal supplyPrice;//结算价
	private BigDecimal costPrice;// 商品成本价
	private BigDecimal oldPrice;// 商品原价
	private BigDecimal price;// 商品成交价
	private Long amount; // 商品数量
	private BigDecimal priceDis;// 优惠金额
	private BigDecimal goodsSumFee;// 小计金额

	private int stockId;// 发货仓库
	private String isDelete;// 是否删除(y/n)
	private String addUserId;// 添加人
	private String addTime;// 添加时间
	private String editUserId;// 修改人
	private String editTime;// 修改时间
	private String groupStatus;// 组合拆分状态
	private String tmSource;// 源tm组合码
	private int goodsStatus;// 商品状态

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDeliveryNo() {
		return orderDeliveryNo;
	}

	public void setOrderDeliveryNo(String orderDeliveryNo) {
		this.orderDeliveryNo = orderDeliveryNo;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsListType() {
		return goodsListType;
	}

	public void setGoodsListType(String goodsListType) {
		this.goodsListType = goodsListType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
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

	public BigDecimal getGoodsSumFee() {
		return goodsSumFee;
	}

	public void setGoodsSumFee(BigDecimal goodsSumFee) {
		this.goodsSumFee = goodsSumFee;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getEditUserId() {
		return editUserId;
	}

	public void setEditUserId(String editUserId) {
		this.editUserId = editUserId;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getTmSource() {
		return tmSource;
	}

	public void setTmSource(String tmSource) {
		this.tmSource = tmSource;
	}

	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public BigDecimal getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(BigDecimal supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	//复制方法
	public OrderItemModel clone(){
		OrderItemModel model = null;
		try {
			model = (OrderItemModel) super.clone();
		} catch (Exception e) {
		}
		return model;
	}
}
