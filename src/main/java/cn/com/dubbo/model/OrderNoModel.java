package cn.com.dubbo.model;

import java.io.Serializable;
import java.util.List;

public class OrderNoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderNo;// 订单编号

	private List<OrderNoItemModelExt> goods;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<OrderNoItemModelExt> getGoods() {
		return goods;
	}

	public void setGoods(List<OrderNoItemModelExt> goods) {
		this.goods = goods;
	}

}
