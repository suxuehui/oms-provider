package cn.com.dubbo.bean;


public class TradeDetail {
	
	private String primaryKey;
	private String title;
	private Integer num;
	private String price;
	private String cid;
	private String item_id;
	private String outer_item_id;
	private String part_discount;
	private String part_post_fee;
	private String divide_fee;
	private String detailsDiscount;
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getOuter_item_id() {
		return outer_item_id;
	}
	public void setOuter_item_id(String outer_item_id) {
		this.outer_item_id = outer_item_id;
	}
	public String getPart_discount() {
		return part_discount;
	}
	public void setPart_discount(String part_discount) {
		this.part_discount = part_discount;
	}
	public String getPart_post_fee() {
		return part_post_fee;
	}
	public void setPart_post_fee(String part_post_fee) {
		this.part_post_fee = part_post_fee;
	}
	public String getDivide_fee() {
		return divide_fee;
	}
	public void setDivide_fee(String divide_fee) {
		this.divide_fee = divide_fee;
	}
	public String getDetailsDiscount() {
		return detailsDiscount;
	}
	public void setDetailsDiscount(String detailsDiscount) {
		this.detailsDiscount = detailsDiscount;
	}
}
