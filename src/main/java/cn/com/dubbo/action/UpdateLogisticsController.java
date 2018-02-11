package cn.com.dubbo.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.bean.TmpHhr;
import cn.com.dubbo.service.order.ProductInventoryService;
import cn.com.dubbo.util.temporary.Util;

import com.alibaba.fastjson.JSON;

/**
 * 修改69码
 * @author hhr
 */
@Controller
@RequestMapping("/update-logistics")
public class UpdateLogisticsController  extends BaseAction{
	
	private Logger logger = Logger.getLogger(UpdateLogisticsController.class);

	private String testUrl = BaseData.edbUrl;
	// 申请的appkey
	private String appkey =  BaseData.appkey;
	// 申请的secret
	private  String secret = BaseData.secret;
	// 申请的token
	private String token = BaseData.token;
	// 主帐号
	private String dbhost = BaseData.dbhost;
	// 返回格式 
	private String format = BaseData.format;
	//
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private ProductInventoryService productInventoryService;

	//main
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() throws Exception {
		 
		List<TmpHhr> list = productInventoryService.queryTmpHhr(); 
		for (int i = 0; i < list.size(); i++) {
			TmpHhr tmp = list.get(i);
//			if(i == 0){
				System.out.println(JSON.toJSONString(tmp));  
				//更新物流
//				this.updateLogistics(tid, delivery_time, express, express_deliveryno, weight); 
				this.updateLogistics(tmp.getTid().trim(), tmp.getDelivery_time().trim(), tmp.getLogistic_company_name().trim(), tmp.getLogistic_no().trim(), null);
				System.out.println("********************************  " + i + "  **************************************");
				//改变状态
//				this.updateLogisticsStatus(tmp.getTid(), out_tid, express, express_no, express_code, printer, print_time, inspecter, inspect_time, is_inspect_delivery, delivery_operator, delivery_time, barCode, inspection_num);
//				this.updateLogisticsStatus(tmp.getTid(), tmp.getOrder_no(), null, tmp.getLogistic_no(), null, null, null, null, null, null, null, sdf.format(new Date()), null, null);
//			} 
		}
	}
	
	
	//更新物流
	private String updateLogistics(String tid,String delivery_time,String express,String express_deliveryno,String weight) throws  Exception {
		
		Integer failNum = 0;
		try {
//				logger.info("进入更新物流方法");
			    // 数据推送开始
				TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
//				String[] tidsArr = tids.split(",");
 
				apiparamsMap.put("tid",tid);//订单编号，根据订单编号，修改该订单的相关信息
				apiparamsMap.put("delivery_time",delivery_time);//发货时间，用于修改订单的发货时间，验货时间和称重时间也会被填充为这个值。
				apiparamsMap.put("express",express);//快递公司，用来完善订单，记录快递公司
				apiparamsMap.put("express_deliveryno",express_deliveryno);//快递单号，用来完善订单，记录快递单号
//				apiparamsMap.put("weight",weight);//快件重量，用来完善订单，记录快件的重量
				
				apiparamsMap.put("dbhost", dbhost);// 添加请求参数——主帐号
				apiparamsMap.put("format", format);// 添加请求参数——返回格式
				apiparamsMap.put("method", "edbLogisticsCompaniesUpdate");// 更新物流信息
				apiparamsMap.put("slencry", "0");// 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
				apiparamsMap.put("ip", "172.16.2.28");// 添加请求参数——IP地址
				apiparamsMap.put("appkey", appkey);// 添加请求参数——appkey
				apiparamsMap.put("appscret", secret);// 添加请求参数——appscret
				apiparamsMap.put("token", token);// 添加请求参数——token
				apiparamsMap.put("v", "2.0");// 添加请求参数——版本号（目前只提供2.0版本）
				apiparamsMap.put("fields", "result,state");
				String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
				apiparamsMap.put("timestamp", timestamp);// 添加请求参数——时间戳
//				apiparamsMap.put("xmlValues", xml.toString());
				// 获取数字签名
				String sign = Util.md5Signature(apiparamsMap, appkey);
				apiparamsMap.put("sign", sign);
				StringBuilder param = new StringBuilder();
				for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, String> e = it.next();
					if (e.getKey() != "appscret" && e.getKey() != "token") {
						if (e.getKey() == "xmlValues") {
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
				logger.info("更新物流信息推送中..."); 
				result = Util.getResult(testUrl, PostData);
				JSONObject obj = JSONObject.fromObject(result);

				System.out.println(obj);
				
			logger.info("更新物流信息推送失败的总数量:"+failNum+" ******************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "更新物流信息 失败了...";
	}
	
	

	//改变(标记)状态
	private String updateLogisticsStatus(String tid,String out_tid,String express,String express_no,String express_code,String printer,String print_time,
			String inspecter,String inspect_time,String is_inspect_delivery,String delivery_operator,String delivery_time,String barCode,String inspection_num) throws  Exception {
		
		Integer failNum = 0;
		try {
				logger.info("进入更新(订单状态)方法");
			    // 数据推送开始
				TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
//				String[] tidsArr = tids.split(",");
 
				apiparamsMap.put("tid",tid);// 订单编号
				apiparamsMap.put("out_tid",out_tid);// 外部平台单号
//				apiparamsMap.put("express",express);// 快递公司
//				apiparamsMap.put("express_no",express_no);// 快递单号
				apiparamsMap.put("express_code",express_code);// 快递代码
//				apiparamsMap.put("printer",printer);// 打印员
//				apiparamsMap.put("print_time",print_time);// 打印时间
//				apiparamsMap.put("inspecter",inspecter);// 验货员
//				apiparamsMap.put("inspect_time",inspect_time);// 验货时间
//				apiparamsMap.put("is_inspect_delivery",is_inspect_delivery);// 是否验货后回传快递信息,验货后回传验货信息必须传1,打印后回传快递信息传0
//				apiparamsMap.put("delivery_operator",delivery_operator);// 发货员
				apiparamsMap.put("delivery_time",delivery_time);// 发货时间
//				apiparamsMap.put("barCode",barCode);// 条形码
//				apiparamsMap.put("inspection_num",inspection_num);// 验货数量
			 
				
				apiparamsMap.put("dbhost", dbhost);// 添加请求参数——主帐号
				apiparamsMap.put("format", format);// 添加请求参数——返回格式
				apiparamsMap.put("method", "1.2.	edbTradeUpdate");// 更新订单的状态
				apiparamsMap.put("slencry", "0");// 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
				apiparamsMap.put("ip", "172.16.2.28");// 添加请求参数——IP地址
				apiparamsMap.put("appkey", appkey);// 添加请求参数——appkey
				apiparamsMap.put("appscret", secret);// 添加请求参数——appscret
				apiparamsMap.put("token", token);// 添加请求参数——token
				apiparamsMap.put("v", "2.0");// 添加请求参数——版本号（目前只提供2.0版本）
				apiparamsMap.put("fields", "result,state");
				String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
				apiparamsMap.put("timestamp", timestamp);// 添加请求参数——时间戳
//				apiparamsMap.put("xmlValues", xml.toString());
				// 获取数字签名
				String sign = Util.md5Signature(apiparamsMap, appkey);
				apiparamsMap.put("sign", sign);
				StringBuilder param = new StringBuilder();
				for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, String> e = it.next();
					if (e.getKey() != "appscret" && e.getKey() != "token") {
						if (e.getKey() == "xmlValues") {
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
				logger.info("更新(订单状态)推送中..."); 
				result = Util.getResult(testUrl, PostData);
				JSONObject obj = JSONObject.fromObject(result);
				
				System.out.println(obj);
				
				if (obj.toString().contains("\"response_Code\":\"200\"")) {
					logger.info("更新(订单状态)推送成功");
					System.out.println("当前时间是：" + sdf.format(new Date()) + "  更新(订单状态) 订单推送成功");
					return "";
				} else {
					try {
						JSONObject suc = (JSONObject) obj.get("Success");
						if(null != suc && !"".equals(suc)){
							JSONObject items = (JSONObject)suc.get("items");
							JSONArray item =  (JSONArray)items.get("item");
							JSONObject listJson = (JSONObject) item.get(0);
							logger.error("更新(订单状态)推送失败的原因是: " + listJson.get("response_Msg"));
							System.out.println("更新(订单状态)推送失败的原因是: " + listJson.get("response_Msg"));
							return (String) listJson.get("response_Msg");
						}
					} catch (Exception e) { 
						logger.error("失败返回的json是: " + result);
						System.out.println("失败返回的json是: " + result);
					}
					failNum++;
					logger.info("更新(订单状态)推送失败");
					System.out.println("当前时间是：" + sdf.format(new Date()) + "   更新(订单状态)推送失败");
				}
			logger.info("更新(订单状态)推送失败的总数量:"+failNum+" ******************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "更新(订单状态) 失败了...";
	}
}
