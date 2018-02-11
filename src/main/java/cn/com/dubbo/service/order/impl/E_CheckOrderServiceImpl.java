package cn.com.dubbo.service.order.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.bean.OrderInfo;
import cn.com.dubbo.bean.OrderInfoExample;
import cn.com.dubbo.bean.OrderInfoExample.Criteria;
import cn.com.dubbo.mapper.OrderInfoMapper;
import cn.com.dubbo.mapper.OrderPackage205Mapper;
import cn.com.dubbo.model.OrderPackage205;
import cn.com.dubbo.model.OrderPackage205Example;
import cn.com.dubbo.service.order.E_CheckOrderService;
import cn.com.dubbo.util.OrderExpressUtil;
import cn.com.dubbo.util.StringUtil;
import cn.com.dubbo.util.TmUtil;

/**
 * 验证前1天漏单信息，从本地order_info表中取出状态为5的订单，然后根据订单号到edb接口中验证是否发货，如果发货则将订单插入到包裹表中，否则不用处理
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-28 下午3:51:26
 */
@Service("e_CheckOrderService")
// @Transactional
public class E_CheckOrderServiceImpl implements E_CheckOrderService {

	// @Autowired
	// private OrderService orderService;

	// @Autowired
	// private EdbOrdersMapper edbOrdersMapper;
	@Autowired
	private OrderPackage205Mapper orderPackage205Mapper;
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public int pageSize = 40;// static

	// private static final Logger logger = Logger.getLogger(E_CheckOrderServiceImpl.class);
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
	 * @param begin
	 * @param str
	 * @return
	 */
	public String getTradesSoldGetResponse(String begin, String end, String str) {
		System.out.println("到E店宝验证");
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
		apiparamsMap.put("method", "edbTradeGet");// 添加请求参数——接口名称
		String fields = "tid,out_tid,print_time,shopid,jd_delivery_time,storage_id,taobao_delivery_status,taobao_delivery_order_status,delivery_name,delivery_status,delivery_time,express,express_no,express_coding";
		apiparamsMap.put("fields", fields);
		apiparamsMap.put("date_type", "打印日期");
		apiparamsMap.put("out_tid", str);
		apiparamsMap.put("begin_time", begin);
		apiparamsMap.put("end_time", end);
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
		String result = "";
		result = TmUtil.getResult(testUrl, PostData);
		return result;
	}

	// ----------------------分割线------------------------------------------------------------------

	public void dealCheckOrderStatus(String begin, String end, OrderInfo info) {
		try {
			String tradesSoldGetResponse = null;
			tradesSoldGetResponse = this.getTradesSoldGetResponse(begin, end, info.getMultiChannelOrderNo());
			System.out.println("时间：：" + info.getPlatformCreateTime());
			System.out.println("E店宝返回成功");
			if (tradesSoldGetResponse != null) {
				JSONObject obj = JSONObject.fromObject(tradesSoldGetResponse);
				if (obj.toString().contains("Success")) {
					System.out.println("E店宝数据正确");
					obj = JSONObject.fromObject(obj.getString("Success"));
					String totalResults_Str = obj.getString("total_results");
					int totalResults = 0;
					if (StringUtil.isStringNotBlank(totalResults_Str)) {
						totalResults = new Integer(totalResults_Str).intValue();
						System.out.println("E店宝返回结果为：" + totalResults);
						if (totalResults > 0) {
							obj = JSONObject.fromObject(obj.get("items").toString());
							JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("item").toString());
							System.out.println("E店宝订单明细总数为：" + trades_JSONArray.size());
							if (trades_JSONArray.size() > 0) {
								for (int j = 0; j < trades_JSONArray.size(); j++) { // 遍历 jsonarray 数组，把每一个对象转成 json 对象
									JSONObject trades_JSONObject = trades_JSONArray.getJSONObject(j);
									String status = trades_JSONObject.getString("delivery_status");
									System.out.println("发货状态：" + status);
									if (status.equals("已发货")) {
										this.saveTradesSoldGetResponse6667(tradesSoldGetResponse, info);
									}
								}
							}
						}
					}
				} else {
					System.out.println("数据格式错误");
				}
			}
		} catch (Exception e1) {
			System.out.println("数据格式错误");
		}
	}

	/**
	 * 查询卖家交易数据:解析返回的对象，保存到数据库
	 * 
	 * @param stat
	 * 
	 * @param trades_JSONArray
	 * @throws Exception
	 * @returnss
	 */
	public Map<String, Object> saveTradesSoldGetResponse6667(String tradesSoldGetResponse, OrderInfo order) {
		System.out.println("进入数据解析");
		Map<String, Object> tempMap = new HashMap<String, Object>();
		String retultStr = tradesSoldGetResponse;
		JSONObject obj = JSONObject.fromObject(retultStr);
		String success_flag = obj.get("Success").toString();
		if (StringUtil.isStringNotBlank(success_flag)) {
			obj = JSONObject.fromObject(obj.get("Success").toString());
			int totalResults = new Integer(obj.getString("total_results")).intValue();
			tempMap.put("totalResults", totalResults);
			obj = JSONObject.fromObject(obj.get("items").toString());
			JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("item").toString());
			if (trades_JSONArray.size() > 0) {
				for (int j = 0; j < trades_JSONArray.size(); j++) { // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject trades_JSONObject = trades_JSONArray.getJSONObject(j);
					if (trades_JSONObject.isEmpty()) {
						continue;
					}
					// 发货信息同步到包裹单
					this.inserttoOrderpackage(trades_JSONObject, order);
				}
			}
		} else {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("请求E店宝的接口：edbTradeGet【获取订单信息】,抛出异常，没有成功");
				e.printStackTrace();
			}
		}
		return tempMap;
	}

	/**
	 * 获取发货信息并入库
	 * 
	 * @author 常胜
	 * @date 2016-7-27 下午1:45:43
	 * @param trades_JSONObject
	 * @param stat
	 * @return void
	 */
	@Transactional
	private void inserttoOrderpackage(JSONObject trades_JSONObject, OrderInfo infoOrder) {
		System.out.println("进入包裹赋值");
		int stat = infoOrder.getOrderStatus();
		OrderPackage205 orderPackage = new OrderPackage205();
		String out_tid = trades_JSONObject.getString("out_tid");// 外部订单编号
		String shopid = trades_JSONObject.getString("shopid");// 渠道来源
		orderPackage.setOrderNo(out_tid);
		String storage_id = trades_JSONObject.getString("storage_id");// 仓库id
		if (isInteger(storage_id)) {
			orderPackage.setStockId(Integer.parseInt(storage_id));
		}
		String express = trades_JSONObject.getString("express");// 快递名称
		String express_no = trades_JSONObject.getString("express_no");// 快递单号
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

	@Override
	public void CheckOrderInfo() {
		System.out.println("检查方法进入");
		// Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.add(Calendar.DAY_OF_MONTH, -2);// 前20天
		// 前20天 86400000 毫秒
		String begin = sdf.format((currentDate.getTime()).getTime());
		String end = sdf.format(new Date());
		// String begin = "2016-09-08 00:00:00";// sdf.format((currentDate.getTime()).getTime());
		// String end = "2016-09-12 00:00:00";// sdf.format(new Date());
		OrderInfoExample example = new OrderInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andPlatformCreateTimeBetween(begin, end);
		List<Integer> ins = new ArrayList<Integer>();
		ins.add(6);
		ins.add(7);
		criteria.andOrderStatusNotIn(ins);
		criteria.andSendStatusEqualTo("Y");
		// criteria.andOrderNoEqualTo("PS532268440804");
		example.setOrderByClause("add_time asc");
		example.setOffset(pageSize);
		// 查询总数进行分页
		int recordCount = orderInfoMapper.countByExample(example);// 总数
		System.out.println("漏发总数：" + recordCount);
		int size = recordCount / pageSize;// 总条数/每页显示的条数=总页数
		int mod = recordCount % pageSize;// 最后一页的条数
		if (mod != 0) {
			size++;
		}
		for (int i = 0; i < size; i++) {
			int startOffset = i * pageSize;
			example.setStartoffset(startOffset);
			System.out.println("分页查询");
			List<OrderInfo> list = orderInfoMapper.selectByExampleForPaging(example);
			System.out.println("分页查询结果：" + list.size());
			for (int j = 0; j < list.size(); j++) {
				System.out.println("订单号：" + list.get(j).getOrderNo());
				this.dealCheckOrderStatus(begin, end, list.get(j));
			}
		}
	}
}
