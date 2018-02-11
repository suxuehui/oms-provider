package cn.com.dubbo.bean;


public class TradesSoldGetRequest {
	
	private String startCreated;
	private String endCreated;
	private String fields;
	private String tradeFrom;
	private String status;
	private String haveCFY;
	private String pdState;
	private Integer pageNo;
	private Integer pageSize;
	
	public String getStartCreated() {
		return startCreated;
	}
	public void setStartCreated(String startCreated) {
		this.startCreated = startCreated;
	}
	public String getEndCreated() {
		return endCreated;
	}
	public void setEndCreated(String endCreated) {
		this.endCreated = endCreated;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getTradeFrom() {
		return tradeFrom;
	}
	public void setTradeFrom(String tradeFrom) {
		this.tradeFrom = tradeFrom;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHaveCFY() {
		return haveCFY;
	}
	public void setHaveCFY(String haveCFY) {
		this.haveCFY = haveCFY;
	}
	public String getPdState() {
		return pdState;
	}
	public void setPdState(String pdState) {
		this.pdState = pdState;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
