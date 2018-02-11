package cn.com.dubbo.base;

public class BaseData {
	
	//同步官网物流信息接口
	public static final  String GW_URL = "http://order.soa.9drug.cn/order-provider/orders/shippingInformation.html";
	//同步微信物流信息接口
	public static final  String WX_URL = "";
	//获取官网订单信息地址 http://192.168.90.201:8117/order-providere/orders/synchro.html?beiginTime=?&endTime=?&pageIndex=?&orderStatus=
//	public static final  String GW_ORDER = "http://192.168.90.201:8117/order-provider/orders/synchro.html";
	public static final  String GW_ORDER = "http://order.soa.9drug.cn/order-provider/orders/synchro.html";
	//向官网同步库存  推的方式
	public static final  String GW_PODUCT_NUM = "http://product.soa.9drug.cn/product-provider/product/synchronizationInventory.html";
    // 之前的调用API地址 http://vip528.edb07.net/rest/index.aspx?
	public static final String edbUrl = "http://vip3129.edb08.com.cn/rest/index.aspx";
	// 申请的appkey
	public static final String appkey = "4d47f04c";
	// 申请的secret
	public static final String secret = "41ed64b8885c477496eccd28b48112d4";
	// 申请的token
	public static final String token = "0aab8a9f960044a9979d6c8252291285";
	// 主帐号
	public static final String dbhost = "edb_a85111";
	// 返回格式
	public static final String format = "json";
	
}
