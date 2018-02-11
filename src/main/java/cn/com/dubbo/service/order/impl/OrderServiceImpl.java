package cn.com.dubbo.service.order.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.base.BaseData;
import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.mapper.ExceptionInfoMapper;
import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.mapper.OrderItemMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.mapper.PaymentLogMapper;
import cn.com.dubbo.model.ExceptionInfo;
import cn.com.dubbo.model.GoodsChannelPrice;
import cn.com.dubbo.model.GoodsGroupItemModel;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.LogisticChannelModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.PaymentLogModel;
import cn.com.dubbo.service.inter.GoodsChannelService;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.service.inter.PaymentLogService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.OrderSupplementService;
import cn.com.dubbo.util.DataQueue;
import cn.com.dubbo.util.DateUtil;
import cn.com.dubbo.util.MathUtil;
import cn.com.dubbo.util.StringUtil;
import cn.com.dubbo.util.temporary.Util;

import com.alibaba.fastjson.JSON;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	static int num = 0;
	@Resource
	private OrderMapper orderMapper;

	@Autowired
	private ExceptionInfoMapper exceptionInfoMapper;

	@Resource
	private PaymentLogMapper payLogMapper;

	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Resource
	private LogisticMapper logisticMapper;
	
	@Resource
	private OrderItemMapper orderItemMapper;
	
	@Resource
	private OrderSupplementService supplementService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private GoodsMapper goodsMapper;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private PaymentLogService paymentLogService;
	
	@Resource
	private GoodsChannelService goodsChannelService;
	
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
	
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public OrderModel findOrderByNo(String orderNo) {

		OrderModel order = orderMapper.findOrderByNo(orderNo);

		return order;
	}
	
	
	/**
	 * 根据原始订单号查询所有的订单信息
	 */
	@Override
	public List<OrderModel> findOrderByMultiOrderNo(String multiChannelNo){
		List<OrderModel> list = orderMapper.findOrderByMultiOrderNo(multiChannelNo);
		return list;
	}

	@Override
	public boolean isOrderByNo(String orderNo) {
		int retCode = 0;
		retCode = orderMapper.isOrderByNo(orderNo);
		if (0 == retCode) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isOrderItemByNo(String orderNo) {
		int retCode = 0;
		retCode = orderMapper.isOrderItemByNo(orderNo);
		if (0 == retCode) {
			return false;
		}
		return true;
	}

	@Override
	public void saveOrder(OrderModel orderModel) {
		orderMapper.saveOrder(orderModel);
	}

	@Override
	public void saveOrderItem(OrderItemModel itemModel) {
		orderMapper.saveOrderItem(itemModel);
	}

	/**
	 * 订单批量保存
	 */
	@Override
	@Transactional
	public int saveBatchOrder(List<OrderModel> orderList, DataQueue<OrderModel> queue) {

		List<OrderModel> batchList = new ArrayList<OrderModel>();
		int totalNum = 0;
		try {

			if (null != orderList && orderList.size() > 0) {

				batchList = addAddBatchOrderListTemp(orderList);
				/*
				 * 最好能控制批量保存时的最大数量 批量保存
				 */
				if (null != batchList && batchList.size() > 0) {
					totalNum = batchList.size();
					orderMapper.saveBatchOrder(batchList);
				}
			}
		} catch (Exception e) {
			if (null != batchList && batchList.size() > 0) {
				for (OrderModel model : batchList) {
					logger.error("订单保存数据库错误 ，错误订单号：" + model.getOrderNo());
					queue.enQueue(model);
				}
			}
			logger.error("订单批量保存数据库错误 ，错误条目：" + batchList.size() + " ,错误信息：" + e.getMessage(), e);
		}
		return totalNum;
	}

	/*
	 * @Transactional private void addAddBatchOrderList(OrderModel model,List<OrderModel> batchList){ try { boolean bl = this.isOrderByNo(model.getOrderNo()); if(!bl){ batchList.add(model); } } catch (Exception e) { logger.error("校验订单是否存入数据出错，订单号："+model.getOrderNo()+" 错误信息："+e.getMessage(),e); } }
	 */

	private List<OrderModel> addAddBatchOrderListTemp(List<OrderModel> orderList) {
		// 已存在的数据
		List<String> orderNos = this.batchQueryOrder(orderList);
		List<OrderModel> tempList = new ArrayList<OrderModel>();
		if (null != orderNos && orderNos.size() > 0) {
			for (OrderModel order : orderList) {
				if (!orderNos.contains(order.getOrderNo())) {
					tempList.add(order);
				}
			}
			return tempList;
		}
		return orderList;
	}

	/**
	 * 订单明细批量保存
	 */
	@Transactional
	public void saveBatchOrderItem(List<OrderItemModel> orderItemList, DataQueue<OrderItemModel> queue) {

		List<OrderItemModel> batchItemList = new ArrayList<OrderItemModel>();
		try {
			if (null != orderItemList && orderItemList.size() > 0) {
				batchItemList = addAddBatchOrderItemListTemp(orderItemList);
				/*
				 * TODO 最好能控制批量保存时的最大数量 批量保存
				 */
				if (null != batchItemList && batchItemList.size() > 0) {
					orderMapper.saveBatchOrderItem(batchItemList);
				}
			}
		} catch (Exception e) {
			if (null != batchItemList && batchItemList.size() > 0) {
				for (OrderItemModel model : orderItemList) {
					logger.error("订单item保存数据库错误 ，错误订单号：" + model.getOrderNo());
					queue.enQueue(model);
				}
			}
			logger.error("订单item保存数据库错误 ，错误条目：" + batchItemList.size() + " ,错误信息：" + e.getMessage(), e);
		}
	}

	/*
	 * @Transactional private void addAddBatchOrderItemList(OrderItemModel model,List<OrderItemModel> batchItemList){ try { boolean bl = this.isOrderItemByNo(model.getOrderNo()); if(!bl){ batchItemList.add(model); } } catch (Exception e) { logger.error("校验订单item是否存入数据出错，订单号："+model.getOrderNo()+" 错误信息："+e.getMessage(),e); } }
	 */

	private List<OrderItemModel> addAddBatchOrderItemListTemp(List<OrderItemModel> orderItemList) {
		// 已存在的数据
		List<String> orderItemNos = this.batchQueryOrderItem(orderItemList);
		List<OrderItemModel> tempList = new ArrayList<OrderItemModel>();
		if (null != orderItemNos && orderItemNos.size() > 0) {
			for (OrderItemModel orderItem : orderItemList) {
				if (!orderItemNos.contains(orderItem.getOrderNo())) {
					tempList.add(orderItem);
				}
			}
			return tempList;
		}
		return orderItemList;
	}

	/**
	 * 更新订单
	 */
	@Override
	public void updateOrder(OrderModel orderModel) {
		orderMapper.updateOrder(orderModel);
	}

	/**
	 * 批量更新订单
	 */
	@Override
	public void updateBatchOrder(List<OrderModel> orderList) {
		orderMapper.updateBatchOrder(orderList);
	}

	/**
	 * 订单批量保存
	 */
	@Override
	// public void saveBatchOrder(List<OrderModel> orderList,String orderNo,long order_item_id){
	public void saveBatchOrder(List<OrderModel> orderList) {
		OrderModel temOrderModel = null;
		int j = 0, i = 0;
		try {
			// this.employee666Mapper.insertSelective666(employee1);
			for (i = 0; i < orderList.size(); i++) {
				temOrderModel = orderList.get(i);
				j = i;
				// this.employee666Mapper.insertSelective666(temOrderModel);

				int bool = orderMapper.isOrderByNo(temOrderModel.getOrderNo());// 0 不存在，1 存在
				if (bool == 0) {
					orderMapper.saveOrder(temOrderModel);
				}

				orderList.remove(j);
				i = 0;
			}
		} catch (Exception e) {
			logger.error(temOrderModel + "保存时出错" + " ,错误信息：" + e.getMessage(), e);
			// 保存记录到数据库
			String orderNo = orderList.get(j).getOrderNo();
//			JSONObject orderModel_JSONObject = JSONObject.fromObject(orderList.get(j));

			ExceptionInfo exceptionInfo = new ExceptionInfo();
			exceptionInfo.setOrder_no(orderNo);
			// exceptionInfo.setOrder_item_id(110);
//			exceptionInfo.setContent(orderModel_JSONObject.toString());
//			exceptionInfo.setExceptionInfos(e.getMessage());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			exceptionInfo.setAdd_time(sdf.format(new Date()));
			exceptionInfoMapper.insertExceptionInfo(exceptionInfo);
			orderList.remove(j);
			i = 0;
		} finally {
			if (orderList.size() == 0) {
				System.out.println("saveBatchOrder保存:执行完毕");
			} else {
				saveBatchOrder(orderList);
			}
		}
	}

	/**
	 * 订单明细批量保存
	 */
	public void saveBatchOrderItem(List<OrderItemModel> orderItemList) {

		OrderItemModel temOrderItemModel = null;
		int j = 0, i = 0;
		try {
			for (i = 0; i < orderItemList.size(); i++) {
				temOrderItemModel = orderItemList.get(i);
				j = i;

				int bool = orderMapper.isOrderItemByNoANDGoodsNo(temOrderItemModel.getOrderNo(), temOrderItemModel.getGoodsNo());// 0 不存在，1 存在
				// int bool=orderMapper.isOrderItemByNo(temOrderItemModel.getOrderNo());//0 不存在，1 存在
				if (bool == 0) {
					orderMapper.saveOrderItem(temOrderItemModel);
				}

				orderItemList.remove(j);
				i = 0;
			}
		} catch (Exception e) {
			logger.error(temOrderItemModel + "保存时出错" + " ,错误信息：" + e.getMessage(), e);
			// 保存记录到数据库
			String orderNo = orderItemList.get(j).getOrderNo();
//			JSONObject orderItemModel_JSONObject = JSONObject.fromObject(orderItemList.get(j));

			ExceptionInfo exceptionInfo = new ExceptionInfo();
			exceptionInfo.setOrder_no(orderNo);
			// exceptionInfo.setOrder_item_id(110);
//			exceptionInfo.setContent(orderItemModel_JSONObject.toString());
//			exceptionInfo.setExceptionInfos(e.getMessage());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			exceptionInfo.setAdd_time(sdf.format(new Date()));
			exceptionInfoMapper.insertExceptionInfo(exceptionInfo);
			orderItemList.remove(j);
			i = 0;
		} finally {
			num++;
			if (orderItemList.size() == 0) {
				System.out.println("saveBatchOrderItem保存:执行完毕");
			} else {
				saveBatchOrderItem(orderItemList);
			}
		}
	}

	// 作为一个通用的方法：插入集合时，遇到异常记录保存下来，同时保证剩余记录的顺利插入
	@Override
	public void savePaymentLogModelList(List<PaymentLogModel> list) {
		PaymentLogModel paymentLogModel = null;
		int j = 0, i = 0;
		try {
			for (i = 0; i < list.size(); i++) {
				paymentLogModel = list.get(i);
				j = i;

				int bl = payLogMapper.isLogByOrderNo(paymentLogModel.getBusinessId());// 0 不存在，1 存在
				if (bl == 0) {
					payLogMapper.savePaymentLog(paymentLogModel);
				}
				// this.employee666Mapper.insertSelective666((Employee) temEmployee);//DAO层保存记录的方法

				list.remove(j);
				i = 0;
			}
		} catch (Exception e) {
			logger.error(paymentLogModel + "保存时出错" + " ,错误信息：" + e.getMessage(), e);
			// 保存记录到数据库
			// Integer orderNo=list.get(j).getEmpid();
//			JSONObject orderModel_JSONObject = JSONObject.fromObject(list.get(j));

			ExceptionInfo exceptionInfo = new ExceptionInfo();
			// exceptionInfo.setOrder_no(orderNo.toString());
			// exceptionInfo.setOrder_item_id(110);
//			exceptionInfo.setContent(orderModel_JSONObject.toString());
//			exceptionInfo.setExceptionInfos(e.getMessage());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			exceptionInfo.setAdd_time(sdf.format(new Date()));
			exceptionInfoMapper.insertExceptionInfo(exceptionInfo);
			list.remove(j);
			i = 0;
		} finally {
			if (list.size() == 0) {
				System.out.println("savePaymentLogModelList保存:执行完毕");
			} else {
				savePaymentLogModelList(list);
			}
		}
	}

	@Override
	public List<OrderItemModel> queryOrderItems(String orderNo) {

		List<OrderItemModel> itemList = orderMapper.queryOrderItems(orderNo);
		return itemList;
	}

	@Override
	public int pageQueryOrderListCount(Map<String, Object> params) {
		int totalCount = orderMapper.pageQueryOrderListCount(params);
		return totalCount;
	}

	@Override
	public List<OrderModel> pageQueryOrderList(Map<String, Object> params) {
		List<OrderModel> orderList = orderMapper.pageQueryOrderList(params);
		return orderList;
	}

	// 作为一个通用的方法：插入集合时，遇到异常记录保存下来，同时保证剩余记录的顺利插入
	@Override
	public void updateBatchOrder666(List<OrderModel> list) {
		OrderModel orderModel = null;
		int j = 0, i = 0;
		try {
			for (i = 0; i < list.size(); i++) {
				orderModel = list.get(i);
				j = i;

				orderMapper.updateOrder(orderModel);
				// logger.info("订单保存数据库错误 ，错误订单号："+orderModel.getOrderNo() +"   "+orderModel.getEditTime());
				list.remove(j);
				i = 0;
			}
		} catch (Exception e) {
			logger.error(orderModel + "保存时出错" + " ,错误信息：" + e.getMessage(), e);
			// 保存记录到数据库

			ExceptionInfo exceptionInfo = new ExceptionInfo();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			exceptionInfo.setAdd_time(sdf.format(new Date()));
			exceptionInfoMapper.insertExceptionInfo(exceptionInfo);
			list.remove(j);
			i = 0;
		} finally {
			if (list.size() == 0) {
				System.out.println("updateBatchOrder666更新:执行完毕");
			} else {
				updateBatchOrder666(list);
			}
		}
	}

	// 作为一个通用的方法：插入集合时，遇到异常记录保存下来，同时保证剩余记录的顺利插入
	@Override
	public void saveTrade(OrderModel orderModel, List<OrderItemModel> orderItemModelList, List<PaymentLogModel> paymentLogList, LogisticChannelModel logisticChannelModel) {
		
		String orderNo = orderModel.getOrderNo();
		int num = 0;
		if("4".equals(orderModel.getMultiChannelId()+"") || "5".equals(orderModel.getMultiChannelId())){
			num = orderMapper.isOrderByMulti_channel_order_no(orderModel.getMultiChannelOrderNo());
		}else{
			num = orderMapper.isOrderByNo(orderNo);
		}
		if (num==0) {
//			str="订单主表--没有--该订单号----";
			
			int flag = 0;
			flag = orderMapper.saveOrder_TM(orderModel);
			if (flag==1) {
				for (int i = 0; i < orderItemModelList.size(); i++) {
					OrderItemModel tempOrderItemModel = orderItemModelList.get(i);
					orderMapper.saveOrderItem(tempOrderItemModel);
				}
				for (int i = 0; i < paymentLogList.size(); i++) {
					PaymentLogModel paymentLogModel = paymentLogList.get(i);
					payLogMapper.savePaymentLog(paymentLogModel);
				}
				if (logisticChannelModel != null) {
					logisticMapper.saveLogisticChannel(logisticChannelModel);
				}
				System.out.println("订单不存在,执行保存成功...订单号:"+orderModel.getOrderNo() + " ----------------------------"); 
			}
		}else{
			//
			String tid_temp=null;
			List<OrderModel>  orderModelList= orderMapper.selectTidByOrderNO(orderNo);
			if (orderModelList!=null && orderModelList.size()>0 && (orderModelList.get(0)!=null)) {
				tid_temp=orderModelList.get(0).getTid();
			}
			if (StringUtil.isBlank(tid_temp)) {
//				str="订单主表--只更新--tid和get_time-----";
				OrderModel orderModel100=new OrderModel();
				orderModel100.setOrderNo(orderNo);
				orderModel100.setTid(orderModel.getTid());
				orderModel100.setGet_time(orderModel.getGet_time());
				orderMapper.updateTid(orderModel100);
			} else {
//				str="订单主表--已有--该订单号----";
				//----------------开始-----------------------------------------
				String orderNo_temp222="R"+orderModel.getOrderNo();
				int isOrderByNo_R=orderMapper.isOrderByNo("R"+orderModel.getOrderNo());
				int isOrderByNo_R2=orderMapper.isOrderByNo("R2"+orderModel.getOrderNo());
				
				if (isOrderByNo_R!=0) {
					orderNo_temp222="R2"+orderModel.getOrderNo();
				}
				if (isOrderByNo_R2!=0) {
					orderNo_temp222="R3"+orderModel.getOrderNo();
				}
				//-----------------结束----------------------------------------
				orderModel.setOrderNo(orderNo_temp222);
				int flag = 0;
				flag = orderMapper.saveOrder_TM(orderModel);
				if (flag==1) {
					for (int i = 0; i < orderItemModelList.size(); i++) {
						OrderItemModel tempOrderItemModel22 = orderItemModelList.get(i);
						tempOrderItemModel22.setOrderNo(orderNo_temp222);
						orderMapper.saveOrderItem(tempOrderItemModel22);
					}
					for (int i = 0; i < paymentLogList.size(); i++) {
						PaymentLogModel paymentLogModel22 = paymentLogList.get(i);
						paymentLogModel22.setBusinessId(orderNo_temp222);
						payLogMapper.savePaymentLog(paymentLogModel22);
					}
					
					System.out.println("订单不存在,执行保存成功...订单号:"+orderModel.getOrderNo() + " ----------------------------"); 
				}
			}	
		}
		
		if(orderItemModelList!=null && orderItemModelList.size()>0){
			for(OrderItemModel item : orderItemModelList){
				if(item.getGoodsListType().equals(Constant.GOODS_TYPE_GROUP)){
					List<OrderItemModel> itemlist = supplementService.tmData(orderItemModelList, item);
					orderService.updateBatchOrderItem(itemlist);
				}
			}
		}
	}

	@Override
	public void updateOrderItem(OrderItemModel itemModel) {
		orderMapper.updateOrderItem(itemModel);
	}

	@Override
	public void updateBatchOrderItem(List<OrderItemModel> itemList) {
		if (null != itemList && itemList.size() > 0) {
			orderMapper.updateBatchOrderItem(itemList);
		}
	}

	@Override
	public List<String> batchQueryOrder(List<OrderModel> orderNos) {

		List<String> list = orderMapper.batchQueryOrder(orderNos);
		return list;
	}

	@Override
	public List<String> batchQueryOrderItem(List<OrderItemModel> orderNos) {
		List<String> list = orderMapper.batchQueryOrderItem(orderNos);
		return list;
	}
	
	
	@Override
	public List<OrderModel> queryOrders(List<String> orderNos){
		
		List<OrderModel> orders = orderMapper.queryOrders(orderNos);
		
		return orders;
	}

	/**
	 * 插入每天统计的订单
	 */
	@SuppressWarnings("static-access")
	@Override
	public void createOrderStatistic() {
		try {
			//查询最大的时间,以防止漏掉
			Object maxDay  = orderMapper.queryMaxDay();
			if(null != maxDay && !"".equals(maxDay)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date oldDate = sdf.parse(maxDay.toString());
				int days = new OrderServiceImpl().dateInterval(oldDate.getTime(),new Date().getTime());
				//相差一天,比如8月18号执行8月17号的统计,则maxDay为8月16号,计算得出相差1
				if(1 <= days){
					Calendar   calendar   = Calendar.getInstance(); 
					for (int j = 0; j < days; j++) {
					     calendar.setTime(oldDate); 
					     calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
					     oldDate=calendar.getTime(); 
					     String tmp =  sdf.format(oldDate);
					     if(!tmp.equals(sdf.format(new Date()))){  
					    	 Map<String, Object> map = new HashMap<String, Object>(); 
					    	 map.put("date", tmp);
					    	 orderMapper.createOrderStatistic(map);
					    	 System.out.println("当前时间:"+sdf.format(new Date())+"-统计订单时间 "+tmp+"的数据成功");
					     }
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
 
	/**  
	 * 计算出两个日期之间相差的天数  
	 * 建议date1 大于 date2 这样计算的值为正数  
	 * @param date1 日期1  
	 * @param date2 日期2  
	 * @return date1 - date2  
	 */  
	private int dateInterval(long date1, long date2) {
	    if(date2 > date1){ 
	        date2 = date2 + date1; 
	        date1 = date2 - date1; 
	        date2 = date2 - date1; 
	    } 
	    Calendar calendar1 = Calendar.getInstance(); // 获得一个日历   
	    calendar1.setTimeInMillis(date1); // 用给定的 long 值设置此 Calendar 的当前时间值。   
	    Calendar calendar2 = Calendar.getInstance(); 
	    calendar2.setTimeInMillis(date2); 
	    // 先判断是否同年   
	    int y1 = calendar1.get(Calendar.YEAR); 
	    int y2 = calendar2.get(Calendar.YEAR); 
	        
	    int d1 = calendar1.get(Calendar.DAY_OF_YEAR); 
	    int d2 = calendar2.get(Calendar.DAY_OF_YEAR); 
	    int maxDays = 0; 
	    int day = 0; 
	    if(y1 - y2 > 0){ 
	        day = numerical(maxDays, d1, d2, y1, y2, calendar2); 
	    }else{ 
	        day = d1 - d2; 
	    } 
	    return day; 
	}

 
	@Override
	public void updateOrderItemByOrderNo(OrderItemModel itemModel){
		orderItemMapper.updateOrderItemByOrderNo(itemModel);
	}
	
	@Override
	public void saveTrade_360(OrderModel orderModel, List<OrderItemModel> orderItemModelList, List<PaymentLogModel> paymentLogList, LogisticChannelModel logisticChannelModel) {
		String orderNo = orderModel.getOrderNo();
		int num = orderMapper.isOrderByNo(orderNo);
		if (num==0) {
	//		String orderNo = orderModel.getOrderNo();
			orderMapper.saveOrder(orderModel);
			for (int i = 0; i < orderItemModelList.size(); i++) {
				OrderItemModel tempOrderItemModel = orderItemModelList.get(i);
				orderMapper.saveOrderItem(tempOrderItemModel);
			}
			for (int i = 0; i < paymentLogList.size(); i++) {
				PaymentLogModel paymentLogModel = paymentLogList.get(i);
				payLogMapper.savePaymentLog(paymentLogModel);
			}
			if (logisticChannelModel != null) {
				logisticMapper.saveLogisticChannel(logisticChannelModel);
			}
	//		System.out.println("saveTrade:360成功执行完毕");
		}
	}
 
	private int numerical(int maxDays, int d1, int d2, int y1, int y2, Calendar calendar){ 
	    int day = d1 - d2; 
	    int betweenYears = y1 - y2; 
	    List<Integer> d366 = new ArrayList<Integer>(); 
	    if(calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366){ 
	        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR)); 
	        day += 1; 
	    } 
	    for (int i = 0; i < betweenYears; i++) { 
	        // 当年 + 1 设置下一年中有多少天   
	        calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR)) + 1); 
	        maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR); 
	        // 第一个 366 天不用 + 1 将所有366记录，先不进行加入然后再少加一个  
	        if(maxDays != 366){ 
	            day += maxDays; 
	        }else{ 
	            d366.add(maxDays); 
	        } 
	        // 如果最后一个 maxDays 等于366 day - 1   
	        if(i == betweenYears-1 && betweenYears > 1 && maxDays == 366){ 
	            day -= 1; 
	        } 
	    } 
	    for(int i = 0; i < d366.size(); i++){ 
	        // 一个或一个以上的366天   
	        if(d366.size() >= 1){ 
	            day += d366.get(i); 
	        }  
	    }   
	    return day; 
	}

	/**
	 * 客服修改69码接口
	 * @param orderNo
	 * @param old_goods_69
	 * @param new_goods_69
	 * @param orderNotes
	 * @param amount
	 * @return
	 * @author hhr
	 */
	@Override
	public boolean updateOrder69ByOrderNo(String orderNo, String old_goods_69,String new_goods_69,String  orderNotes, String amount,Integer addUserId) {
		try {
			//(1)先复制order_info表的数据		
			 OrderModel order_model = orderMapper.findOrderByNo(orderNo);
			 if(null != order_model && !"7".equals(order_model.getOrderStatus()+"")){
				 //如果不存在新的69商品,直接返回false
				 GoodsModel newGoods = goodsMapper.getGoodByGoodNo69(new_goods_69);
				 if(null == newGoods){
					 return false;
				 }
				//(2)复制原来的order_info,order_status 变为2,send_status变为N,更换订单号,将订单号PA替换为TH,order_notes替换更换原因
				 String newOrderNo = "";
				 if(!"".equals(orderNo) && (orderNo.indexOf("TH") > -1 || orderNo.indexOf("BF") > -1 )){
					   newOrderNo = "TH"+orderNo;
				 }else{
					   newOrderNo = "TH"+orderNo.substring(2,orderNo.length());
				 }
				 order_model.setOrderStatus(OrderState.STATE_2.getCode());
				 order_model.setSendStatus("N");
				 order_model.setOrderNotes(orderNotes);
				 order_model.setOrderNo(newOrderNo);
				 if(!"".equals(addUserId) && null != addUserId){
					 order_model.setAddUserId(addUserId);
				 }else{
					 addUserId = 0;
				 }
				 order_model.setMultiChannelOrderNo(newOrderNo);
				 orderMapper.saveOrder(order_model);
				 
				//(3)将原来的订单作废
//				 orderMapper.updateOrderCancleStatus(orderNo);
				 orderService.orderCannel(orderNo, addUserId, orderNotes);
				//(4)处理order_item 
				 List<OrderItemModel> itemList = orderMapper.queryOrderItems(orderNo);
	//			 GoodsModel oldGoods = goodsMapper.getGoodByGoodNo69(old_order_69);
				
				 String newGoodsNo = newGoods.getGoodsNo();
				 String newGoodsName = newGoods.getName();
				 String newGoodsStandard = newGoods.getStandard();
				 
				//(5)查询出明细,复制出来,然后将`order_item_id`全部置为null,并且更换order_no,然后更换新的 69码,条码,名称,规格.最后更换数量
				 List<OrderItemModel> orderItemList =  new ArrayList<OrderItemModel>();
				 for (int i = 0; i < itemList.size(); i++) {
					 OrderItemModel newItem = itemList.get(i);
	//				 newItem.setOrderItemId(0); 
					 newItem.setOrderNo(newOrderNo);
					 //注意,这里是单独修改的单品字段
					 if(old_goods_69.trim().equals(newItem.getGoodsNo_69().trim())){
						 newItem.setGoodsNo_69(new_goods_69);
						 newItem.setGoodsNo(newGoodsNo);
						 newItem.setName(newGoodsName);
						 newItem.setStandard(newGoodsStandard);
						 //修改数量(需要的时候打开注释)
						 if(null != amount && !"".equals(amount.trim()) && Integer.valueOf(amount.trim()) > 0 ){
							 newItem.setAmount(Long.valueOf(amount));
						 }
						 try {
							 newItem.setSupplyPrice(updateSupplyPriceMeth(new_goods_69, order_model.getMultiChannelId()));
							 newItem.setCostPrice(newGoods.getCostPriceAverage());
//							 newItemModel.setOldPrice(new BigDecimal("0"));
//							 newItemModel.setPriceDis(new BigDecimal("0"));
//							 newItemModel.setGoodsSumFee(new BigDecimal("0"));
							 if(null != amount && !"".equals(amount)){
								 newItem.setPrice(MathUtil.div(newItem.getGoodsSumFee(),new BigDecimal(amount),4));
							 }else{
								 newItem.setPrice(newItem.getGoodsSumFee()); 
							 }
						} catch (Exception e) {
							 System.out.println("由TM码替换成单品"); 
						}
					 }
					 orderItemList.add(newItem);
				}
				 if(null != orderItemList && orderItemList.size() > 0){
					 orderMapper.saveBatchOrderItem(orderItemList);
					 savePayLog(newOrderNo,orderNo);
					 return true;
				 }else{
					System.out.println("订单没有明细 :  orderNo = "+ orderNo + " ,  old_goods_69 = "+old_goods_69 + " ,new_goods_69 = "+ new_goods_69 +" , orderNotes = "+ orderNotes );
				 }
				 
			 }else{
				 if(null == order_model){
					 System.out.println("在205库中未找到订单orderNo = "+ orderNo + " ,  old_goods_69 = "+old_goods_69 + " ,new_goods_69 = "+ new_goods_69 +" , orderNotes = "+ orderNotes );
				 } 
			 }
		} catch (Exception e) {
			 logger.error("失败了....orderNo = "+ orderNo + " ,  old_goods_69 = "+old_goods_69 + " ,new_goods_69 = "+ new_goods_69 +" , orderNotes = "+ orderNotes );
			 e.printStackTrace();
		}
		return false;
		
	}

	/**
	 * 客服由TM码替换成单品
	 */
	@Override
	public boolean updateOrderTmTo69(String orderNo, String oldTmNo,String new_goods_69, String orderNotes, String amount, Integer addUserId) {
		try {
		//(1)先复制order_info表的数据		
		 OrderModel order_model = orderMapper.findOrderByNo(orderNo);
		 if(null != order_model && !"7".equals(order_model.getOrderStatus()+"")){
			 //如果不存在新的69商品,直接返回false
			 GoodsModel newGoods = goodsMapper.getGoodByGoodNo69(new_goods_69);
			 if(null == newGoods){
				 return false;
			 }
			//(2)复制原来的order_info,order_status 变为2,send_status变为N,更换订单号,将订单号PA替换为TH,order_notes替换更换原因
			 String newOrderNo = "";
			 if(!"".equals(orderNo) && (orderNo.indexOf("TH") > -1 || orderNo.indexOf("BF") > -1 )){
				   newOrderNo = "TH"+orderNo;
			 }else{
				   newOrderNo = "TH"+orderNo.substring(2,orderNo.length());
			 }
			 order_model.setOrderStatus(OrderState.STATE_2.getCode());
			 order_model.setSendStatus("N");
			 order_model.setOrderNotes(orderNotes);
			 order_model.setOrderNo(newOrderNo);
			 if(!"".equals(addUserId) && null != addUserId){
				 order_model.setAddUserId(addUserId);
			 }else{
				 addUserId = 0;
			 }
			 order_model.setMultiChannelOrderNo(newOrderNo);
			 orderMapper.saveOrder(order_model);
			 
			//(3)将原来的订单作废
//			 orderMapper.updateOrderCancleStatus(orderNo);
			 orderService.orderCannel(orderNo, addUserId, orderNotes);
			//(4)处理order_item 
			 List<OrderItemModel> itemList = orderMapper.queryOrderItems(orderNo);
			 if(null == itemList || itemList.size() <= 0 ){
				 logger.error("订单不存在明细 orderNo = " + orderNo);
				 return false;
			 }
//			 GoodsModel oldGoods = goodsMapper.getGoodByGoodNo69(old_order_69);
			
			 String newGoodsNo = newGoods.getGoodsNo();
			 String newGoodsName = newGoods.getName();
			 String newGoodsStandard = newGoods.getStandard();
			 
			//(5)查询出明细,复制出来,然后将`order_item_id`全部置为null,并且更换order_no,然后更换新的 69码,条码,名称,规格.最后更换数量
			 List<OrderItemModel> orderItemList =  new ArrayList<OrderItemModel>();
			 OrderItemModel newItemModel = new OrderItemModel();
			 for (int i = 0; i < itemList.size(); i++) {
				 OrderItemModel newItem = itemList.get(i);
				 
				 //新替换的69码model ******************开始
				 if(oldTmNo.equals(newItem.getGoodsNo_69().trim()) ){
					 newItemModel = newItem.clone();
					 newItemModel.setOrderNo(newOrderNo);
					 newItemModel.setGoodsListType("GENERAL");
					 newItemModel.setGoodsType(newGoods.getGoodsType());
					 newItemModel.setGoodsNo_69(new_goods_69);
					 newItemModel.setGoodsNo(newGoodsNo);
					 newItemModel.setName(newGoodsName);
					 newItemModel.setGoodsType("OTHER");
					 newItemModel.setStandard(newGoodsStandard);
					 newItemModel.setGoodsStatus(5);
					 if(null != amount && !"".equals(amount.trim()) && Integer.valueOf(amount.trim()) > 0 ){
						  newItemModel.setAmount(Long.valueOf(amount));
					 }
					 try {
						 newItemModel.setSupplyPrice(updateSupplyPriceMeth(new_goods_69, order_model.getMultiChannelId()));
						 newItemModel.setCostPrice(newGoods.getCostPriceAverage());
//						 newItemModel.setOldPrice(new BigDecimal("0"));
//						 newItemModel.setPriceDis(new BigDecimal("0"));
//						 newItemModel.setGoodsSumFee(new BigDecimal("0"));
						 if(null != amount && !"".equals(amount)){
							 newItemModel.setPrice(MathUtil.div(newItemModel.getGoodsSumFee(),new BigDecimal(amount),4));
						 }else{
							 newItemModel.setPrice(newItem.getGoodsSumFee()); 
						 }
						 
					} catch (Exception e) {
						 System.out.println("由TM码替换成单品"); 
					}
					 
					 orderItemList.add(newItemModel);
				 }
				//***********************************结束
				 
//				 newItem.setOrderItemId(0); 
				 newItem.setOrderNo(newOrderNo);
				 //注意,这里是单独修改的单品字段
				 if(oldTmNo.equals(newItem.getGoodsNo_69().trim()) || oldTmNo.equals(newItem.getTmSource().trim()) ){
//					 newItem.setGoodsNo_69(new_goods_69);
//					 newItem.setGoodsNo(newGoodsNo);
//					 newItem.setName(newGoodsName);
//					 newItem.setStandard(newGoodsStandard);
//					 //修改数量(需要的时候打开注释)
//					 if(null != amount && !"".equals(amount.trim()) && Integer.valueOf(amount.trim()) > 0 ){
//						 newItem.setAmount(Long.valueOf(amount));
//					 }
					 System.out.println("是tm组合中的数据,不处理 newItem = " + JSON.toJSONString(newItem)); 
					 
				 }else{
					 orderItemList.add(newItem);
				 }
			}
			 if(null != orderItemList && orderItemList.size() > 0){
				 orderMapper.saveBatchOrderItem(orderItemList);
				 savePayLog(newOrderNo,orderNo);
				 return true;
			 }else{
				System.out.println("订单没有明细 :  orderNo = "+ orderNo + " ,  oldTmNo = "+oldTmNo + " ,new_goods_69 = "+ new_goods_69 +" , orderNotes = "+ orderNotes );
			 }
			 
		 }else{
			 if(null == order_model){
				 System.out.println("在205库中未找到订单orderNo = "+ orderNo + " ,  oldTmNo = "+oldTmNo + " ,new_goods_69 = "+ new_goods_69 +" , orderNotes = "+ orderNotes );
			 } 
		 }
		} catch (Exception e) {
		 logger.error("失败了....orderNo = "+ orderNo + " ,  oldTmNo = "+oldTmNo + " ,new_goods_69 = "+ new_goods_69 +" , orderNotes = "+ orderNotes );
		 e.printStackTrace();
	}
	return false;
	}

	
	//客服由单品替换TM码
	@Override
	public boolean excSingleToTM(String orderNo, String oldGoods69,String newOrderTM, String orderNotes, String amount, Integer addUserId,String orderNoHead) {
		try {
		//(1)先复制order_info表的数据		
		 OrderModel order_model = orderMapper.findOrderByNo(orderNo);
		 if(null != order_model && !"7".equals(order_model.getOrderStatus()+"")){
			//(2)复制原来的order_info,order_status 变为2,send_status变为N,更换订单号,将订单号PA替换为TH,order_notes替换更换原因
			 String newOrderNo = "";
			 if(!"".equals(orderNo) && (orderNo.indexOf("TH") > -1 || orderNo.indexOf("BF") > -1 )){
				   newOrderNo = "TH"+orderNo;
			 }else{
				   newOrderNo = "TH"+orderNo.substring(2,orderNo.length());
			 }
			 order_model.setOrderStatus(OrderState.STATE_2.getCode());
			 order_model.setSendStatus("N");
			 order_model.setOrderNotes(orderNotes);
			 order_model.setOrderNo(newOrderNo);
			 if(!"".equals(addUserId) && null != addUserId){
				 order_model.setAddUserId(addUserId);
			 }else{
				 addUserId = 0;
			 }
			 order_model.setMultiChannelOrderNo(newOrderNo);
			 orderMapper.saveOrder(order_model);
			 
			//(3)将原来的订单作废
//			 orderMapper.updateOrderCancleStatus(orderNo);
			 orderService.orderCannel(orderNo, addUserId, orderNotes);
			 
			 //(4)处理order_item 
			 List<OrderItemModel> itemList = orderMapper.queryOrderItems(orderNo);
			 if(null == itemList || itemList.size() <= 0 ){
				 logger.error("订单不存在明细 orderNo = " + orderNo);
				 return false;
			 }
			 
			//(5)查询出明细,复制出来,然后将`order_item_id`全部置为null,并且更换order_no,然后更换新的 69码,条码,名称,规格.最后更换数量
			 List<OrderItemModel> orderItemList =  new ArrayList<OrderItemModel>();
			 for (int i = 0; i < itemList.size(); i++) {
				 
				 OrderItemModel newItem = itemList.get(i);
				 
				 newItem.setOrderNo(newOrderNo);
				 //注意,这里是单独修改的单品字段
				 if(oldGoods69.equals(newItem.getGoodsNo_69().trim()) || (null != newItem.getTmSource() && oldGoods69.equals(newItem.getTmSource().trim())) ){
					 //真实的替换功能
					 newItem.setGoodsNo_69(newOrderTM);
					 newItem.setTmSource(null);
					 newItem.setGoodsNo(null);
					 List<GoodsGroupItemModel> groupItemList = goodsMapper.queryGroupItemByNo69(newOrderTM);
					 if(groupItemList!=null&&groupItemList.size()>0){
						 newItem.setName(groupItemList.get(0).getGoodsName());
						 newItem.setSupplyPrice(groupItemList.get(0).getSupplyPrice());
					 }
					 newItem.setStandard(null);
					 newItem.setGoodsListType("GROUP");
					 newItem.setGroupStatus(null);
//					 //修改数量(需要的时候打开注释)
					 if(null != amount && !"".equals(amount.trim()) && Integer.valueOf(amount.trim()) > 0 ){
						 newItem.setAmount(Long.valueOf(amount));
					 }else{
						 newItem.setAmount(1L);
					 }
				 } 
				orderItemList.add(newItem);
				  
			}
			 if(null != orderItemList && orderItemList.size() > 0){
				 orderMapper.saveBatchOrderItem(orderItemList);
				 //进行拆分
				 supplementService.orderDataUpdate(order_model);
				 supplementService.dealSupplement(order_model);
				 savePayLog(newOrderNo,orderNo);
				 return true;
			 }else{
				System.out.println("订单没有明细 :  orderNo = "+ orderNo + " ,  newOrderTM = "+newOrderTM + " ,oldGoods69 = "+ oldGoods69 +" , orderNotes = "+ orderNotes );
			 }
		 }else{
			 if(null == order_model){
				 System.out.println("在205库中未找到订单orderNo = "+ orderNo + " ,  newOrderTM = "+newOrderTM + " ,oldGoods69 = "+ oldGoods69 +" , orderNotes = "+ orderNotes );
			 } 
		 }
		} catch (Exception e) {
		 logger.error("失败了...");
		 e.printStackTrace();
	}
	return false;
	}

	//运营由TM替换TM码
	@Override
	public boolean excTMToTM(String orderNo, String oldOrderTM, String newOrderTM, String orderNotes, String amount, Integer addUserId) {
		try {
			 //(1)先复制order_info表的数据		
			 OrderModel order_model = orderMapper.findOrderByNo(orderNo);
			 if(null != order_model && !"7".equals(order_model.getOrderStatus()+"")){
				//(2)复制原来的order_info,order_status 变为2,send_status变为N,更换订单号,将订单号PA替换为TH,order_notes替换更换原因
				 String newOrderNo = "";
				 if(!"".equals(orderNo) && (orderNo.indexOf("TH") > -1 || orderNo.indexOf("BF") > -1 )){
					   newOrderNo = "TH"+orderNo;
				 }else{
					   newOrderNo = "TH"+orderNo.substring(2,orderNo.length());
				 }
				 order_model.setOrderStatus(OrderState.STATE_2.getCode());
				 order_model.setSendStatus("N");
				 order_model.setOrderNotes(orderNotes);
				 order_model.setOrderNo(newOrderNo);
				 if(!"".equals(addUserId) && null != addUserId){
					 order_model.setAddUserId(addUserId);
				 }else{
					 addUserId = 0;
				 }
				 order_model.setMultiChannelOrderNo(newOrderNo);
				 orderMapper.saveOrder(order_model);
				 
				//(3)将原来的订单作废
//				 orderMapper.updateOrderCancleStatus(orderNo);
				 orderService.orderCannel(orderNo, addUserId, orderNotes);
				//(4)处理order_item 
				 List<OrderItemModel> itemList = orderMapper.queryOrderItems(orderNo);
				 if(null == itemList || itemList.size() <= 0 ){
					 logger.error("订单不存在明细 orderNo = " + orderNo);
					 return false;
				 }
				 
				//(5)查询出明细,复制出来,然后将`order_item_id`全部置为null,并且更换order_no,然后更换新的 69码,条码,名称,规格.最后更换数量
				 List<OrderItemModel> orderItemList =  new ArrayList<OrderItemModel>();
				 for (int i = 0; i < itemList.size(); i++) {
					 
					 OrderItemModel newItem = itemList.get(i);
					 newItem.setOrderNo(newOrderNo);
					 
					 //进行一次TM拼接,方便替换
					 OrderItemModel newItemModel = new OrderItemModel();
					 if(i == 0){
						 newItemModel = newItem.clone();
						 //真实的替换功能
						 newItemModel.setGoodsNo_69(newOrderTM);
						 newItemModel.setTmSource(null);
						 newItemModel.setGoodsNo(null);
						 List<GoodsGroupItemModel> groupItemList = goodsMapper.queryGroupItemByNo69(newOrderTM);
						 if(groupItemList!=null&&groupItemList.size()>0){
							 newItemModel.setName(groupItemList.get(0).getGoodsName());
							 newItemModel.setSupplyPrice(groupItemList.get(0).getSupplyPrice());
						 }
						 newItemModel.setStandard(null);
						 newItemModel.setGoodsListType("GROUP");
						 newItemModel.setGroupStatus(null);
						 //修改数量(需要的时候打开注释)
						 if(null != amount && !"".equals(amount.trim()) && Integer.valueOf(amount.trim()) > 0 ){
							 newItemModel.setAmount(Long.valueOf(amount));
						 }else{
							 newItemModel.setAmount(1L);
						 }
						 orderItemList.add(newItemModel);
					 }
					 //注意,这里 ,旧TM不做处理
					 if(oldOrderTM.equals(newItem.getGoodsNo_69().trim()) || (null != newItem.getTmSource() && oldOrderTM.equals(newItem.getTmSource().trim())) ){
						 System.out.println("旧TM,不做任何处理");
					 }else{
						 //做处理
						 orderItemList.add(newItem);
					 } 
				}
				 if(null != orderItemList && orderItemList.size() > 0){
					 orderMapper.saveBatchOrderItem(orderItemList);
					 //进行拆分
					 supplementService.orderDataUpdate(order_model);
					 supplementService.dealSupplement(order_model);
					 savePayLog(newOrderNo,orderNo);
					 return true;
				 }else{
					System.out.println("订单没有明细 :  orderNo = "+ orderNo + " ,  newOrderTM = "+newOrderTM + " , orderNotes = "+ orderNotes );
				 }
			 }else{
				 if(null == order_model){
					 System.out.println("在205库中未找到订单orderNo = "+ orderNo + " ,  newOrderTM = "+newOrderTM +  " , orderNotes = "+ orderNotes );
				 } 
			 }
		} catch (Exception e) {
			logger.error("失败了...");
			e.printStackTrace();
		}
		return false;
	
	}


	@Override
	public String orderCannel(String orderNo,int userId,String notes) {
		try {
			if(null == orderNo || "".equals(orderNo)){
				return "订单号为必填项!";
			}
			//扣减库存
			OrderModel orderModel = this.findOrderByNo(orderNo);
			if(orderModel!=null){
				//作废订单开始
				Map<String,String> map =  edbTradeGet(orderModel.getMultiChannelOrderNo()); 
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
							logger.info("订单作废失败");
							return msg;
						}
					}
				}
				if(orderModel.getOrderStatus()==OrderState.STATE_7.getCode()){
					return "订单已经作废,无需再次作废";
				}
				if(orderModel.getOrderStatus()!=OrderState.STATE_3.getCode()){
					List<OrderItemModel> itemList = this.queryOrderItems(orderNo);
					if(itemList!=null&&itemList.size()>0){
						for(OrderItemModel itemModel : itemList){
							if(itemModel.getGoodsListType().equalsIgnoreCase(Constant.GOODS_TYPE_GENERAL)){
								int amount = itemModel.getAmount().intValue();
								//库存还原
								goodsService.updateGoodsStock(itemModel.getGoodsNo(),amount, -amount, 0);
							}
						}
					}
				}
				//更改状态
				orderModel.setOrderStatus(OrderState.STATE_7.getCode());
				orderModel.setCancelUserId(userId);
				orderModel.setCancelNotes(notes);
				orderModel.setCancelTime(DateUtil.DateTimeToStr(new Date()));
				this.updateOrder(orderModel);
			}
			
			return "操作成功";
		} catch (Exception e) {
			logger.error("取消订单失败，失败原因:" + e.getMessage(),e);
		}
		return "作废失败了.....";
	} 
	

	private void savePayLog(String newOrderNo,String oldOrderNo){
		List<PaymentLogModel> payList = paymentLogService.queryLogsByOrderNo(oldOrderNo);
		if(payList!=null&&payList.size()>0){
			for(PaymentLogModel payLog : payList){
				payLog.setBusinessId(newOrderNo);
			}
			paymentLogService.saveBatchPayLog(payList);
		}
	}
	
	@Override
	public void deleteExamineFailOrder(){
		orderItemMapper.deleteExamineFailOrder();
		orderItemMapper.updateFailItemsData();
	}
	
	@Override
	public int queyBFOrderByMultiOrderNo(String multiChannelNo){
		return orderMapper.queyBFOrderByMultiOrderNo(multiChannelNo);
	}
	
	//重新计算单品的价格
	private BigDecimal updateSupplyPriceMeth(String goods69,int multiChannelId){
		//查下供货价格
		GoodsChannelPrice goodsChannel = goodsChannelService.findGoodsChannelPrice(multiChannelId, goods69);
		if(goodsChannel!=null&&!StringUtil.isBlank(goodsChannel.getGoodsNo_69())){
			return new BigDecimal(goodsChannel.getSupplyPrice()+""); 
		}
		//更新订单明细
		 return new BigDecimal("0");
	 
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
	
	
}
