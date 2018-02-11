package cn.com.dubbo.bean.ext;

import java.math.BigDecimal;

public class ItemBatchDetail {
    private Integer orderDataId;

    private Long orderItemId;

    private String eOrdernumber;

    private String orderNo;

    private String goodsNo;

    private String goodsNo69;

    private BigDecimal costPrice;

    private Integer multiChannelId;

    private String goodName;

    private Long amount;

    private String stockStatus;

    private String goodsBatch;

    private Integer addUserId;

    private String addTime;

    private Integer editUserId;

    private String editTime;

    private String summary;

    private Integer indexId;

    public Integer getOrderDataId() {
        return orderDataId;
    }

    public void setOrderDataId(Integer orderDataId) {
        this.orderDataId = orderDataId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String geteOrdernumber() {
        return eOrdernumber;
    }

    public void seteOrdernumber(String eOrdernumber) {
        this.eOrdernumber = eOrdernumber == null ? null : eOrdernumber.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getMultiChannelId() {
        return multiChannelId;
    }

    public void setMultiChannelId(Integer multiChannelId) {
        this.multiChannelId = multiChannelId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus == null ? null : stockStatus.trim();
    }

    public String getGoodsBatch() {
        return goodsBatch;
    }

    public void setGoodsBatch(String goodsBatch) {
        this.goodsBatch = goodsBatch == null ? null : goodsBatch.trim();
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }
}