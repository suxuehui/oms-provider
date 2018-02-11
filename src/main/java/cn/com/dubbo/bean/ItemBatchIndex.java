package cn.com.dubbo.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemBatchIndex implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long batchItemId;
	
    private String eOrdernumber;

    private String addTime;

    private Integer billstates;

    private Integer multiChannelId;

    private String goodsno;

    private String goods69;

    private Integer amount;
    
    private BigDecimal sum_price;   

	public Long getBatchItemId() {
		return batchItemId;
	}

	public void setBatchItemId(Long batchItemId) {
		this.batchItemId = batchItemId;
	}

	public BigDecimal getSum_price() {
		return sum_price;
	}

	public void setSum_price(BigDecimal sum_price) {
		this.sum_price = sum_price;
	}

	public String geteOrdernumber() {
        return eOrdernumber;
    }

    public void seteOrdernumber(String eOrdernumber) {
        this.eOrdernumber = eOrdernumber == null ? null : eOrdernumber.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public Integer getBillstates() {
        return billstates;
    }

    public void setBillstates(Integer billstates) {
        this.billstates = billstates;
    }

    public Integer getMultiChannelId() {
        return multiChannelId;
    }

    public void setMultiChannelId(Integer multiChannelId) {
        this.multiChannelId = multiChannelId;
    }

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno == null ? null : goodsno.trim();
    }

    public String getGoods69() {
        return goods69;
    }

    public void setGoods69(String goods69) {
        this.goods69 = goods69 == null ? null : goods69.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}