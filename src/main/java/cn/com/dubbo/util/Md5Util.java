package cn.com.dubbo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Md5Util {
	// 调用API地址 http://vip528.edb07.net/rest/index.aspx?
	protected static String testUrl = "http://vip528.edb07.net/rest/index.aspx";
	// 申请的appkey
	public static final String appkey = "456d35b2";
	// 申请的secret
	public static final String secret = "6023d4d099ea523c17a16219621bd4be";
	// 申请的token
	public static final String token = "bace8679b6c381b10cd18e951a2dad6e";
	// 主帐号
	public static final String dbhost = "edb_a85111";
	// 返回格式
	public static final String format = "json";

	// 获取订单信息
	public static String edbTradeGet(TreeMap<String, String> map) {
		// TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
		//
		// 获取数字签名
		String sign = Util.md5Signature(map, secret, token);// appkey secret
		return sign;
	}

	public static void page(Integer pageNo, Integer pageSize) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 52; i++) {
			list.add(i + "");
		}

		int page = list.size() / pageSize;
		int remainder = list.size() % pageSize;
		if (remainder != 0) {
			page++;
		}
		int start = (pageNo - 1) * pageSize;
		list.subList(0, start).clear();
		System.out.println(list);
		for (int i = 0; i < pageSize; i++) {
			System.out.print(list.get(i) + "  ");
		}
	}

	public static void main(String[] args) {
		Md5Util.page(2, 5);
	}
}
