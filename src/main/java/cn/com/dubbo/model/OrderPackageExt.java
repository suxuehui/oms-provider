package cn.com.dubbo.model;

import java.io.Serializable;
import java.util.List;

public class OrderPackageExt implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 返回数量 **/
	private int totalResults;
	/** 返回物流数据 **/
	private List<SendOrderPackage> trades;

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public List<SendOrderPackage> getTrades() {
		return trades;
	}

	public void setTrades(List<SendOrderPackage> trades) {
		this.trades = trades;
	}

}
