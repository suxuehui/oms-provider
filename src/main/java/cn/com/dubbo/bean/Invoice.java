package cn.com.dubbo.bean;


public class Invoice {
	
	private String type;
	private String title;
	private Double amount;
	private int isDetails;
	private String content;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getIsDetails() {
		return isDetails;
	}
	public void setIsDetails(int isDetails) {
		this.isDetails = isDetails;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
