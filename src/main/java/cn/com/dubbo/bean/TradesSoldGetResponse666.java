package cn.com.dubbo.bean;

import java.util.List;

public class TradesSoldGetResponse666{

	private List<Trade> trades ;
	private Integer totalResults;
	
	public List<Trade> getTrades() {
		return trades;
	}
	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	
}
