package cn.com.dubbo.constant;

/**
 * 京东订单状态
 */
public enum JDOrderState {
	
	
	/**
	 * 	 * 多订单状态可以用英文逗号隔开
		 *  1）WAIT_SELLER_STOCK_OUT 等待出库 
		 *  2）SEND_TO_DISTRIBUTION_CENER 发往配送中心（只适用于LBP，SOPL商家） 
		 *  3）DISTRIBUTION_CENTER_RECEIVED 配送中心已收货（只适用于LBP，SOPL商家） 
		 *  4）WAIT_GOODS_RECEIVE_CONFIRM 等待确认收货 
		 *  5）RECEIPTS_CONFIRM 收款确认（服务完成）（只适用于LBP，SOPL商家）
		 *   6）WAIT_SELLER_DELIVERY等待发货（只适用于海外购商家，等待境内发货 标签下的订单） 
		 *   7）FINISHED_L 完成 
		 *   8）TRADE_CANCELED 取消 
		 *   9）LOCKED 已锁定 
		 *   10）PAUSE 暂停  
	 */
	STATE_1("WAIT_SELLER_STOCK_OUT","等待出库 "),
	STATE_2("SEND_TO_DISTRIBUTION_CENER","发往配送中心(只适用于LBP，SOPL商家)"),
	STATE_3("DISTRIBUTION_CENTER_RECEIVED","配送中心已收货(只适用于LBP，SOPL商家)"),
	STATE_4("WAIT_GOODS_RECEIVE_CONFIRM","等待确认收货"),
	STATE_5("RECEIPTS_CONFIRM","收款确认（服务完成）（只适用于LBP，SOPL商家）"),
	STATE_6("WAIT_SELLER_DELIVERY","等待发货（只适用于海外购商家，等待境内发货 标签下的订单）"),
	STATE_7("FINISHED_L","完成"),
	STATE_8("TRADE_CANCELED","取消"),
	STATE_9("LOCKED","已锁定"),
	STATE_10("PAUSE","暂停");

	// 成员变量
	private String code;
    private String name;
    
    //构造方法
    private JDOrderState(String code, String name) {
    	this.code = code;
        this.name = name;
    }
    
    //普通方法
    public static String getName(String code) {
        for (JDOrderState c : JDOrderState.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}