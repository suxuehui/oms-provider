package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.GoodsStockMapper;
import cn.com.dubbo.mapper.OrderDeliveryMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.GoodsStock;
import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.service.order.SplitOrderService;
import cn.com.dubbo.service.order.TransactionalOrderService;

/**
 *
 */
@Service("splitOrderService")
public class SplitOrderServiceImpl implements SplitOrderService{
	
	private static final Logger logger = Logger.getLogger(SplitOrderServiceImpl.class);
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private OrderDeliveryMapper orderDeliveryMapper;
	
	@Resource
	private GoodsStockMapper goodsStockMapper;
	
	@Resource
	private TransactionalOrderService transactionalOrderService;
	
//	select order_no from  order_info WHERE order_status=2
//	通过以上查询出已审核的订单
//	select goods_no from  order_item WHERE order_no=?
//	根据以上的goods_no，查询出
//	SELECT goods_id FROM goods WHERE goods_no=?		
//			SELECT stock_id FROM `goods_stock` WHERE goods_id=?
	
	@Override
	public void splitOrder(){
		String goodsNo123456=null;
		try {
			List<OrderModel> orderModelList=orderMapper.getOrderNoListByOrderStatus(2);
			String add_time=sdf.format(new Date());
//			如果就一个商品肯定是一个发货单；
			for (int i = 0; i < orderModelList.size(); i++) {
				String	orderNo=orderModelList.get(i).getOrderNo();
//				if (orderNo.equalsIgnoreCase("36A1931033487095544101")) {
//					int a=1+2;
//					int b=a+2;
//				}
				List<OrderItemModel> orderItemModelList2=orderMapper.getOrderItemModelList_NotTM(orderNo);
				if (orderItemModelList2.size()==1) {
					List<OrderDelivery> list_OrderDelivery55=new ArrayList<OrderDelivery>();
					List<OrderItemModel> list_OrderItemModel55=new ArrayList<OrderItemModel>();
					OrderItemModel temOrderItemModel=(OrderItemModel) orderItemModelList2.get(0);
					String	tempOrderNo=temOrderItemModel.getOrderNo();
					temOrderItemModel.getGoodsNo();
					//得到商品的仓库ID
					int goodsId=0,stock_id=0;
/*					goodsNo123456=temOrderItemModel.getGoodsNo();
					if (temOrderItemModel.getGoodsNo().equalsIgnoreCase("QX0356") || temOrderItemModel.getGoodsNo().equalsIgnoreCase("JSYP0228")) {
						System.out.println("667788   OrderNo=" + temOrderItemModel.getOrderNo());
					}else{
						System.out.println("557784   OrderNo=" + temOrderItemModel.getOrderNo() +"   商品编码是："+temOrderItemModel.getGoodsNo());
						goodsId=goodsStockMapper.getGoodsIdByGoodsNo(temOrderItemModel.getGoodsNo());
					}*/
					List<GoodsModel> goodsModelList =goodsStockMapper.getGoodsIdByGoodsNo(temOrderItemModel.getGoodsNo());
//					goodsModelList.get(0);
					if (goodsModelList!=null && goodsModelList.size()>0 && (goodsModelList.get(0)!=null)) {
						goodsId=goodsModelList.get(0).getGoodsId();
						List<GoodsStock> goodsStockList=goodsStockMapper.getStockIdByGoodsId(goodsId);
						//TODO
						if (goodsStockList!=null && goodsStockList.size()>0) {
							stock_id=goodsStockList.get(0).getStock_id();
						}else{
							stock_id=1;
						}
					} else {
						stock_id=1;
					}

					OrderDelivery orderDelivery=new OrderDelivery();
					orderDelivery.setOrder_delivery_no(tempOrderNo);
//					orderDelivery.setPackage_no(package_no);
					orderDelivery.setOrder_no(tempOrderNo);
					orderDelivery.setStock_id(stock_id);
					orderDelivery.setAdd_time(add_time);
					//TODO 物流信息是从订单主表取得的吗？ 此时是：已拆单
//					orderDelivery.setLogistic_code(logistic_code);
//					orderDelivery.setLogistic_company_name(logistic_company_name);
//					orderDelivery.setLogistic_code(logistic_code);
//					orderDelivery.setState(state);
					//orderDeliveryMapper.saveOrderDelivery(orderDelivery);
					String orderDeliveryNo=tempOrderNo;
					OrderItemModel itemModel=new OrderItemModel();
					itemModel.setOrderItemId(temOrderItemModel.getOrderItemId());
					itemModel.setOrderDeliveryNo(orderDeliveryNo);
					//orderMapper.updateOrderDeliveryNo_OrderItem(itemModel);
					
					OrderModel orderModel=new OrderModel();
					orderModel.setOrderNo(tempOrderNo);
					orderModel.setOrderStatus(4);
					//orderMapper.updateOrderStatus(orderModel);
					list_OrderDelivery55.add(orderDelivery);
					list_OrderItemModel55.add(itemModel);
					transactionalOrderService.saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus(list_OrderDelivery55,list_OrderItemModel55,orderModel);
				} 
				
				if (orderItemModelList2.size()>1) {
					Map<Object,String> map=new HashMap<Object,String>();
					for (int j = 0; j < orderItemModelList2.size(); j++) {
						OrderItemModel temOrderItemModel=(OrderItemModel) orderItemModelList2.get(j);
						temOrderItemModel.getGoodsNo();
						long orderItemId_temp=temOrderItemModel.getOrderItemId();
						//得到商品仓库的ID
						int goodsId=0,stock_id=0;
						List<GoodsModel> goodsModelList =goodsStockMapper.getGoodsIdByGoodsNo(temOrderItemModel.getGoodsNo());
						if (goodsModelList!=null && goodsModelList.size()>0 && (goodsModelList.get(0)!=null)) {
							goodsId=goodsModelList.get(0).getGoodsId();
							List<GoodsStock> goodsStockList=goodsStockMapper.getStockIdByGoodsId(goodsId);
							//TODO
							if (goodsStockList!=null && goodsStockList.size()>0) {
								stock_id=goodsStockList.get(0).getStock_id();
							}else{
								stock_id=1;
							}
						} else {
							stock_id=1;
						}
						
						if (stock_id!=0) {
							if(map.get(new Integer(stock_id))==null || map.get(new Integer(stock_id)).equals("")){
//								Map map222=new HashMap<String,String>();
//								map222.put(new Integer(stock_id), new Integer(goodsId));
								map.put(new Integer(stock_id), new Long(orderItemId_temp).toString());
							}else {
								String orderItemIds_before = (String) map.get(new Integer(stock_id));
								String orderItemIds_after=orderItemIds_before.concat("&").concat(new Long(orderItemId_temp).toString());
//								String goodsIds=map333.get(new Integer(stock_id)).toString().concat(";").concat(new Integer(goodsId).toString());
//								map333.put(new Integer(stock_id), goodsIds);
								map.put(new Integer(stock_id), orderItemIds_after);
							}
						}
					}
					
					Object[] array=map.keySet().toArray();
					int num=array.length;
					if (num==1) {
						List<OrderDelivery> list_OrderDelivery66=new ArrayList<OrderDelivery>();
						OrderDelivery orderDelivery66=new OrderDelivery();
						String order_delivery_no=orderNo;
						orderDelivery66.setOrder_delivery_no(order_delivery_no);
//						orderDelivery66.setPackage_no(package_no);
						orderDelivery66.setOrder_no(orderNo);
						orderDelivery66.setStock_id(new Integer(array[0].toString()).intValue());
						
						orderDelivery66.setAdd_time(add_time);
						list_OrderDelivery66.add(orderDelivery66);
//						orderDeliveryMapper.saveOrderDelivery(orderDelivery66);
						String orderDeliveryNo=order_delivery_no;
//						OrderItemModel itemModel=new OrderItemModel();
						String orderItemIds_after=map.get(array[0]);
						String[] orderItemId_array=orderItemIds_after.split("&");
						List<OrderItemModel> list_OrderItemModel66=new ArrayList<OrderItemModel>();
						for (int j = 0; j < orderItemId_array.length; j++) {
							OrderItemModel itemModel66=new OrderItemModel();
							itemModel66.setOrderItemId(new Long(orderItemId_array[j]).longValue());
							itemModel66.setOrderDeliveryNo(orderDeliveryNo);
							list_OrderItemModel66.add(itemModel66);
//							orderMapper.updateOrderDeliveryNo_OrderItem(itemModel66);
						}
						OrderModel orderModel66=new OrderModel();
						orderModel66.setOrderNo(orderNo);
						orderModel66.setOrderStatus(4);
//						orderMapper.updateOrderStatus(orderModel);
						transactionalOrderService.saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus(list_OrderDelivery66,list_OrderItemModel66,orderModel66);
					}
					if (num>1) {
						List<OrderDelivery> list_OrderDelivery77=new ArrayList<OrderDelivery>();
						List<OrderItemModel> list_OrderItemModel77=new ArrayList<OrderItemModel>();
						for (int j = 0; j < num; j++) {
//							Map map555=(Map) map.get(array[j]);
							OrderDelivery orderDelivery=new OrderDelivery();
							String order_delivery_no=orderNo.concat("-").concat(new Integer(j+1).toString());
							orderDelivery.setOrder_delivery_no(order_delivery_no);
//							orderDelivery.setPackage_no(package_no);
							orderDelivery.setOrder_no(orderNo);
							orderDelivery.setStock_id(new Integer(array[j].toString()).intValue());
							
							orderDelivery.setAdd_time(add_time);
//							orderDeliveryMapper.saveOrderDelivery(orderDelivery);
							list_OrderDelivery77.add(orderDelivery);
							
							String orderDeliveryNo=order_delivery_no;
							
//							OrderItemModel itemModel=new OrderItemModel();
							String orderItemIds_after=map.get(array[j]);
							String[] orderItemId_array=orderItemIds_after.split("&");
							for (int jj = 0; jj < orderItemId_array.length; jj++) {
								OrderItemModel itemModel=new OrderItemModel();
								itemModel.setOrderItemId(new Long(orderItemId_array[jj]).longValue());
								itemModel.setOrderDeliveryNo(orderDeliveryNo);
//								orderMapper.updateOrderDeliveryNo_OrderItem(itemModel);
								list_OrderItemModel77.add(itemModel);
							}
						}
						
						OrderModel orderModel77=new OrderModel();
						orderModel77.setOrderNo(orderNo);
						orderModel77.setOrderStatus(4);
//						orderMapper.updateOrderStatus(orderModel);
						transactionalOrderService.saveOrderDelivery_updateOrderDeliveryNo_OrderItem_updateOrderStatus(list_OrderDelivery77,list_OrderItemModel77,orderModel77);
					}
				}
//				Map map=new HashMap<String,String>();
//				以下放到HashMap对象
//				仓库1ID:商品1ID;仓库2ID:商品2ID;仓库3ID:商品3ID,商品4ID
//				如果HashMap的getKey得到的值不为空，就put成格式“仓库3ID:商品3ID,商品4ID”；
//				如果HashMap的getKey得到的值为空，就put成格式“仓库1ID:商品1ID”；
//				这样通过HashMap的size得到需要拆分成几个发货单，
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}	
}
