package cn.com.dubbo.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.util.BaseObject;
import cn.com.dubbo.util.FunctionUtil;
import cn.com.dubbo.util.HttpClientUtils;
import cn.com.dubbo.util.InitUtils;
import cn.com.dubbo.util.JsonUtil;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;

import com.pajk.openapi.codec.client.RequestEncoder;
import com.pajk.openapi.codec.client.RequestEntity;
import com.pajk.openapi.codec.client.ResponseDecoder;

/**
 * 订单信息展示
 */
@Controller
@RequestMapping("show")
public class OrderShow {
	
	private Logger logger = Logger.getLogger(OrderShow.class);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static String apiId = "e4e0475234a625f4f78ca7477296b396#PROD"; //即上文apiId
	
	/**
	 * 展示平安订单信息
	 */
	@RequestMapping(value="paOrders")
	@ResponseBody
	public void paOrders(HttpServletRequest request,
			HttpServletResponse response){
		BaseObject object = new BaseObject();
		try {
			object.setCode(InitUtils.STATUS_OK);
			int pageIndex = 1;
			//1：已支付未发货订单：PAID
			// 2：已经退款的订单:REFUND
			String method = "PAID";
			String orderState = "0";
			String startTime = "1470499200";
			String endTime ="1470585599";
			int multi = 1;
			Map<String,Object> map = pageQueryB2COrderForOpenApi(pageIndex, method, orderState, startTime, endTime, multi);
			object.setObj(map);
		} catch (Exception e) {
			object.setCode(InitUtils.STATUS_300);
			object.setMsg("系统异常，请联系管理员");
			logger.error("调用处方药审核接口异常："+e.getMessage(),e);
		}
		FunctionUtil.responseIO(JsonUtil.object2json(object), response);
	}
	
	
	/*
	 * 订单查询接口
	 */
	public Map<String,Object> pageQueryB2COrderForOpenApi(int pageIndex,String method,
			String orderState,String startTime,String endTime,int multi) throws Exception{
		
		Map<String,Object> tempMap = null;
		
		RequestEncoder encoder = new RequestEncoder(ThirdConstant.partnerId_pa, ThirdConstant.key_pa, apiId);
		//string 类型参数
		String arg1 = "";
		if(1==multi){
			arg1 = ThirdConstant.sellerId_pa;//平安好医生
		}else{
			arg1 = "12202680605";//平安商城
		}
		
		String arg2 = startTime;//查询开始时间（支付时间）
		String arg3 = endTime;//查询结束时间（支付时间）
		/*
		 * 订单状态 
		 * 1：已支付未发货订单：PAID
		 * 2：已经退款的订单:REFUND
		 */
		String arg4 = method;
		
		/*
		 * 订单状态
		 * 0：全部订单
		 * 1：普通订单
		 * 2：未审核处方药订单
		 * 3：已审核通过处方药订单
		 * 4：已审核未通过处方药订单
		 */
		String arg5 = orderState;
		String arg6 = new Integer(pageIndex).toString();//第几页
		String arg7 = new Integer(100).toString();//每页的最大数，默认40，最多不大于100
		
		//按接口定义的参数顺序放入参数
		encoder.addParameter(arg1);
		encoder.addParameter(arg2);
		encoder.addParameter(arg3);
		encoder.addParameter(arg4);
		encoder.addParameter(arg5);
		encoder.addParameter(arg6);
		encoder.addParameter(arg7);
		
		//进行加密
		RequestEntity e = encoder.encode();

		//拼装url
		String url = ThirdConstant.baseUrl_pa + ThirdConstant.apiGroup_pa +"/pageQueryB2COrderForOpenApi?";
		String postURL = url + e.getQueryParams();
		String postData = e.getFormParams();

		String text = HttpClientUtils.do_post(postURL, postData);
		
		// 解析返回值(此处使用fastjson 1.2.4解析json字符串，也可使用其他json解析类库)
		Map obj = JSONObject.fromObject(text);
		String objectStr = obj.get("object").toString();
		Integer code =  (Integer)obj.get("code");
		//调用成功
		if(code==0){
		    //解码返回结果
			ResponseDecoder decoder =new ResponseDecoder(ThirdConstant.key_pa);
			decoder.decode(objectStr);
			String resultData = decoder.getData();
			JSONObject object = new JSONObject().fromObject(resultData);
			int resultCode = object.getInt("resultCode");
			if(0==resultCode){//返回成功
				//总数量
				int totalCount = object.getInt("totalCount");
				tempMap = String2Order(object);
				tempMap.put("totalCount", totalCount);
			}else{
				logger.error("错误码："+resultCode + ",错误信息："+objectStr);
			}
		}else{
			logger.error("错误码code："+code + ",错误信息："+obj);
		}
		return tempMap;
	}
	
	private Map<String,Object> String2Order(JSONObject object){
		
		Map<String,Object> tempMap = new HashMap<String, Object>();
		try {
			List<OrderModel> tempList = new ArrayList<OrderModel>();
			OrderModel orderModer = null;
			
			List<OrderItemModel> itemList = new ArrayList<OrderItemModel>();
			JSONArray modelArray = object.getJSONArray("model");
			
			if(modelArray!=null && modelArray.size()>0){
				JSONObject orderObj = null;
				JSONObject bizOrder = null;
				JSONArray items = null;
				
				String nowDate = sdf.format(new Date());
				OrderItemModel detailModel = null;
				
				for(int i=0;i<modelArray.size();i++){
					
					orderModer = new OrderModel();
					
					//--------------订单信息------------------
					orderObj = modelArray.getJSONObject(i);

					bizOrder = orderObj.getJSONObject("bizOrder");
					//订单号
					String bizOrderId = bizOrder.getString("bizOrderId");
					
					orderModer.setMultiChannelOrderNo(bizOrderId);//没有PA标志
					orderModer.setOrderNo(bizOrderId);//oderNo
					//商品信息
					items = bizOrder.getJSONArray("items");
					JSONObject itemsTmp=null;
					for(int j=0;j<items.size();j++){
						detailModel = new OrderItemModel();
						itemsTmp = items.getJSONObject(j);
						detailModel.setOrderNo(bizOrderId);
						//合作方商品ID
						detailModel.setGoodsNo_69(itemsTmp.getString("referId"));
						//商品名
						detailModel.setName(itemsTmp.getString("itemName"));
						//实际价格，销售单价，单位分
						BigDecimal actualPrice = new BigDecimal(itemsTmp.getLong("actualPrice"));
						detailModel.setOldPrice(MathUtil.changeF2Y(actualPrice));
						//购买数量
						detailModel.setAmount(itemsTmp.getLong("buyAmount"));
						//支付时间
						detailModel.setAddTime(nowDate);
						detailModel.setGroupStatus(null);
						//商品skuId
						detailModel.setPriceDis(new BigDecimal(0));
						itemList.add(detailModel);
					}
					//------------购买者相关 信息---------------
					JSONObject buyerInfo = orderObj.getJSONObject("buyerInfo");
					String buyerNick = buyerInfo.getString("buyerNick");
					if(!StringUtil.isBlank(buyerNick)){
						orderModer.setMemberNick(buyerNick);
					}
					
					//----------------支付相关信息------------
					JSONObject payOrder = orderObj.getJSONObject("payOrder");
					//支付状态
					long payTime = bizOrder.getLong("payTime");
					//原始运费
					BigDecimal originalPostFee = new BigDecimal(payOrder.getLong("originalPostFee"));
					orderModer.setDeliveryFeeOld(MathUtil.changeF2Y(originalPostFee));
					//实际运费
					BigDecimal actualPostFee = new BigDecimal(payOrder.getLong("actualPostFee"));
					orderModer.setDeliveryFee(MathUtil.changeF2Y(actualPostFee));
					//实际商品销售总价
					BigDecimal actualProductFee = new BigDecimal(payOrder.getLong("actualProductFee"));
					orderModer.setSkuFee(MathUtil.changeF2Y(actualProductFee));
					//用户实际支付的现金
					BigDecimal actualTotalFee = new BigDecimal(payOrder.getLong("actualTotalFee"));
					orderModer.setPaidFee(MathUtil.changeF2Y(actualTotalFee));
					orderModer.setOrderFee(MathUtil.changeF2Y(actualTotalFee));
					//优惠价格=商品总价+原始邮费-用户实际支付的现金
					BigDecimal discountFee = MathUtil.sub(MathUtil.add(actualProductFee, actualPostFee), actualTotalFee);
					orderModer.setActivityDiscountFee(MathUtil.changeF2Y(discountFee));
					//-------物流相关---------------
					JSONObject lgOrder = orderObj.getJSONObject("lgOrder");
					//收货人
					orderModer.setReceiveUser(lgOrder.getString("fullName"));
					//完整收货地址
					orderModer.setReceiveFullAddress(orderModer.getReceiveAddress()+lgOrder.getString("address"));
					//收货手机
					orderModer.setReceiveMobile(lgOrder.getString("mobilePhone"));
					
					orderModer.setAddTime(nowDate);
					orderModer.setItemList(itemList);
					tempList.add(orderModer);
					
				}
				tempMap.put("orderList", tempList);
			}
			
		} catch (Exception e) {
			logger.error("组装数据失败，"+e.getMessage(),e);
		}
		return tempMap;
	}
	
}
