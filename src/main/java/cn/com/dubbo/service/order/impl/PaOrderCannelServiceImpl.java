package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.PaOrderCancelService;
import cn.com.dubbo.service.order.TmOrderService;
import cn.com.dubbo.util.ClassInfo;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.HttpClientUtils;

import com.pajk.openapi.codec.client.RequestEncoder;
import com.pajk.openapi.codec.client.RequestEntity;
import com.pajk.openapi.codec.client.ResponseDecoder;

/**
 * http://localhost:8080/dubbo-provider/index.jsp
 *
 */
@Service("paOrderCannelService")
public class PaOrderCannelServiceImpl implements PaOrderCancelService{
	
	// 数据准备，服务端提供的配置参数
	public final static String apiId = "e4e0475234a625f4f78ca7477296b396#PROD"; //即上文apiId
	public final static String apiName = "pageQueryB2COrderForOpenApi"; //即上文apiName,订单查询接口
	
	public final static int pageSize = 90;//一页显示的条目数量
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private TmOrderService tmOrderService;
	
	private DataQueue<OrderModel> orderErrorQueue = new DataQueue<OrderModel>();
	
	private static final Logger logger = Logger.getLogger(PaOrderCannelServiceImpl.class);
	
	@Override
	public void dealCannelOrder(String startTime,String endTime,String multiChannel){
		
		try {
			
			//已支付未发货订单：PAID
			this.orderSerach(1,startTime,endTime,multiChannel);
			
			if(!orderErrorQueue.isEmpty()){
				this.dealOrderQueue(orderErrorQueue);
			}
		} catch (Exception e) {
			logger.error("平安订单抓取错误： " +e.getMessage(),e);
		}
		
	}
	
	private void dealOrderQueue(DataQueue<OrderModel> errorQueue){
		OrderModel model = null;
		try {
			Thread.sleep(1000);
			logger.info("休息5秒后开始保存订单异常数据...........共 "+errorQueue.getLength()+" 条");
			while(!errorQueue.isEmpty()){
				model = errorQueue.deQueue();
				boolean bl = orderService.isOrderByNo(model.getOrderNo());
				if(!bl){
					orderService.saveOrder(model);
				}
			}
		} catch (Exception e) {
			ClassInfo.show(model);
			logger.error("从队列中保存平安订单失败 ： " +e.getMessage());
		}
	}
	
	private void orderSerach(int pageIndex,String startTime,String endTime,String multiChannel) {
		
		try {
			logger.info(multiChannel+" orderSerach...............");
			
			Map<String,Object> dataMap=null;
			if(multiChannel.equals(ChannelConstant.CHANNEL_PA)||multiChannel.equals(ChannelConstant.CHANNEL_PS)){
				dataMap = this.pageQueryB2COrderForOpenApi(pageIndex,startTime,endTime,multiChannel);
			}else if(multiChannel.equals(ChannelConstant.CHANNEL_TM)){
				dataMap = tmOrderService.updateTmOrderState(pageIndex, pageSize, startTime, endTime);
			}
			
			List<OrderModel> orderList = (List<OrderModel>) dataMap.get("orderList");
			
			//处理退款的订单
			if(null!=orderList&&orderList.size()>0){
				/*DataQueue<OrderModel> orderQueue = new DataQueue<OrderModel>();
				this.updateBatchOrder(orderList, orderQueue);
				while(!orderQueue.isEmpty()){
					orderService.updateOrder(orderQueue.deQueue());
				}*/
				for(OrderModel o : orderList){
					orderService.orderCannel(o.getOrderNo(), 0,"系统定时获取，取消订单");
				}
			}
				
			int totalCount = new Integer(dataMap.get("totalCount").toString()).intValue();
			//总页码数
			int pageCount = getPageCount(totalCount);
			pageIndex = getNextPage(pageIndex, pageCount);
			
			if(pageIndex>0&&totalCount>0){
				System.out.println("pageIndex: "+pageIndex+" totalCount: "+totalCount+" pageCount: "+pageCount);
				this.orderSerach(pageIndex,startTime,endTime,multiChannel);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	private void updateBatchOrder(List<OrderModel> orderList,DataQueue<OrderModel> orderQueue){
		
		try {
			if(null!=orderList&&orderList.size()>0){
				orderService.updateBatchOrder(orderList);
			}
		} catch (Exception e) {
			if(null!=orderList&&orderList.size()>0){
				for(OrderModel model : orderList){
					logger.error("取消订单状态更新时，批量更新错误，错误orderNO："+model.getOrderNo()+",状态："+model.getOrderStatus());
					orderQueue.enQueue(model);
				}
			}
			logger.error("取消订单状态更新时，批量更新错误，错误条目："+orderList.size()+" ,错误信息："+e.getMessage(),e);
		}
	}
	
	/*
	 * 获得下页的页码数
	 * @params currentIndex 当前页码
	 * @params pageCount 总页数
	 * 
	 */
	public static int getNextPage(int currentIndex,int pageCount){
		currentIndex = currentIndex +1;
		if(currentIndex>pageCount){
			return 0;
		}
		return currentIndex;
	}
	
    /*
     * 总页数
     * @params totalCount 信息总条数
     * @return 总页码
     */
    public static int getPageCount(int totalCount) {
    	
        int size = totalCount/pageSize;//总条数/每页显示的条数=总页数
        int mod = totalCount % pageSize;//最后一页的条数
        if(mod != 0)
            size++;
        return totalCount == 0 ? 1 : size;
        
    } 
	
	
	/*
	 * 订单查询接口
	 */
	public Map<String,Object> pageQueryB2COrderForOpenApi(int pageIndex,
			String startTime,String endTime,String multiChannel) throws Exception{
		
		Map<String,Object> tempMap = null;
		
		RequestEncoder encoder = new RequestEncoder(ThirdConstant.partnerId_pa, ThirdConstant.key_pa, apiId);
		
		//string 类型参数
		String arg1 = "";
		if(multiChannel.equals(ChannelConstant.CHANNEL_PA)){
			arg1 = ThirdConstant.sellerId_pa;//卖家id
		}else{
			arg1 = "12202680605";
		}
		
		String arg2 = startTime;//查询开始时间（支付时间）
		String arg3 = endTime;//查询结束时间（支付时间）
		/*
		 * 订单状态 
		 * 1：已支付未发货订单：PAID
		 * 2：已经退款的订单:REFUND
		 */
		String arg4 = "REFUND";
		
		/*
		 * 订单状态
		 * 0：全部订单
		 * 1：普通订单
		 * 2：未审核处方药订单
		 * 3：已审核通过处方药订单
		 * 4：已审核未通过处方药订单
		 */
		String arg5 = "0";//订单状态
		String arg6 = new Integer(pageIndex).toString();//第几页
		String arg7 = new Integer(pageSize).toString();//每页的最大数，默认40，最多不大于100
		
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
		String url = ThirdConstant.baseUrl_pa + ThirdConstant.apiGroup_pa +"/"+ apiName +"?";
		String postURL = url + e.getQueryParams();
//		System.out.println("url: "+postURL);
		String postData = e.getFormParams();

		//post "application/x-www-form-urlencoded" http请求 发起请求代码示例见下方
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
			JSONArray modelArray = object.getJSONArray("model");
			if(modelArray!=null && modelArray.size()>0){
				JSONObject orderObj = null;
				JSONObject bizOrder = null;
				for(int i=0;i<modelArray.size();i++){
					orderModer = new OrderModel();
					//--------------订单信息------------------
					orderObj = modelArray.getJSONObject(i);

					bizOrder = orderObj.getJSONObject("bizOrder");
					//订单号
					String bizOrderId = bizOrder.getString("bizOrderId");
					
					bizOrderId = "PA"+bizOrderId;
					orderModer.setOrderNo(bizOrderId);//oderNo
					String storeOrderStatus = bizOrder.getString("storeOrderStatus");
					if(storeOrderStatus.equals("REFUNDED")){
						orderModer.setOrderStatus(OrderState.STATE_7.getCode());
						tempList.add(orderModer);
					}
				}
				tempMap.put("orderList", tempList);
			}
			
		} catch (Exception e) {
			logger.error("组装数据失败，"+e.getMessage(),e);
		}
		return tempMap;
	}
	
}
