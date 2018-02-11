package cn.com.dubbo.util;

public class OrderExpressUtil {
	/**
	 * 将E店宝数据转换成本地对应
	 * 
	 * @author 常胜
	 * @date 2016-8-3 下午7:05:47
	 * @param name
	 * @return
	 * @return String[]
	 */
	public static String[] getExpress(String name) {
		String[] str = new String[3];
		if (name.equals("E邮宝")) {
			str[0] = "4";
			str[1] = "EMS";
			str[2] = "200004";
		} else if (name.equals("汇通")) {
			str[0] = "6";
			str[1] = "百世汇通";
			str[2] = "200006";
		} else if (name.equals("宅急送")) {
			str[0] = "8";
			str[1] = "宅急送";
			str[2] = "200008";
		} else if (name.equals("申通快运")) {
			str[0] = "10";
			str[1] = "申通快递";
			str[2] = "200010";
		} else if (name.equals("全一")) {
			str[0] = "11";
			str[1] = "全一快递";
			str[2] = "200011";
		} else if (name.equals("虚拟发货")) {
			str[0] = "0";
			str[1] = "虚拟发货";
			str[2] = "";
		} else if (name.equals("全锋快递")) {
			str[0] = "12";
			str[1] = "全峰快递";
			str[2] = "200012";
		} else if (name.equals("联邦快递")) {
			str[0] = "0";
			str[1] = "联邦快递";
			str[2] = "";
		} else if (name.equals("邦德物流")) {
			str[0] = "19";
			str[1] = "邦德物流";
			str[2] = "200019";
		} else if (name.equals("如风达")) {
			str[0] = "0";
			str[1] = "如风达";
			str[2] = "";
		} else if (name.equals("自提")) {
			str[0] = "24";
			str[1] = "自提";
			str[2] = "200027";
		} else if (name.equals("申通")) {
			str[0] = "10";
			str[1] = "申通快递";
			str[2] = "200010";
		} else if (name.equals("快捷")) {
			str[0] = "";
			str[1] = "快捷快递";
			str[2] = "";
		} else if (name.equals("京东快递")) {
			str[0] = "22";
			str[1] = "京东快递";
			str[2] = "200022";
		} else if (name.equals("EMS")) {
			str[0] = "4";
			str[1] = "EMS";
			str[2] = "200004";
		} else if (name.equals("圆通")) {
			str[0] = "7";
			str[1] = "圆通快递";
			str[2] = "200007";
		} else if (name.equals("天天快递")) {
			str[0] = "5";
			str[1] = "天天快递";
			str[2] = "200005";
		} else if (name.equals("顺丰")) {
			str[0] = "2";
			str[1] = "顺丰速运";
			str[2] = "200002";
		} else if (name.equals("顺丰国际")) {
			str[0] = "2";
			str[1] = "顺丰速运";
			str[2] = "200002";
		} else if (name.equals("韵达")) {
			str[0] = "3";
			str[1] = "韵达快递";
			str[2] = "200003";
		} else if (name.equals("中通")) {
			str[0] = "9";
			str[1] = "中通快递";
			str[2] = "200009";
		} else {
			str[0] = "";
			str[1] = "";
			str[2] = "";
		}

		return str;
	}

	public static String[] getTmExpress(String name) {
		String[] str = new String[3];
		if (name.equals("E邮宝")) {
			str[0] = "4";
			str[1] = "EMS";
			str[2] = "EMS";
		} else if (name.equals("汇通")) {
			str[0] = "83";
			str[1] = "百世汇通";
			str[2] = "HTKY";
		} else if (name.equals("宅急送")) {
			str[0] = "108";
			str[1] = "宅急送";
			str[2] = "ZJS";
		} else if (name.equals("申通快运")) {
			str[0] = "79";
			str[1] = "申通快递";
			str[2] = "STO";
		} else if (name.equals("虚拟发货")) {
			str[0] = "0";
			str[1] = "虚拟发货";
			str[2] = "";
		} else if (name.equals("自提")) {
			str[0] = "24";
			str[1] = "自提";
			str[2] = "200027";
		} else if (name.equals("申通")) {
			str[0] = "79";
			str[1] = "申通快递";
			str[2] = "STO";
		} else if (name.equals("EMS")) {
			str[0] = "81";
			str[1] = "EMS";
			str[2] = "EMS";
		} else if (name.equals("圆通")) {
			str[0] = "97";
			str[1] = "圆通速递";
			str[2] = "YTO";
		} else if (name.equals("天天快递")) {
			str[0] = "107";
			str[1] = "天天快递";
			str[2] = "TTKDEX";
		} else if (name.equals("顺丰")) {
			str[0] = "82";
			str[1] = "顺丰速运";
			str[2] = "SF";
		} else if (name.equals("韵达")) {
			str[0] = "84";
			str[1] = "韵达快递";
			str[2] = "YUNDA";
		} else if (name.equals("中通")) {
			str[0] = "109";
			str[1] = "中通快递";
			str[2] = "ZTO";
		} else {
			str[0] = "";
			str[1] = "";
			str[2] = "";
		}

		return str;
	}

	public static String[] getPDExpress(String name) {
		String[] str = new String[3];
		if (name.equals("E邮宝")) {
			str[0] = "4";
			str[1] = "EMS";
			str[2] = "200004";
		} else if (name.contains("汇通")) {
			str[0] = "115";
			str[1] = "百世快递";
			str[2] = "3";
		} else if (name.contains("宅急送")) {
			str[0] = "128";
			str[1] = "宅急送快递";
			str[2] = "124";
		} else if (name.contains("申通")) {
			str[0] = "114";
			str[1] = "申通快递";
			str[2] = "1";
		} else if (name.contains("全一")) {
			str[0] = "11";
			str[1] = "全一快递";
			str[2] = "200011";
		} else if (name.equals("虚拟发货")) {
			str[0] = "0";
			str[1] = "虚拟发货";
			str[2] = "";
		} else if (name.contains("全锋")) {
			str[0] = "120";
			str[1] = "全峰快递";
			str[2] = "116";
		} else if (name.contains("联邦")) {
			str[0] = "132";
			str[1] = "联邦快递";
			str[2] = "135";
		} else if (name.contains("九曳")) {
			str[0] = "133";
			str[1] = "九曳供应链";
			str[2] = "136";
		} else if (name.contains("万象")) {
			str[0] = "134";
			str[1] = "万象物流";
			str[2] = "142";
		} else if (name.contains("速尔")) {
			str[0] = "135";
			str[1] = "速尔快递";
			str[2] = "155";
		} else if (name.contains("亚马逊")) {
			str[0] = "136";
			str[1] = "亚马逊物流";
			str[2] = "156";
		} else if (name.contains("城市100")) {
			str[0] = "137";
			str[1] = "城市100";
			str[2] = "162";
		} else if (name.contains("德邦")) {
			str[0] = "130";
			str[1] = "德邦快递";
			str[2] = "131";
		} else if (name.contains("小红帽")) {
			str[0] = "138";
			str[1] = "小红帽";
			str[2] = "176";
		} else if (name.contains("城际速递")) {
			str[0] = "139";
			str[1] = "城际速递";
			str[2] = "185";
		} else if (name.contains("青岛安捷")) {
			str[0] = "140";
			str[1] = "青岛安捷";
			str[2] = "187";
		} else if (name.contains("贝海国际")) {
			str[0] = "141";
			str[1] = "贝海国际速递";
			str[2] = "195";
		} else if (name.contains("全一")) {
			str[0] = "142";
			str[1] = "全一快递";
			str[2] = "201";
		} else if (name.contains("远成快运")) {
			str[0] = "143";
			str[1] = "远成快运";
			str[2] = "205";
		} else if (name.contains("安能")) {
			str[0] = "144";
			str[1] = "安能物流";
			str[2] = "208";
		} else if (name.contains("天地华宇")) {
			str[0] = "145";
			str[1] = "天地华宇";
			str[2] = "210";
		} else if (name.contains("中邮")) {
			str[0] = "146";
			str[1] = "中邮速递";
			str[2] = "211";
		} else if (name.contains("中铁")) {
			str[0] = "147";
			str[1] = "中铁物流";
			str[2] = "214";
		} else if (name.contains("新邦")) {
			str[0] = "148";
			str[1] = "新邦物流";
			str[2] = "216";
		} else if (name.contains("龙邦")) {
			str[0] = "131";
			str[1] = "龙邦快递";
			str[2] = "132";
		} else if (name.contains("如风达")) {
			str[0] = "129";
			str[1] = "如风达";
			str[2] = "130";
		} else if (name.equals("自提")) {
			str[0] = "24";
			str[1] = "自提";
			str[2] = "200027";
		} else if (name.contains("快捷")) {
			str[0] = "126";
			str[1] = "快捷快递";
			str[2] = "122";
		} else if (name.contains("京东快递")) {
			str[0] = "124";
			str[1] = "京东配送";
			str[2] = "120";
		} else if (name.contains("京东")) {
			str[0] = "124";
			str[1] = "京东快递";
			str[2] = "120";
		} else if (name.contains("EMS")) {
			str[0] = "122";
			str[1] = "邮政EMS";
			str[2] = "118";
		} else if (name.contains("圆通")) {
			str[0] = "117";
			str[1] = "圆通快递";
			str[2] = "85";
		} else if (name.contains("赛澳递")) {
			str[0] = "118";
			str[1] = "赛澳递";
			str[2] = "89";
		} else if (name.contains("天天")) {
			str[0] = "123";
			str[1] = "天天快递";
			str[2] = "119";
		} else if (name.contains("顺丰")) {
			str[0] = "116";
			str[1] = "顺丰快递";
			str[2] = "44";
		} else if (name.equals("顺丰国际")) {
			str[0] = "2";
			str[1] = "顺丰速运";
			str[2] = "200002";
		} else if (name.contains("韵达")) {
			str[0] = "125";
			str[1] = "韵达快递";
			str[2] = "121";
		} else if (name.contains("中通")) {
			str[0] = "119";
			str[1] = "中通快递";
			str[2] = "115";
		} else if (name.contains("优速")) {
			str[0] = "121";
			str[1] = "优速快递";
			str[2] = "117";
		} else if (name.contains("国通")) {
			str[0] = "127";
			str[1] = "国通快递";
			str[2] = "124";
		} else {
			str[0] = "";
			str[1] = "";
			str[2] = "";
		}

		return str;
	}

	/**
	 * 官网物流转换
	 * 
	 * @author 常胜
	 * @date 2016-12-19 上午10:39:13
	 * @param name
	 * @return
	 * @return String[]
	 */
	public static String[] getGWExpress(String name) {
		String[] str = new String[3];
		if (name.contains("申通")) {
			str[0] = "98";
			str[1] = "申通快递";
			str[2] = "STO";
		} else if (name.contains("圆通")) {
			str[0] = "99";
			str[1] = "圆通速递";
			str[2] = "YTO";
		} else if (name.equals("韵达")) {
			str[0] = "100";
			str[1] = "韵达快递";
			str[2] = "YD";
		} else if (name.contains("顺丰")) {
			str[0] = "101";
			str[1] = "顺丰快递";
			str[2] = "SF";
		} else if (name.contains("EMS")) {
			str[0] = "102";
			str[1] = "EMS";
			str[2] = "EMS";
		} else if (name.contains("天天")) {
			str[0] = "103";
			str[1] = "天天快递";
			str[2] = "HHTT";
		} else {
			str[0] = "";
			str[1] = "";
			str[2] = "";
		}

		return str;
	}

	public static String[] get360Express(String name) {
		String[] str = new String[3];
		if (name.equals("E邮宝")) {
			str[0] = "4";
			str[1] = "EMS";
			str[2] = "200004";
		} else if (name.contains("申通")) {
			str[0] = "80";
			str[1] = "申通";
			str[2] = "shentong";
		} else if (name.equals("虚拟发货")) {
			str[0] = "0";
			str[1] = "虚拟发货";
			str[2] = "";
		} else if (name.contains("全锋")) {
			str[0] = "112";
			str[1] = "全峰";
			str[2] = "quanfengkuaidi";
		} else if (name.equals("自提")) {
			str[0] = "24";
			str[1] = "自提";
			str[2] = "200027";
		} else if (name.contains("EMS")) {
			str[0] = "95";
			str[1] = "EMS";
			str[2] = "ems";
		} else if (name.contains("圆通")) {
			str[0] = "110";
			str[1] = "圆通";
			str[2] = "yuantong";
		} else if (name.contains("天天")) {
			str[0] = "113";
			str[1] = "天天";
			str[2] = "tiantian";
		} else if (name.contains("顺丰")) {
			str[0] = "94";
			str[1] = "顺丰";
			str[2] = "shunfeng";
		} else if (name.contains("韵达")) {
			str[0] = "96";
			str[1] = "韵达";
			str[2] = "yunda";
		} else if (name.contains("中通")) {
			str[0] = "111";
			str[1] = "中通快递";
			str[2] = "zhongtong";
		} else if (name.contains("快捷")) {
			str[0] = "149";
			str[1] = "快捷";
			str[2] = "kuaijiesudi";
		} else if (name.contains("百世汇通")) {
			str[0] = "150";
			str[1] = "百世汇通";
			str[2] = "huitongkuaidi";
		} else {
			str[0] = "";
			str[1] = "";
			str[2] = "";
		}

		return str;
	}

	/**
	 * 将物流转换成E店宝物流
	 * 
	 * @author 常胜
	 * @date 2016-8-3 下午7:06:31
	 * @param name
	 * @return
	 * @return String
	 */
	public static String getEDBExpress(String name) {
		String str = "";
		if (name.equals("EMS")) {
			str = "E邮宝";
		} else if (name.equals("百世汇通")) {
			str = "汇通";
		} else if (name.equals("宅急送")) {
			str = "宅急送";
		} else if (name.equals("全一快递")) {
			str = "全一";
		} else if (name.equals("虚拟发货")) {
			str = "虚拟发货";
		} else if (name.equals("全锋快递")) {
			str = "全峰快递";
		} else if (name.equals("联邦快递")) {
			str = "联邦快递";
		} else if (name.equals("邦德物流")) {
			str = "邦德物流";
		} else if (name.equals("如风达")) {
			str = "如风达";
		} else if (name.equals("自提")) {
			str = "自提";
		} else if (name.equals("申通快递")) {
			str = "申通";
		} else if (name.equals("快捷快递")) {
			str = "快捷";
		} else if (name.equals("京东快递")) {
			str = "京东快递";
		} else if (name.equals("EMS")) {
			str = "EMS";
		} else if (name.equals("圆通快递")) {
			str = "圆通";
		} else if (name.equals("天天快递")) {
			str = "天天快递";
		} else if (name.equals("顺丰速运")) {
			str = "顺丰";
		} else if (name.equals("顺丰速运")) {
			str = "顺丰国际";
		} else if (name.equals("韵达快递")) {
			str = "韵达";
		} else if (name.equals("中通快递")) {
			str = "中通";
		} else if (name.equals("中通速递")) {
			str = "中通";
		} else if (name.equals("优速快递")) {
			str = "优速快递";
		} else {
			str = "";
		}

		return str;
	}

	/**
	 * 将E店宝渠道改为本地渠道
	 * 
	 * @author 常胜
	 * @date 2016-8-3 下午7:07:05
	 * @param id
	 * @return
	 * @return String
	 */
	public static String getMultiChannel(String id) {
		if (id.equals("1")) {
			return "1";
		} else if (id.equals("2")) {
			return "2";
		} else if (id.equals("4")) {
			return "3";
		} else if (id.equals("6")) {
			return "4";
		} else if (id.equals("3")) {
			return "7";
		} else if (id.equals("10")) {
			return "8";
		} else if (id.equals("11")) {
			return "9";
		} else if (id.equals("12")) {
			return "10";
		} else if (id.equals("14")) {
			return "6";
		} else {
			return "0";
		}

	}

	/**
	 * 将本地渠道改为E店宝渠道
	 * 
	 * @author 常胜
	 * @date 2016-8-3 下午7:07:05
	 * @param id
	 * @return
	 * @return String
	 */
	public static String getMultiChannelEDB(String id) {
		if (id.equals("1")) {
			return "1";
		} else if (id.equals("2")) {
			return "2";
		} else if (id.equals("3")) {
			return "4";
		} else if (id.equals("4")) {
			return "6";
		} else if (id.equals("5")) {
			return "6";
		} else if (id.equals("7")) {
			return "3";
		} else if (id.equals("8")) {
			return "10";
		} else if (id.equals("9")) {
			return "11";
		} else if (id.equals("10")) {
			return "12";
		} else if (id.equals("6")) {
			return "14";
		} else {
			return "0";
		}

	}

	/**
	 * 删除订单前缀
	 * 
	 * @author 常胜
	 * @date 2016-8-3 下午7:07:41
	 * @param orderNo
	 * @return
	 * @return String
	 */
	public static String removePrefix(String orderNo) {
		if (orderNo.contains("TM")) {
			return orderNo.substring(2, orderNo.length());
		} else if (orderNo.contains("JD")) {
			return orderNo.substring(2, orderNo.length());
		} else if (orderNo.contains("PA")) {
			return orderNo.substring(2, orderNo.length());
		} else {
			return orderNo.substring(2, orderNo.length());
		}

	}
}
