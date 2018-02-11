package cn.com.dubbo.bean;

import java.math.BigDecimal;

public class ItemBatchDetail {
    private Integer orderDataId;

    private Long orderItemId;

    private String eOrdernumber;

    private String orderNo;

    private String goodsNo;

    private String goodsNo69;

    private BigDecimal costPrice;

    private String goodName;

    private BigDecimal amount;

    private String stockStatus;

    private String goodsBatch;

    private Integer addUserId;

    private String addTime;

    private Integer editUserId;

    private String editTime;

    private Integer billstates;
    
    private Integer multi_channel_id;
    
    private Long saveId;
    
    private BigDecimal sum_price;
    
    private String summary;
    
    private String index_id;
    
    public String getIndex_id() {
		return index_id;
	}

	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public BigDecimal getSum_price() {
		return sum_price;
	}

	public void setSum_price(BigDecimal sum_price) {
		this.sum_price = sum_price;
	}

	public Long getSaveId() {
		return saveId;
	}

	public void setSaveId(Long saveId) {
		this.saveId = saveId;
	}

	public Integer getBillstates() {
		return billstates;
	}

	public void setBillstates(Integer billstates) {
		this.billstates = billstates;
	}

	public Integer getMulti_channel_id() {
		return multi_channel_id;
	}

	public void setMulti_channel_id(Integer multi_channel_id) {
		this.multi_channel_id = multi_channel_id;
	}

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

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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
}