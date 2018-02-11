package cn.com.dubbo.model2;

import java.io.Serializable;

public class E_orderstate implements Serializable {
	/**  */
	private static final long serialVersionUID = 1L;

	private String eOrdernumber;

	private Integer billstates;

	public String geteOrdernumber() {
		return eOrdernumber;
	}

	public void seteOrdernumber(String eOrdernumber) {
		this.eOrdernumber = eOrdernumber == null ? null : eOrdernumber.trim();
	}

	public Integer getBillstates() {
		return billstates;
	}

	public void setBillstates(Integer billstates) {
		this.billstates = billstates;
	}
}