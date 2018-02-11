package cn.com.dubbo.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.constant.ChannelConstant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.constant.ThirdConstant;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.order.JDOrderCancelService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.util.DataQueue;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.OrderResult;
import com.jd.open.api.sdk.domain.order.OrderSearchInfo;
import com.jd.open.api.sdk.request.order.OrderSearchRequest;
import com.jd.open.api.sdk.response.order.OrderSearchResponse;

/**
 * http://localhost:8080/dubbo-provider/index.jsp
 *
 */
@Service("jdOrderCannelServiceImpl")
public class JDOrderCannelServiceImpl implements JDOrderCancelService{
	
	public JdClient client = new DefaultJdClient(ThirdConstant.SERVER_URL,
			ThirdConstant.accessToken,ThirdConstant.appKey,ThirdConstant.appSecret);
	
	private static final Logger logger = Logger.getLogger(JDOrderCannelServiceImpl.class);
	
	private final static int pageSize = 40;//一页显示的条目数量
	
	@Resource
	private OrderService orderService;
	
	
	@Override
	public void dealCannelOrder(String startString,String endString,String orderState){
		try {
			
			System.out.println("jd 查询取消订单开始 时间：" + startString + ",结束时间：" + endString);
			
			this.orderSerach(1,"",startString,endString,orderState);
			
		} catch (Exception e) {
			e.printStackTrace();
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
	 * 查询订单
	 * http://jos.jd.com/api/detail.htm?apiName=360buy.order.search&id=393#a8
	 */
	public Map<String,Object> orderSerach(int pageIndex,String method,
			String startTime,String endTime,String orderState) {
		Map<String,Object> dataMap = null;
		try {
			
			dataMap = this.pageQueryOrder(pageIndex,startTime, endTime, orderState);
			
			List<OrderModel> orderList = (List<OrderModel>) dataMap.get("orderList");
			
			//处理退款的订单
			if(null!=orderList&&orderList.size()>0){
				DataQueue<OrderModel> orderQueue = new DataQueue<OrderModel>();
				this.updateBatchOrder(orderList, orderQueue);
				while(!orderQueue.isEmpty()){
					orderService.updateOrder(orderQueue.deQueue());
				}
			}
			
			int totalCount = (Integer) dataMap.get("totalCount");
			
			//总页码数
			int pageCount = getPageCount(totalCount);
			pageIndex = getNextPage(pageIndex, pageCount);
			
			if(pageIndex>0){
				System.out.println("pageIndex: "+pageIndex+" totalCount: "+totalCount+" pageCount: "+pageCount);
				this.orderSerach(pageIndex,method,startTime,endTime,orderState);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return dataMap;
	}
	
	/*
	 * 订单查询接口
	 */
	public Map<String,Object> pageQueryOrder(int pageIndex,
			String startTime,String endTime,String orderState) throws JdException{
		
		OrderSearchRequest request=new OrderSearchRequest();
		//格式：2010-12-20 17:15:00
		request.setStartDate(startTime);
		request.setEndDate(endTime);
		/*
		 * 多订单状态可以用英文逗号隔开
		 *  1）WAIT_SELLER_STOCK_OUT 等待出库 
		 *  2）SEND_TO_DISTRIBUTION_CENER 发往配送中心（只适用于LBP，SOPL商家） 
		 *  3）DISTRIBUTION_CENTER_RECEIVED 配送中心已收货（只适用于LBP，SOPL商家） 
		 *  4）WAIT_GOODS_RECEIVE_CONFIRM 等待确认收货 
		 *  5）RECEIPTS_CONFIRM 收款确认（服务完成）（只适用于LBP，SOPL商家）
		 *   6）WAIT_SELLER_DELIVERY等待发货（只适用于海外购商家，等待境内发货 标签下的订单） 
		 *   7）FINISHED_L 完成 
		 *   8）TRADE_CANCELED 取消 
		 *   9）LOCKED 已锁定 
		 *   10）PAUSE 暂停  
		 */
		request.setOrderState(orderState);//必填
		request.setPage(new Long(pageIndex).toString());//必填
		request.setPageSize(new Long(pageSize).toString());//必填每页的条数（最大page_size 100条）  
//		request.setOptionalFields("payment_confirm_time"); //TODO 需要加入相关信息 付款确认时间/物流公司ID/售后订单标记
//		request.setOptionalFields("logistics_id");
//		request.setSortType("jingdong");//排序方式，默认升序,1是降序,其它数字都是升序
		request.setDateType("1");//查询时间类型，默认按修改时间查询。 1为按订单创建时间查询；其它数字为按订单（订单状态、修改运单号）修改时间 
		OrderSearchResponse response = client.execute(request);
		String code = response.getCode();
		Map<String,Object> map = new HashMap<String, Object>();
		if("0".equals(code)){
			OrderResult result = response.getOrderInfoResult();
			List<OrderSearchInfo> orderJdlist = result.getOrderInfoList();
			if(null!=orderJdlist&&orderJdlist.size()>0){
				map = json2Order(orderJdlist);
			}
			int orderTotal = result.getOrderTotal();
			map.put("totalCount", orderTotal);
		}
		System.out.println(response.getOrderInfoResult());
		return map;
	}
	
	
	public Map<String,Object> json2Order(List<OrderSearchInfo> orderJdlist){
		
		Map<String,Object> tempMap = new HashMap<String, Object>();
		List<OrderModel> orderList = new ArrayList<OrderModel>();
		
		if(null!=orderJdlist&&orderJdlist.size()>0){
			
			OrderModel orderModel = null; //订单信息
			
			for(OrderSearchInfo searchInfo : orderJdlist){
				
				orderModel = new OrderModel();
				//订单id 
				orderModel.setOrderNo(ChannelConstant.CHANNEL_JD+searchInfo.getOrderId());
				orderModel.setMultiChannelOrderNo(searchInfo.getOrderId());
				String orderState = searchInfo.getOrderState();
				if(orderState.equals("TRADE_CANCELED")){//已取消
					orderModel.setOrderStatus(OrderState.STATE_7.getCode());
				}
				orderList.add(orderModel);
			}
		}
		tempMap.put("orderList", orderList);
		return tempMap;
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
	
}
