package cn.com.dubbo.util.temporary;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class test {
	// 调用API地址 http://vip528.edb07.net/rest/index.aspx?
	protected static String testUrl = "http://vip528.edb07.net/rest/index.aspx";
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

	// 获取订单信息
	public static void edbTradeGet() {
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
		apiparamsMap.put("method", "edbTradeGet");// 添加请求参数——接口名称
		String fields = "out_tid,transaction_id,customer_id,distributor_id,shop_name,out_pay_tid,voucher_id,shopid,serial_num,order_channel,order_from,buyer_id,buyer_name,type,status,abnormal_status," + "merge_status,receiver_name,receiver_mobile,phone,province,city,district,address,post,email,is_bill,invoice_name,invoice_situation,invoice_title,invoice_type,invoice_content,pro_totalfee,"
				+ "order_totalfee,reference_price_paid,invoice_fee,cod_fee,other_fee,refund_totalfee,discount_fee,discount,channel_disfee,merchant_disfee,order_disfee,commission_fee,is_cod,point_pay,cost_point,point," + "superior_point,royalty_fee,external_point,express_no,express,express_coding,online_express,sending_type,real_income_freight,real_pay_freight,gross_weight,gross_weight_freight,net_weight_freight,"
				+ "freight_explain,total_weight,tid_net_weight,tid_time,pay_time,Sorting_code,jd_delivery_time,is_pre_delivery_notice,deliver_station,deliver_centre,last_refund_time,last_returned_time,express_code," + "area_code,city_code,provinc_code,is_adv_sale,plat_type,plat_send_status,break_explain,break_time,breaker,is_break,order_process_time,taobao_delivery_method,"
				+ "taobao_delivery_status,taobao_delivery_order_status,is_flag,flag_color,single_num,item_num,sku,product_num,express_col_fee,cod_service_fee,distributor_level," + "is_new_customer,delivery_name,import_mark,related_orders_type,related_orders,is_stock,message_time,message,other_remarks,system_remarks,distributor_mark,inner_lable,"
				+ "service_remarks,buyer_message,delivery_status,currency,rate,platform_status,pay_status,pay_mothed,alipay_status,alipay_id,enable_inte_delivery_time," + "enable_inte_sto_time,verificaty_time,receive_time,good_receive_time,out_promotion_detail,promotion_plan,is_promotion,modity_time,finish_time,file_time,"
				+ "file_operator,book_file_time,lock_time,locker,delivery_time,delivery_operator,book_delivery_time,weigh_time,weigh_operator,pack_time,packager,"
				+ "revoke_cancel_time,revoke_cancel_er,cancel_time,cancel_operator,inspect_time,business_man,payment_received_operator,payment_received_time,"
				+ "review_orders_time,finance_review_operator,finance_review_time,review_orders_operator,printer,print_time,is_print,advance_printer,adv_distributer,"
				+ "adv_distribut_time,get_time,order_creater,distributer,distribut_time,is_inspection,inspecter,freight_explain,"
				+
				// ----------EdbOrders有，但是查询字段中没有--------------------------------------------
				// codSettlementVouchernumber; totalNum origincode destcode bigMarker payTimeBak

				// -----------------EdbOrders、EdbGoods 共有的字段---------------------------------
				"storage_id,tid,"
				+ // out_tid,
					// ----------EdbGoods--------------------------------------------
				"pro_detail_code,pro_name,specification,barcode,combine_barcode,iscancel,isscheduled,stock_situation,isbook_pro,iscombination,isgifts,gift_num," + "book_storage,pro_num,send_num,refund_num,refund_renum,inspection_num,timeinventory,cost_price," + "sell_price,average_price,original_price,sys_price,Ferght,item_discountfee,inspection_time,Weight,shopid,out_proid,out_prosku,proexplain,"
				+ "buyer_memo,seller_remark,distributer,distribut_time,second_barcode,product_no,brand_number,brand_name,discount_amount,credit_amount,MD5_encryption";
		// ----------EdbGoods有，但是查询字段中没有--------------------------------------------
		apiparamsMap.put("fields", fields);
		apiparamsMap.put("date_type", "获取日期");// 添加请求参数——主帐号---订单产生时间 付款日期 获取日期

		apiparamsMap.put("out_tid", "556714710902");// 外部订单ID
		// $params['order_channel'] = "直营网店";

		apiparamsMap.put("begin_time", "2016-07-01 00:00:00");
		apiparamsMap.put("end_time", "2016-08-03 10:56:00");// 2016-07-26 15:57:54
		// apiparamsMap.put("shopid", "6");// shopid:店铺id "shopid": "1"----"shop_name": "淘宝国泰永康大药房旗舰店"; //平安 6 360 4 京东 2 淘宝 1
		// apiparamsMap.put("proce_Status", "已确认");// 未确认,已确认,已作废 TODO 23分 740 ---329 366 13 742
		apiparamsMap.put("page_size", "40");
		apiparamsMap.put("page_no", "1");

		apiparamsMap.put("dbhost", dbhost);// 添加请求参数——主帐号
		apiparamsMap.put("appkey", appkey);// 添加请求参数——appkey
		apiparamsMap.put("format", format);// 添加请求参数——返回格式
		apiparamsMap.put("v", "2.0");// 添加请求参数——版本号（目前只提供2.0版本）
		apiparamsMap.put("slencry", "0");// 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
		apiparamsMap.put("ip", "172.16.2.28");// 添加请求参数——IP地址 172.16.2.28:是正确的

		apiparamsMap.put("appscret", secret);// 添加请求参数——appscret
		apiparamsMap.put("token", token);// 添加请求参数——token
		String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		apiparamsMap.put("timestamp", timestamp);// 添加请求参数——时间戳
		// 获取数字签名
		String sign = Util.md5Signature(apiparamsMap, appkey);// appkey secret

		apiparamsMap.put("sign", sign);

		StringBuilder param = new StringBuilder();

		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			if (e.getKey() != "appscret" && e.getKey() != "token") {
				// param.append("&").append(e.getKey()).append("=").append(e.getValue());

				if (e.getKey() == "shop_id" || e.getKey() == "wangwang_id" || e.getKey() == "date_type") {
					try {
						param.append("&").append(e.getKey()).append("=").append(Util.encodeUri(e.getValue()));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				} else {
					param.append("&").append(e.getKey()).append("=").append(e.getValue());
				}
			}
		}

		String PostData = "";
		PostData = param.toString().substring(1);
		System.out.println(testUrl + "?" + PostData);
		String result = "";
		result = Util.getResult(testUrl, PostData);
		System.out.println(result);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test.edbTradeGet();
	}
}
