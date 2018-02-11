package cn.com.dubbo.model;

import java.io.Serializable;
import java.util.List;

public class ToSendModel implements Serializable {

	private static final long serialVersionUID = 591193130278760068L;

	private int totalResults;// 总数
	private List<OrderModelExt> deliverys;

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public List<OrderModelExt> getDeliverys() {
		return deliverys;
	}

	public void setDeliverys(List<OrderModelExt> deliverys) {
		this.deliverys = deliverys;
	}

}