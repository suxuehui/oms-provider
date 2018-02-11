package cn.com.dubbo.bean.ext;

public class ItemBatchIndex {
	private Long batchItemId;

	private String eOrdernumber;

	private String addTime;

	private Integer billstates;

	private Integer multiChannelId;

	private String goodsno;

	private String goods69;

	private Integer amount;

	private Long sumPrice;

	private String batchno;

	public Long getBatchItemId() {
		return batchItemId;
	}

	public void setBatchItemId(Long batchItemId) {
		this.batchItemId = batchItemId;
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

	public Long getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Long sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

}