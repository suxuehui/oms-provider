package cn.com.dubbo.action;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.StringUtil;
import cn.com.dubbo.util.temporary.Util;

import com.alibaba.fastjson.JSON;

/**
 * 修改69码
 * @author hhr
 */
@Controller
@RequestMapping("/update-goods69")
public class UpdateGoods69Controller  extends BaseAction{
	
	private Logger logger = Logger.getLogger(UpdateGoods69Controller.class);

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
	private OrderService orderService;
	
	
	@RequestMapping(value = "/exc", method = RequestMethod.GET)
	public @ResponseBody String  UpdateGoods69Main(@RequestParam String orderNo,@RequestParam String oldOrder69,@RequestParam String newOrder69,@RequestParam String amount,
			   									   @RequestParam String multiChannelId,@RequestParam String orderNotes,@RequestParam Integer addUserId) throws  Exception {
		try {
			response.addHeader("Access-Control-Allow-Origin","*");
			response.addHeader("Access-Control-Allow-Methods","*");
			response.addHeader("Access-Control-Max-Age","100");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.addHeader("Access-Control-Allow-Credentials","false");
	        
			orderNo = orderNo.trim();
			oldOrder69 = oldOrder69.trim();
			newOrder69 = newOrder69.trim();
			
			//根据order_no查询数据库中的order_info
			List<OrderModel> orderList = orderService.findOrderByMultiOrderNo(orderNo);
			if(null == orderList || orderList.size() == 0){
				return "oms中订单不存在!";
			}
			OrderModel order = orderList.get(0);
			if(null != order && "7".equals(order.getOrderStatus())){
				return "oms中订单已作废,请检查单号是否正确!";
			}
			multiChannelId =  order.getMultiChannelId()+"";
			
			String orderNoHead = "";
			if(null == orderNotes || "".equals(orderNotes.trim())){
				orderNotes = "缺货";
			}
			if("1".equals(multiChannelId)){
				orderNoHead = ChannelConstant.CHANNEL_TM;
			}
			if("2".equals(multiChannelId)){
				orderNoHead = ChannelConstant.CHANNEL_JD;
			}
			if("3".equals(multiChannelId)){
				orderNoHead = ChannelConstant.CHANNEL_36+"A" ;
			}
			if("4".equals(multiChannelId) ){
				orderNoHead = ChannelConstant.CHANNEL_PA ;
			}
			if("5".equals(multiChannelId) ){
				orderNoHead = ChannelConstant.CHANNEL_PS ;
			}
			if("6".equals(multiChannelId) ){
				orderNoHead =  ChannelConstant.CHANNEL_WX ;
			}
			if("7".equals(multiChannelId) ){
				orderNoHead =  "GW" ;
			}
			if("10".equals(multiChannelId) ){
				orderNoHead =  "CA" ;
			}
			if(!"".equals(orderNo) && ( orderNo.indexOf("TH") > -1 ||orderNo.indexOf("BF") > -1) ){
				orderNoHead = "";
			}
				
			String result = "";
			// 单品对单品
			if(oldOrder69.indexOf("TM") == -1 && newOrder69.indexOf("TM") == -1 ){
				 logger.info("单品对单品");
				result = UpdateGoods69(orderNo, oldOrder69, newOrder69, amount, orderNoHead, orderNotes, addUserId);
			}
			// TM对单品
			if(oldOrder69.indexOf("TM") > -1 && newOrder69.indexOf("TM") == -1 ){
				 logger.info("TM对单品");
				result = excTMtoSingle(orderNo, oldOrder69, newOrder69, amount, orderNoHead, addUserId);
			}
			// 单品对TM
			if(oldOrder69.indexOf("TM") == -1 && newOrder69.indexOf("TM") > -1 ){
				logger.info("单品对TM");
				result = excSingleToTM(orderNo, oldOrder69, newOrder69, amount, orderNoHead, addUserId);
			}
			//TM对TM
			if(oldOrder69.indexOf("TM") > -1 && newOrder69.indexOf("TM") > -1 ){
				logger.info("TM对TM");
				result = excTMToTM(orderNo, oldOrder69, newOrder69, amount, orderNoHead, addUserId);
			}
			return result;
		} catch (Exception e) {
			 
		}
		return "入口异常!";
	}
	
	

	/**
	 * 修改69码
	 * @author hhr
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	private String  UpdateGoods69(  String orderNo, String oldOrder69, String newOrder69, String amount,String orderNoHead, String orderNotes, Integer addUserId) throws  Exception {
		if(!"".equals(orderNo) &&  orderNo.indexOf("THTHTHTH") > -1 ){
			return "该订单已经更改69码四次了,不能再次替换~!";
		}
		try {
			//(1)首先查询订单在e店宝的状态
			Map<String,String> map =  edbTradeGet(orderNo); 
			if(null != map){
				String tid  = map.get("tid");
				String status  = map.get("status");
				if(null != status && "已作废".equals(status)){
					logger.warn("订单已经被作废!无需再作废了");
				}else{
					//(2)如果不是作废的状态,将订单作废
					String msg = pushCancleOrder(tid);
					if("".equals(msg)){ 
						logger.info("订单作废成功");
					}else{
						return msg;
					}
				}
				if(!"".equals(tid) && null != tid ){
						//(3)处理订单 
						orderNo = orderNoHead + orderNo ;
						boolean updateFlag = orderService.updateOrder69ByOrderNo(orderNo, oldOrder69, newOrder69,orderNotes,amount,addUserId);
						if(updateFlag){
							return  "Y";
						}
				}
			}else{
				//(3)处理订单 
				orderNo = orderNoHead + orderNo ;
				boolean updateFlag = orderService.updateOrder69ByOrderNo(orderNo, oldOrder69, newOrder69,orderNotes,amount,addUserId);
				if(updateFlag){
					return  "Y";
				}
//				return  "e店宝在该渠道下不存在对应的单号!";
			}
		} catch (Exception e) {
			 logger.error("客服修改69码失败了....",e);
		}
		return  "替换失败了,内部生成新订单失败!";
	}
	

	/**
	 * 取消订单
	 * @author hhr
	 * @param tids
	 * @return
	 * @throws ParseException
	 */
	
	private String pushCancleOrder(String tids) throws ParseException {
		Integer failNum = 0;
		try {
			
				logger.info("进入方法");
			    // 数据推送开始
				logger.info("数据推送开始");
				TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
				String[] tidsArr = tids.split(",");
				StringBuffer xml = new StringBuffer();
				xml.append("<order> ");
				for (int i = 0; i < tidsArr.length; i++) {
					xml.append(" <orderInfo>  <tid>"+tidsArr[i].toString().trim()+"</tid> </orderInfo> ");
				}
				xml.append(" </order>");
				// apiparamsMap.put("shopid", "6");// shopid:店铺id "shopid": "1"----"shop_name": "淘宝国泰永康大药房旗舰店"; //平安 6 360 4 京东 2 淘宝 1
				// apiparamsMap.put("proce_Status", "已确认");// 未确认,已确认,已作废   
				apiparamsMap.put("dbhost", dbhost);// 添加请求参数——主帐号
				apiparamsMap.put("format", format);// 添加请求参数——返回格式
				apiparamsMap.put("method", "edbTradeCancel");// 添加请求参数——接口名称
				apiparamsMap.put("slencry", "0");// 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
				apiparamsMap.put("ip", "172.16.2.28");// 添加请求参数——IP地址
				apiparamsMap.put("appkey", appkey);// 添加请求参数——appkey
				apiparamsMap.put("appscret", secret);// 添加请求参数——appscret
				apiparamsMap.put("token", token);// 添加请求参数——token
				apiparamsMap.put("v", "2.0");// 添加请求参数——版本号（目前只提供2.0版本）
				apiparamsMap.put("fields", "result,state");
				String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
				apiparamsMap.put("timestamp", timestamp);// 添加请求参数——时间戳
				apiparamsMap.put("xmlValues", xml.toString());
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
				logger.info("作废订单推送中..."); 
				result = Util.getResult(testUrl, PostData);
				
				// System.out.println(result);
				JSONObject obj = JSONObject.fromObject(result);
				if (obj.toString().contains("\"response_Code\":\"200\"")) {
					logger.info("推送成功");
					System.out.println("当前时间是：" + sdf.format(new Date()) + "   订单推送成功");
					return "";
				} else {
					try {
						JSONObject suc = (JSONObject) obj.get("Success");
						if(null != suc && !"".equals(suc)){
							JSONObject items = (JSONObject)suc.get("items");
							JSONArray item =  (JSONArray)items.get("item");
							JSONObject listJson = (JSONObject) item.get(0);
							logger.error("推送失败的原因是: " + listJson.get("response_Msg"));
							System.out.println("推送失败的原因是: " + listJson.get("response_Msg"));
							return (String) listJson.get("response_Msg");
						}
					} catch (Exception e) { 
						logger.error("失败返回的json是: " + result);
						System.out.println("失败返回的json是: " + result);
					}
					failNum++;
					logger.info("推送失败");
					System.out.println("当前时间是：" + sdf.format(new Date()) + "   订单推送失败");
				}
			logger.info("推送失败的总数量:"+failNum+" ******************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "订单作废失败了...";
	}
	
	

	//获取订单信息
	private  Map<String,String> edbTradeGet(String orderNo){
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
        apiparamsMap.put("method", "edbTradeGet");//添加请求参数——接口名称
        String fields="tid,type,status"; 
		apiparamsMap.put("fields", fields);
		apiparamsMap.put("date_type", "订货日期");//添加请求参数——主帐号---订单产生时间     付款日期   获取日期
		
		apiparamsMap.put("begin_time", "2016-04-01 00:00:00"); 
		apiparamsMap.put("end_time", sdf.format(new Date()));//2016-07-26 15:57:54
		
//		apiparamsMap.put("shopid", shopid);//shopid:店铺id   "shopid": "1"----"shop_name": "淘宝国泰永康大药房旗舰店";   //平安 6  360 4  京东 2  淘宝 1
		apiparamsMap.put("out_tid", orderNo);//外部订单ID
		apiparamsMap.put("page_size", "1");
		apiparamsMap.put("page_no", "1");
		
		apiparamsMap.put("dbhost", dbhost);//添加请求参数——主帐号
		apiparamsMap.put("appkey",appkey);//添加请求参数——appkey
        apiparamsMap.put("format", format);//添加请求参数——返回格式
        apiparamsMap.put("v", "2.0");//添加请求参数——版本号（目前只提供2.0版本）
        apiparamsMap.put("slencry","0");//添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
		apiparamsMap.put("ip","172.16.2.28");//添加请求参数——IP地址  172.16.2.28:是正确的
		
		apiparamsMap.put("appscret",secret);//添加请求参数——appscret
		apiparamsMap.put("token",token);//添加请求参数——token
		String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		apiparamsMap.put("timestamp", timestamp);//添加请求参数——时间戳
//
		//获取数字签名
		String sign = Util.md5Signature(apiparamsMap, appkey);//appkey   secret

		apiparamsMap.put("sign", sign);

		StringBuilder param = new StringBuilder();

		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			if(e.getKey()!="appscret" && e.getKey()!="token"){
//				param.append("&").append(e.getKey()).append("=").append(e.getValue());
				
				if(e.getKey()=="shop_id" || e.getKey()=="wangwang_id" || e.getKey()=="date_type"){
					try {
						param.append("&").append(e.getKey()).append("=").append(Util.encodeUri(e.getValue()));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}else{
					param.append("&").append(e.getKey()).append("=").append(e.getValue());
				}
			}
		}
		
		String PostData="";
		PostData=param.toString().substring(1);
		System.out.println(testUrl+"?"+PostData);
		String result="";
		result=Util.getResult(testUrl,PostData);
		//解析
		JSONObject obj = JSONObject.fromObject(result);
		obj = JSONObject.fromObject(obj.getString("Success"));
		String totalResults_Str = obj.getString("total_results");
		int totalResults=0;
		if(StringUtil.isStringNotBlank(totalResults_Str)){
			totalResults=new Integer(totalResults_Str).intValue();
		}  
		if(totalResults > 0){
			obj = JSONObject.fromObject(obj.get("items").toString());
			JSONArray trades_JSONArray = JSONArray.fromObject(obj.get("item").toString());
			 if(trades_JSONArray.size()>0){
				 JSONObject trades_JSONObject  = trades_JSONArray.getJSONObject(0);
				 String tid = trades_JSONObject.getString("tid");
				 String status = trades_JSONObject.getString("status");
				 System.out.println("tid == " + tid);
				 Map<String,String > map = new HashMap<String, String>();
				 map.put("tid", tid);
				 map.put("status", status);
				 return map;
			 } 
		}
		return null;
	}	
	
	
	
	/**
	 * 由TM码替换成单品
	 */
	private     String  excTMtoSingle(  String orderNo,  String oldTmNo,  String newOrder69,  String amount,String orderNoHead,  Integer addUserId) throws  Exception {
		if(!"".equals(orderNo) &&  orderNo.indexOf("THTHTHTH") > -1 ){
			return "该订单已经更改69码四次了,不能再次替换~!";
		}
		try {
			//(1)首先查询订单在e店宝的状态
			Map<String,String> map =  edbTradeGet(orderNo); 
			if(null != map){
				String tid  = map.get("tid");
				String status  = map.get("status");
				if(null != status && "已作废".equals(status)){
					logger.warn("订单已经被作废!无需再作废了");
				}else{
					//(2)如果不是作废的状态,将订单作废
					String msg = pushCancleOrder(tid);
					if("".equals(msg)){ 
						logger.info("订单作废成功");
					}else{
						return msg;
					}
				}
				if(!"".equals(tid) && null != tid ){
						//(3)处理订单 
						orderNo = orderNoHead + orderNo ;
						boolean updateFlag = orderService.updateOrderTmTo69(orderNo, oldTmNo, newOrder69,"原商品缺货",amount,addUserId);
						if(updateFlag){
							return  "Y";
						}
				}
			}else{
				//(3)处理订单 
				orderNo = orderNoHead + orderNo ;
				boolean updateFlag = orderService.updateOrderTmTo69(orderNo, oldTmNo, newOrder69,"原商品缺货",amount,addUserId);
				if(updateFlag){
					return  "Y";
				}
//				return  "e店宝在该渠道下不存在对应的单号!" ;
			}
		} catch (Exception e) {
			 logger.error("由TM码修改为单品失败了....",e);
		}
		 
		return  "替换失败了,内部生成新订单失败!";
	}
	
	
	/**
	 * 由TM码替换成单品
	 */
	private  String  excSingleToTM(  String orderNo,  String oldGoods69,  String newOrderTM,  String amount, String orderNoHead,  Integer addUserId) throws  Exception {
		if(!"".equals(orderNo) &&  orderNo.indexOf("THTHTHTH") > -1 ){
			return "该订单已经更改69码四次了,不能再次替换~!";
		}
		try {
		 
			//(1)首先查询订单在e店宝的状态
			Map<String,String> map =  edbTradeGet(orderNo); 
			if(null != map){
				String tid  = map.get("tid");
				String status  = map.get("status");
				if(null != status && "已作废".equals(status)){
					logger.warn("订单已经被作废!无需再作废了");
				}else{
					//(2)如果不是作废的状态,将订单作废
					String msg = pushCancleOrder(tid);
					if("".equals(msg)){ 
						logger.info("订单作废成功");
					}else{
						return msg;
					}
				}
				if(!"".equals(tid) && null != tid ){
						//(3)处理订单 
						orderNo = orderNoHead + orderNo ;
						boolean updateFlag = orderService.excSingleToTM(orderNo, oldGoods69, newOrderTM,"原商品缺货",amount,addUserId,orderNoHead);
						if(updateFlag){
							return  "Y";
						}
				}
			}else{
				//(3)处理订单 
				orderNo = orderNoHead + orderNo ;
				boolean updateFlag = orderService.excSingleToTM(orderNo, oldGoods69, newOrderTM,"原商品缺货",amount,addUserId,orderNoHead);
				if(updateFlag){
					return  "Y";
				}
//				return  "e店宝在该渠道下不存在对应的单号!" ;
			}
		} catch (Exception e) {
			 logger.error("由TM码修改为单品失败了....",e);
		}
		 
		return  "替换失败了,内部生成新订单失败!";
	}


	//TM TO TM
	private String excTMToTM(String orderNo, String oldOrderTM,String newOrderTM, String amount, String orderNoHead,Integer addUserId) {
			if(!"".equals(orderNo) &&  orderNo.indexOf("THTHTHTH") > -1 ){
				return "该订单已经更改69码四次了,不能再次替换~!";
			}
			try {
			 
				//(1)首先查询订单在e店宝的状态
				Map<String,String> map =  edbTradeGet(orderNo); 
				if(null != map){
					String tid  = map.get("tid");
					String status  = map.get("status");
					if(null != status && "已作废".equals(status)){
						logger.warn("订单已经被作废!无需再作废了");
					}else{
						//(2)如果不是作废的状态,将订单作废
						String msg = pushCancleOrder(tid);
						if("".equals(msg)){ 
							logger.info("订单作废成功");
						}else{
							return msg;
						}
					}
					if(!"".equals(tid) && null != tid ){
							//(3)处理订单 
							orderNo = orderNoHead + orderNo ;
							boolean updateFlag = orderService.excTMToTM(orderNo, oldOrderTM, newOrderTM,"原商品缺货",amount,addUserId);
							if(updateFlag){
								return  "Y";
							}
					}
				}else{
					//(3)处理订单 
					orderNo = orderNoHead + orderNo ;
					boolean updateFlag = orderService.excTMToTM(orderNo, oldOrderTM, newOrderTM,"原商品缺货",amount,addUserId);
					if(updateFlag){
						return  "Y";
					}
//					return  "e店宝在该渠道下不存在对应的单号!" ;
				}
			} catch (Exception e) {
				 logger.error("由TM码修改为单品失败了....",e);
			}
			 
			return  "替换失败了,内部生成新订单失败!";
	}
	
	public static void main(String[] args) {
		 Map<String, String> map = new UpdateGoods69Controller().edbTradeGet("476406670108");
		 System.out.println(JSON.toJSONString(map)); 
		
	}
}
