package cn.com.dubbo.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.constant.Constant;
import cn.com.dubbo.constant.OrderState;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.inter.GoodsService;
import cn.com.dubbo.service.inter.OrderReissuedService;
import cn.com.dubbo.service.order.OrderService;
import cn.com.dubbo.service.order.OrderStepService;
import cn.com.dubbo.util.StringUtil;


@Service
public class OrderReissuedServiceImpl implements OrderReissuedService{

	private static final Logger logger = Logger.getLogger(OrderReissuedServiceImpl.class);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private OrderStepService stepService;
	
	
	@Override
	public Map<String,Object> addOrder(String orders) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			logger.info("补发订单操作源数据："+orders);
			JSONObject obj = JSONObject.fromObject(orders);
			String orderNo = obj.getString("orderNo");
			String notes = obj.getString("notes");
			String userId = obj.getString("addUserId");
			JSONArray items = obj.getJSONArray("items");
			if(null!=items){
				JSONObject itemObj = null;
				//跟进orderNo查下订单
				List<OrderModel> orderList = orderService.findOrderByMultiOrderNo(orderNo);
				OrderModel orderModel = null;
				if(null!=orderList&&orderList.size()>0){
					//计算第几次补发
					String orderNoPrefix="";
					int size = orderService.queyBFOrderByMultiOrderNo(orderNo);
					if(size==0){//第一次补发
						orderNoPrefix = "BF";
					}else if(size==1){//第二次补发
						orderNoPrefix = "BFT";
					}else if(size==2){//第三次补发
						orderNoPrefix = "BFW";
					}else{
						new Exception("补发已超过三次，禁止补发");
					}
					for(OrderModel o : orderList){
						if(o.getOrderType()==0&&o.getOrderStatus()!=7){
							orderModel = new OrderModel();
							orderNo = o.getOrderNo();
							o.setOrderNo(orderNoPrefix+o.getMultiChannelOrderNo());
							o.setMultiChannelOrderNo(o.getOrderNo());
							o.setOrderType(1);//补发类型
							o.setOrderStatus(OrderState.STATE_15.getCode());
							o.setSendStatus(Constant.NO);
							o.setActivityDiscountFee(new BigDecimal(0));
							o.setPaidFee(new BigDecimal(0));
							o.setOrderPayFee(new BigDecimal(0));
							o.setOrderFee(new BigDecimal(0));
							o.setSupplyPrice(new BigDecimal(0));
							o.setOrderNotes(notes);
							o.setAuditTime(null);
							o.setAddTime(sdf.format(new Date()));
							o.setAddUserId(Integer.parseInt(userId));
							BeanUtils.copyProperties(o, orderModel);
						}
					}
					
					//查询订单明细
					if(orderModel!=null){
						List<OrderItemModel> itemList = orderService.queryOrderItems(orderNo);
						List<OrderItemModel> itemSaveList = new ArrayList<OrderItemModel>();
						for(int i=0;i<items.size();i++){
							itemObj = items.getJSONObject(i);
							String goods69 = itemObj.getString("goods69");
							String amount = itemObj.getString("amount");
							//新添加的商品
							String mark = itemObj.getString("mark");
							if(StringUtil.isBlank(goods69)){
								throw new Exception(goods69+"传递的69码为空！");
							}
							if(mark.equals("0")){
								if(!StringUtil.isBlank(goods69)&&!StringUtil.isBlank(amount)){
									for(OrderItemModel item : itemList){
										if(goods69.equals(item.getGoodsNo_69())){
											item.setAmount(new Long(amount));
											item.setOrderNo(orderModel.getOrderNo());
											item.setSupplyPrice(new BigDecimal(0));
											item.setPriceDis(new BigDecimal(0));
											item.setGoodsSumFee(new BigDecimal(0));
											item.setOldPrice(new BigDecimal(0));
											item.setPrice(new BigDecimal(0));
											item.setCostPrice(new BigDecimal(0));
											itemSaveList.add(item);
										}
									}
								}else{
									map.put("error", goods69+" 69码对应的商品数量为 "+amount);
									return map;
								}
							}
							
							if(mark.equals("1")){
								GoodsModel goodsModel = goodsService.findGood69(goods69);
								if(goodsModel!=null){
									OrderItemModel item = new OrderItemModel();
									item.setOrderNo(orderModel.getOrderNo());
									item.setGoodsListType(Constant.GOODS_TYPE_GENERAL);
									if("0".equals(goodsModel.getOtcFlag())){
										item.setGoodsType(Constant.GOODS_TYPE_OTHER);
									}else{
										item.setGoodsType(Constant.GOODS_TYPE_RX);
									}
									item.setGoodsNo(goodsModel.getGoodsNo());
									item.setGoodsNo_69(goods69);
									item.setName(goodsModel.getName());
									item.setAmount(new Long(amount));
									item.setCostPrice(new BigDecimal(0));
									item.setStandard(goodsModel.getStandard());
									item.setSupplyPrice(new BigDecimal(0));
									item.setPriceDis(new BigDecimal(0));
									item.setGoodsSumFee(new BigDecimal(0));
									item.setAddTime(sdf.format(new Date()));
									itemSaveList.add(item);
								}else{
									throw new Exception(goods69+"69码无或者重复！");
								}
							}
						}
						
						//保存相关信息
						if(itemSaveList.size()>0){
							this.saveOrderInfo(orderModel, itemSaveList);
						}else{
							map.put("error", "异常，没有该订单号，请联系技术");
						}
					}else{
						map.put("error", "异常，没有该订单号，请联系技术");
					}
				}
			}
		} catch (Exception e) {
			map.put("error", "系统异常，请联系技术人员");
			logger.info("补发订单时，系统异常"+e.getMessage(),e);
		}
		return map;
	}
	
	//必须一个事物
	@Transactional
	private void saveOrderInfo(OrderModel orderModel,List<OrderItemModel> itemList){
		//保存订单
		boolean bl = orderService.isOrderByNo(orderModel.getOrderNo());
		if(!bl){
			orderService.saveOrder(orderModel);
		}
		//保存订单明细
		orderService.saveBatchOrderItem(itemList, null);
	}
	
	/**
	 * {"orderNo":"590163020507","auditId":"22222","operate":"delete pass"}
	 */
	@Override
	public Map<String,Object> examine(String orders){
		logger.info("补发订单操作源数据："+orders);
		JSONObject obj = JSONObject.fromObject(orders);
		Map<String,Object> map = new HashMap<String, Object>();
		OrderModel order = orderService.findOrderByNo(obj.getString("orderNo"));
		if(order.getOrderType()==1){
			String operate = obj.getString("operate");
			if("delete".equals(operate)){
				order.setOrderStatus(OrderState.STATE_7.getCode());
			}else if("pass".equals(operate)){
				order.setOrderStatus(OrderState.STATE_2.getCode());
				//扣减库存
				//查询订单明细
				List<OrderItemModel> itemList = orderService.queryOrderItems(order.getOrderNo());
				stepService.examineNine(itemList);
			}
			order.setAuditUserId(Integer.parseInt(obj.getString("auditId")));
			order.setAuditTime(sdf.format(new Date()));
			orderService.updateOrder(order);
		}else{
			map.put("error", "非补发类型订单");
		}
		return map;
	}
	
}
