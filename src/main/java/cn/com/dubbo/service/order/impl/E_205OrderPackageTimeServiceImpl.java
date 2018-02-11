package cn.com.dubbo.service.order.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.bean.EdbOrders;
import cn.com.dubbo.mapper.OrderPackage205Mapper;
import cn.com.dubbo.model.OrderPackage205;
import cn.com.dubbo.model.OrderPackage205Example;
import cn.com.dubbo.service.order.E_205OrderPackageTimeService;
import cn.com.dubbo.util.OrderExpressUtil;
import cn.com.dubbo.util.StringUtil;
import cn.com.dubbo.util.TmUtil;

@Service("e_205OrderPackageTimeServiceImpl")
// @Transactional
public class E_205OrderPackageTimeServiceImpl implements E_205OrderPackageTimeService {

	// @Autowired
	// private OrderService orderService;
	//
	// @Autowired
	// private EdbOrdersMapper edbOrdersMapper;
	@Autowired
	private OrderPackage205Mapper orderPackage205Mapper;
	// @Resource
	// private OrderDeliveryMapper orderDeliveryMapper;

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public int pageSize = 40;// static

	// private static final Logger logger = Logger.getLogger(E_205OrderPackageTimeServiceImpl.class);
	// --------------------------------------------------------------------------------------

	private String testUrl = BaseData.edbUrl;
	// 申请的appkey
	private String appkey = BaseData.appkey;
	// 申请的secret
	private String secret = BaseData.secret;
	// 申请的token
	private String token = BaseData.token;
	// 主帐号
	private String dbhost = BaseData.dbhost;
	// 返回格式
	private String format = BaseData.format;

	/**
	 * 查询卖家交易数据,按照页码查询
	 * 
	 * @param pageNo
	 * @return
	 */
	public String getTradesSoldGetResponse(int pageNo, int pageSize, String[] str, String shopid) {
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
		apiparamsMap.put("method", "edbTradeGet");// 添加请求参数——接口名称
		String fields = "tid,out_tid,print_time,shopid,jd_delivery_time,storage_id,taobao_delivery_status,taobao_delivery_order_status,delivery_name,delivery_status,delivery_time,express,express_no,express_coding";
		apiparamsMap.put("fields", fields);
		apiparamsMap.put("date_type", "打印日期");// 添加请求参数——主帐号---订单产生时间 付款日期 获取日期 发货日期
		// apiparamsMap.put("out_tid", "735739110905"); // 外部订单ID
		// $params['order_channel'] = "直营网店";
		// apiparamsMap.put("out_tid", "TH1202697400304");
		// TODO aaaaaaa
		// apiparamsMap.put("begin_time", "2016-10-20 16:00:08");
		// apiparamsMap.put("end_time", "2016-10-28 16:00:59");
		// apiparamsMap.put("out_tid", "2130645221040939348");
		// apiparamsMap.put("begin_time", "2016-10-12 19:03:13");
		// apiparamsMap.put("end_time", "2016-12-15 19:03:13");
		apiparamsMap.put("begin_time", str[0]);
		apiparamsMap.put("end_time", str[1]);
		apiparamsMap.put("page_size", new Integer(pageSize).toString());
		apiparamsMap.put("page_no", new Integer(pageNo).toString());

		apiparamsMap.put("dbhost", dbhost);// 添加请求参数——主帐号
		apiparamsMap.put("appkey", appkey);// 添加请求参数——appkey
		apiparamsMap.put("format", format);// 添加请求参数——返回格式
		apiparamsMap.put("v", "2.0");// 添加请求参数——版本号（目前只提供2.0版本）
		apiparamsMap.put("slencry", "0");// 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
		apiparamsMap.put("ip", "172.16.2.28");// 添加请求参数——IP地址 172.16.2.28:是正确的

		// apiparamsMap.put("shopid", "1");// shopid:店铺id "shopid": "1"----"shop_name": "淘宝国泰永康大药房旗舰店";"6"----"shop_name": "平安";
		// apiparamsMap.put("proce_Status", "已确认");//TODO

		apiparamsMap.put("appscret", secret);// 添加请求参数——appscret
		apiparamsMap.put("token", token);// 添加请求参数——token
		String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		apiparamsMap.put("timestamp", timestamp);// 添加请求参数——时间戳

		// 获取数字签名
		String sign = TmUtil.md5Signature(apiparamsMap, appkey);// appkey secret
		apiparamsMap.put("sign", sign);

		StringBuilder param = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			if (e.getKey() != "appscret" && e.getKey() != "token") {
				// param.append("&").append(e.getKey()).append("=").append(e.getValue());
				if (e.getKey() == "shop_id" || e.getKey() == "wangwang_id" || e.getKey() == "date_type") {
					try {
						param.append("&").append(e.getKey()).append("=").append(TmUtil.encodeUri(e.getValue()));
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
		// System.out.println(testUrl+"?"+PostData);
		String result = "";
		result = TmUtil.getResult(testUrl, PostData);
		// System.out.println(result);
		return result;
	}

	// ----------------------分割线------------------------------------------------------------------

	@Override
	public void dealOrderStatus_TradesSoldGetResponse(String[] str, Long multiChannelOrderBatch, String shopid) {
		try {
			// int num=0;
			String tradesSoldGetResponse = null;
			int pageNo = 1;
			// int pageSize = 40;
			// String has_next=null;
			tradesSoldGetResponse = this.getTradesSoldGetResponse(pageNo, pageSize, str, shopid);
			// System.out.println(tradesSoldGetResponse.getBody().toString());
			JSONObject obj = JSONObject.fromObject(tradesSoldGetResponse);

			obj = JSONObject.fromObject(obj.getString("Success"));
			String totalResults_Str = obj.getString("total_results");
			int totalResults = 0;
			if (StringUtil.isStringNotBlank(totalResults_Str)) {
				totalResults = new Integer(totalResults_Str).intValue();
			}
			int page_index = 0;
			if (totalResults < pageSize) {
				page_index++;
				this.saveTradesSoldGetResponse6667(tradesSoldGetResponse, 0, multiChannelOrderBatch);
				System.out.println("当前时间是：" + sdf.format(new Date()) + "---" + str[0] + "--" + str[1] + "第" + page_index + "页,  总记录数：" + totalResults);
			} else {
				for (int i = 0; i < 123456; i++) {// TODO
					// tempMap.put("totalResults", totalResults);
					tradesSoldGetResponse = this.getTradesSoldGetResponse(i + 1, pageSize, str, shopid);
					Map<String, Object> map = this.saveTradesSoldGetResponse6667(tradesSoldGetResponse, 0, multiChannelOrderBatch);
					int totalResults_Temp = 0;
					totalResults_Temp = new Integer(map.get("totalResults").toString()).intValue();
					if (totalResults_Temp < pageSize) {
						break;
					} else {
						continue;
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return;
	}

	/**
	 * 查询卖家交易数据:解析返回的对象，保存到数据库
	 * 
	 * @param trades_JSONArray
	 * @throws Exception
	 * @returnss
	 */
	public Map<String, Object> saveTradesSoldGetResponse6667(String tradesSoldGetResponse, int flag_save_update, Long multiChannelOrderBatch) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		String retultStr = tradesSoldGetResponse;
		JSONObject obj = JSONObject.fromObject(retultStr);
		if (obj.toString().contains("Success")) {
			String success_flag = obj.get("Success").toString();
			if (StringUtil.isStringNotBlank(success_flag)) {
				obj = JSONObject.fromObject(obj.get("Success").toString());
				int totalResults = new Integer(obj.getString("total_results")).intValue();
				tempMap.put("totalResults", totalResults);
				// String total_results=obj.getString("total_results");
				// if (totalResults>0) {
				obj = JSONObject.fromObject(obj.get("items").toString());
				JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("item").toString());

				if (trades_JSONArray.size() > 0) {
					List<EdbOrders> orderModelList = new ArrayList<EdbOrders>();

					for (int j = 0; j < trades_JSONArray.size(); j++) { // 遍历 jsonarray 数组，把每一个对象转成 json 对象
						JSONObject trades_JSONObject = trades_JSONArray.getJSONObject(j);
						if (trades_JSONObject.isEmpty()) {
							continue;
						}

						// String status66 = trades_JSONObject.getString("status");
						// ---------------如果以上的num<0,即不存在,直接保存到数据库,进行如下处理-------------------------------------------------------------------------------------------------------------
						EdbOrders edbOrders = new EdbOrders();
						// String fields="out_tid,jd_delivery_time,taobao_delivery_status,taobao_delivery_order_status,delivery_name,delivery_status,delivery_time";

						String tid = trades_JSONObject.getString("tid");// 外部订单编号
						edbOrders.setTid(tid);
						String out_tid = trades_JSONObject.getString("out_tid");// 外部订单编号
						edbOrders.setOutTid(out_tid);
						String jdDeliveryTime = trades_JSONObject.getString("jd_delivery_time");// 送货时间
						edbOrders.setJdDeliveryTime(jdDeliveryTime);
						// String taobaoDeliveryStatus = trades_JSONObject.getString("taobao_delivery_status");//
						// edbOrders.setTaobaoDeliveryStatus(taobaoDeliveryStatus);
						// String taobaoDeliveryOrderStatus = trades_JSONObject.getString("taobao_delivery_order_status");//
						// edbOrders.setTaobaoDeliveryOrderStatus(taobaoDeliveryOrderStatus);
						String deliveryName = trades_JSONObject.getString("delivery_name");// 第三方快递名称
						edbOrders.setDeliveryName(deliveryName);
						String deliveryStatus = trades_JSONObject.getString("delivery_status");// 发货状态
						edbOrders.setDeliveryStatus(deliveryStatus);
						String deliveryTime = trades_JSONObject.getString("delivery_time");// 发货时间
						edbOrders.setDeliveryTime(deliveryTime);

						String express_no = trades_JSONObject.getString("express_no");// 发货状态
						edbOrders.setExpressNo(express_no);
						// 发货信息同步到包裹单
						this.inserttoOrderpackage(trades_JSONObject);

						// System.out.println("当前时间是：" + sdf.format(new Date()) + "发货信息同步到包裹单成功！  j=" + j + " , tid = " + tid);
						// //
						// if (StringUtil.isStringNotBlank(deliveryName) || StringUtil.isStringNotBlank(express_no)) {
						// int number = 0;
						// number = edbOrdersMapper.isExistByOrderNo_DeliveryStatus(edbOrders);
						// if (number == 0) {
						// // 更新订单状态：orderStatus
						// edbOrdersMapper.updateOrderStatus_E(edbOrders);// updateOrderStatus_E
						// // logger.info("更新发货信息,tid是--"+tid);
						// continue;
						// }
						// }

						// else{
						// logger.info("没有要更新的发货信息");
						// }
					}

				}
			}
		} else {
			try {
				throw new Exception("数据格式错误");
			} catch (Exception e) {
				System.out.println("请求E店宝的接口：edbTradeGet【获取订单信息】,抛出异常，没有成功");
				e.printStackTrace();
			}
		}
		return tempMap;
	}

	/**
	 * 获取发货信息并入库205
	 * 
	 * @author 常胜
	 * @date 2016-7-27 下午1:45:43
	 * @param trades_JSONObject
	 * @return void
	 */
	private void inserttoOrderpackage(JSONObject trades_JSONObject) {
		OrderPackage205 orderPackage = new OrderPackage205();
		String out_tid = trades_JSONObject.getString("out_tid");// 外部订单编号
		orderPackage.setOrderNo(out_tid);
		String storage_id = trades_JSONObject.getString("storage_id");// 仓库id
		if (isInteger(storage_id)) {
			orderPackage.setStockId(Integer.parseInt(storage_id));
		}
		String express = trades_JSONObject.getString("express");// 快递名称

		String shopid = trades_JSONObject.getString("shopid");// 快递单号
		String express_no = trades_JSONObject.getString("express_no");// 快递单号
		System.out.println("快递单号：==" + express_no);
		if (StringUtils.isNotBlank(express_no) && shopid.equals("1")) {
			if (StringUtils.isNotBlank(express)) {
				String[] str = OrderExpressUtil.getTmExpress(express);
				orderPackage.setLogisticCompanyId(Integer.parseInt(str[0]));
				orderPackage.setLogisticCompanyName(str[1]);
				orderPackage.setLogisticCompanyNo(str[2]);
			}
			orderPackage.setLogisticNo(express_no);
			orderPackage.setAddUserId(1449);
			// String shopid = trades_JSONObject.getString("shopid");// 渠道来源
			orderPackage.setMultiChannelId(Integer.parseInt(OrderExpressUtil.getMultiChannel(shopid)));
			orderPackage.setDeliveryStatus("N");
			String delivery_time = trades_JSONObject.getString("print_time");// 发货时间
			System.out.println("发货时间：" + delivery_time);
			orderPackage.setDeliveryTime(delivery_time);
			orderPackage.setAddTime(sdf.format(new Date()));
			// /
			OrderPackage205Example packageExample = new OrderPackage205Example();
			cn.com.dubbo.model.OrderPackage205Example.Criteria packageCriteria = packageExample.createCriteria();
			packageCriteria.andOrderNoEqualTo(out_tid);
			List<OrderPackage205> list = orderPackage205Mapper.selectByExample(packageExample);
			// //
			if (list.size() == 1) {
				System.out.println("订单已存在");
			}
			if (list.size() == 0) {
				orderPackage205Mapper.insert(orderPackage);
				System.out.println("订单不存在");
				System.out.println("当前时间是：" + sdf.format(new Date()) + "发货信息同步到包裹单成功！ ::::" + out_tid);
			}
		}
		// 拼多多
		if (StringUtils.isNotBlank(express_no) && shopid.equals("11")) {
			if (StringUtils.isNotBlank(express)) {
				String[] str = OrderExpressUtil.getPDExpress(express);
				orderPackage.setLogisticCompanyId(Integer.parseInt(str[0]));
				orderPackage.setLogisticCompanyName(str[1]);
				orderPackage.setLogisticCompanyNo(str[2]);
			}
			orderPackage.setLogisticNo(express_no);
			orderPackage.setAddUserId(1449);
			// String shopid = trades_JSONObject.getString("shopid");// 渠道来源
			orderPackage.setMultiChannelId(Integer.parseInt(OrderExpressUtil.getMultiChannel(shopid)));
			orderPackage.setDeliveryStatus("N");
			String delivery_time = trades_JSONObject.getString("print_time");// 发货时间
			System.out.println("发货时间：" + delivery_time);
			orderPackage.setDeliveryTime(delivery_time);
			orderPackage.setAddTime(sdf.format(new Date()));
			// /
			OrderPackage205Example packageExample = new OrderPackage205Example();
			cn.com.dubbo.model.OrderPackage205Example.Criteria packageCriteria = packageExample.createCriteria();
			packageCriteria.andOrderNoEqualTo(out_tid);
			List<OrderPackage205> list = orderPackage205Mapper.selectByExample(packageExample);
			// //
			if (list.size() == 1) {
				System.out.println("订单已存在");
			}
			if (list.size() == 0) {
				orderPackage205Mapper.insert(orderPackage);
				System.out.println("订单不存在");
				System.out.println("当前时间是：" + sdf.format(new Date()) + "发货信息同步到包裹单成功！ ::::" + out_tid);
			}
		}
		// 360
		if (StringUtils.isNotBlank(express_no) && shopid.equals("4")) {
			if (StringUtils.isNotBlank(express)) {
				String[] str = OrderExpressUtil.get360Express(express);
				orderPackage.setLogisticCompanyId(Integer.parseInt(str[0]));
				orderPackage.setLogisticCompanyName(str[1]);
				orderPackage.setLogisticCompanyNo(str[2]);
			}
			orderPackage.setLogisticNo(express_no);
			orderPackage.setAddUserId(1449);
			// String shopid = trades_JSONObject.getString("shopid");// 渠道来源
			orderPackage.setMultiChannelId(Integer.parseInt(OrderExpressUtil.getMultiChannel(shopid)));
			orderPackage.setDeliveryStatus("N");
			String delivery_time = trades_JSONObject.getString("print_time");// 发货时间
			System.out.println("发货时间：" + delivery_time);
			orderPackage.setDeliveryTime(delivery_time);
			orderPackage.setAddTime(sdf.format(new Date()));
			// /
			OrderPackage205Example packageExample = new OrderPackage205Example();
			cn.com.dubbo.model.OrderPackage205Example.Criteria packageCriteria = packageExample.createCriteria();
			packageCriteria.andOrderNoEqualTo(out_tid);
			List<OrderPackage205> list = orderPackage205Mapper.selectByExample(packageExample);
			// //
			if (list.size() == 1) {
				System.out.println("订单已存在");
			}
			if (list.size() == 0) {
				orderPackage205Mapper.insert(orderPackage);
				System.out.println("订单不存在");
				System.out.println("当前时间是：" + sdf.format(new Date()) + "发货信息同步到包裹单成功！ ::::" + out_tid);
			}
		}
		// 官网物流
		if (StringUtils.isNotBlank(express_no) && shopid.equals("3")) {
			if (StringUtils.isNotBlank(express)) {
				String[] str = OrderExpressUtil.getGWExpress(express);
				orderPackage.setLogisticCompanyId(Integer.parseInt(str[0]));
				orderPackage.setLogisticCompanyName(str[1]);
				orderPackage.setLogisticCompanyNo(str[2]);
			}
			orderPackage.setLogisticNo(express_no);
			orderPackage.setAddUserId(1449);
			// String shopid = trades_JSONObject.getString("shopid");// 渠道来源
			orderPackage.setMultiChannelId(Integer.parseInt(OrderExpressUtil.getMultiChannel(shopid)));
			orderPackage.setDeliveryStatus("N");
			String delivery_time = trades_JSONObject.getString("print_time");// 发货时间
			System.out.println("发货时间：" + delivery_time);
			orderPackage.setDeliveryTime(delivery_time);
			orderPackage.setAddTime(sdf.format(new Date()));
			// /
			OrderPackage205Example packageExample = new OrderPackage205Example();
			cn.com.dubbo.model.OrderPackage205Example.Criteria packageCriteria = packageExample.createCriteria();
			packageCriteria.andOrderNoEqualTo(out_tid);
			List<OrderPackage205> list = orderPackage205Mapper.selectByExample(packageExample);
			// //
			if (list.size() == 1) {
				System.out.println("订单已存在");
			}
			if (list.size() == 0) {
				orderPackage205Mapper.insert(orderPackage);
				System.out.println("订单不存在");
				System.out.println("当前时间是：" + sdf.format(new Date()) + "发货信息同步到包裹单成功！ ::::" + out_tid);
			}
		}
		// 3是官网，1是天猫
		if (StringUtils.isNotBlank(express_no) && !shopid.equals("1") && !shopid.equals("11") && !shopid.equals("4") && !shopid.equals("3")) {
			if (StringUtils.isNotBlank(express)) {
				String[] str = OrderExpressUtil.getExpress(express);
				orderPackage.setLogisticCompanyId(Integer.parseInt(str[0]));
				orderPackage.setLogisticCompanyName(str[1]);
				orderPackage.setLogisticCompanyNo(str[2]);
			}
			orderPackage.setLogisticNo(express_no);
			orderPackage.setAddUserId(1449);
			// String shopid = trades_JSONObject.getString("shopid");// 渠道来源
			orderPackage.setMultiChannelId(Integer.parseInt(OrderExpressUtil.getMultiChannel(shopid)));
			orderPackage.setDeliveryStatus("N");
			String delivery_time = trades_JSONObject.getString("print_time");// 发货时间
			System.out.println("发货时间：" + delivery_time);
			orderPackage.setDeliveryTime(delivery_time);
			orderPackage.setAddTime(sdf.format(new Date()));
			// /
			OrderPackage205Example packageExample = new OrderPackage205Example();
			cn.com.dubbo.model.OrderPackage205Example.Criteria packageCriteria = packageExample.createCriteria();
			packageCriteria.andOrderNoEqualTo(out_tid);
			List<OrderPackage205> list = orderPackage205Mapper.selectByExample(packageExample);
			// //
			if (list.size() == 1) {
				System.out.println("订单已存在");
			}
			if (list.size() == 0) {
				orderPackage205Mapper.insert(orderPackage);
				System.out.println("订单不存在");
				System.out.println("当前时间是：" + sdf.format(new Date()) + "发货信息同步到包裹单成功！ ::::" + out_tid);
			}
		}
	}

	/*
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	public static void main(String[] args) {
		String i = "5";
		if (!i.equals("4")) {
			System.out.println(44);
		}
	}
}
