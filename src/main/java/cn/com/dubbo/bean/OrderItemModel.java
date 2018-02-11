package cn.com.dubbo.bean;

import java.math.BigDecimal;

public class OrderItemModel {
    private Long orderItemId;

    private String orderNo;

    private String orderDeliveryNo;

    private String goodsListType;

    private String goodsType;

    private String goodsNo;

    private String goodsNo69;

    private String name;

    private String standard;

    private Short supplyPrice;

    private String unit;

    private BigDecimal costPrice;

    private BigDecimal oldPrice;

    private BigDecimal price;

    private BigDecimal amount;

    private BigDecimal priceDis;

    private BigDecimal goodsSumFee;

    private Integer stockId;

    private String isDelete;

    private Integer addUserId;

    private String addTime;

    private Integer editUserId;

    private String editTime;

    private String groupStatus;

    private String tmSource;

    private Integer goodsStatus;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderDeliveryNo() {
        return orderDeliveryNo;
    }

    public void setOrderDeliveryNo(String orderDeliveryNo) {
        this.orderDeliveryNo = orderDeliveryNo == null ? null : orderDeliveryNo.trim();
    }

    public String getGoodsListType() {
        return goodsListType;
    }

    public void setGoodsListType(String goodsListType) {
        this.goodsListType = goodsListType == null ? null : goodsListType.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public String getGoodsNo69() {
        return goodsNo69;
    }

    public void setGoodsNo69(String goodsNo69) {
        this.goodsNo69 = goodsNo69 == null ? null : goodsNo69.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public Short getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(Short supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public Integer getEditUserId() {
        return editUserId;
    }

    public void setEditUserId(Integer editUserId) {
        this.editUserId = editUserId;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus == null ? null : groupStatus.trim();
    }

    public String getTmSource() {
        return tmSource;
    }

    public void setTmSource(String tmSource) {
        this.tmSource = tmSource == null ? null : tmSource.trim();
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
}