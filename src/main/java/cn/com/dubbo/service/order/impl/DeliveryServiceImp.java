package cn.com.dubbo.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.mapper.OrderDeliveryMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.LogisticCompany;
import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.model.RefundModel;
import cn.com.dubbo.service.order.DeliveryService;
import cn.com.dubbo.util.MultipleDataSource;

@Service
public class DeliveryServiceImp implements DeliveryService {
	@Resource
	OrderPackageMapper orderPackageMapper;
	@Resource
	OrderMapper orderMapper;
	@Resource
	GoodsMapper goodsMapper;
	@Resource
	OrderDeliveryMapper orderDeliveryMapper;
	@Resource
	LogisticMapper logisticMapper;

	@Override
	public RefundModel deliveryUpdate(String str) throws Exception {
		MultipleDataSource.setDataSourceKey("DataSource2");
		List<String> errorList = new ArrayList<String>();
		List<OrderModel> orderModelList = new ArrayList<OrderModel>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<OrderDelivery> orderDeliverylList = new ArrayList<OrderDelivery>();
		List<OrderPackage> orderPackagelList = new ArrayList<OrderPackage>();
		Map<String, Object> map = new HashMap<String, Object>();
		RefundModel s = new RefundModel();
		try {
			// 格式化字符串为json数据
			JSONObject obj = JSONObject.fromObject(str);
			obj = JSONObject.fromObject(obj.get("root").toString());
			int total = (Integer.parseInt(obj.getString("totalResults")));
			// 获取deliverys节点
			JSONArray delivery = JSONArray.fromObject(obj.get("deliverys").toString());
			// 循环获取订单
			for (int i = 0; i < total; i++) {
				JSONObject jsonObject = delivery.getJSONObject(i);
				if (jsonObject.isEmpty()) {
					continue;
				}
				String order_no = jsonObject.getString("orderNo");// 订单编号
				String order_status = jsonObject.getString("orderStatus");// 订单状态
				// String logLogistic_CompanyId = jsonObject.getString("logLogisticCompanyId");// 物流ID
				// 更新order_Info 订单状态
				OrderModel orderModel = new OrderModel();
				orderModel.setOrderNo(order_no);
				orderModel.setOrderStatus(Integer.parseInt(order_status));
				// orderModel.setLogLogisticCompanyId(Integer.parseInt(logLogistic_CompanyId));
				// orderMapper.updateOrder(orderModel);
				orderModelList.add(orderModel);
				JSONArray goods = JSONArray.fromObject(jsonObject.get("goods").toString());
				// 循环获取商品发货状态
				for (int j = 0; j < goods.size(); j++) {
					Map<String, Object> mapUpdate = new HashMap<String, Object>();
					JSONObject goodsObject = goods.getJSONObject(j);
					String goods_no = goodsObject.getString("goodsNo");// 商品编号
					String stock_id = goodsObject.getString("stockId");// 仓库id
					// String logistic_company_id = goodsObject.getString("logistic_company_id");// 物流公司id
					String logistic_company_name = goodsObject.getString("logisticCompanyName");// 物流公司id
					String logistic_no = goodsObject.getString("logisticNo");// 快递单号
					// String delivery_status = goodsObject.getString("deliveryStatus");// N表示已经发货，没有发货传空
					String edit_time = goodsObject.getString("deliveryTime");// 发货时间
					// 更新订单明细
					mapUpdate.put("order_no", order_no);
					mapUpdate.put("goods_no", goods_no);
					mapUpdate.put("stock_id", stock_id);
					mapList.add(mapUpdate);
					// orderMapper.updateOrderItemBymap(mapUpdate);j
					OrderItemModel item = orderMapper.queryItemBymap(mapUpdate);
					OrderDelivery model = orderDeliveryMapper.selectOrderDeliveryByStr(item.getOrderDeliveryNo());
					// 更新发货单和包裹单
					if (model != null) {
						// 更新发货单
						OrderDelivery orderDelivery = new OrderDelivery();
						orderDelivery.setOrder_delivery_no(item.getOrderDeliveryNo());
						// orderDelivery.setLogistic_company_id(Integer.parseInt(logistic_company_id));
						orderDelivery.setLogistic_no(logistic_no);
						// orderDelivery.setStock_id(Integer.parseInt(stock_id));
						// orderDelivery.setDelivery_status(delivery_status);
						orderDelivery.setEdit_time(edit_time);
						// orderDeliveryMapper.updateDeliveryByMap(orderDelivery);
						orderDeliverylList.add(orderDelivery);
						// 更新包裹单
						OrderPackage orderPackage = new OrderPackage();
						LogisticCompany lcy = logisticMapper.getByPrimaryKey(3);
						if (lcy != null) {
							orderPackage.setPackage_no(model.getPackage_no());
							// orderPackage.setDelivery_status(delivery_status);
							orderPackage.setLogistic_company_id(lcy.getLogisticCompanyId());
							orderPackage.setLogistic_company_name(logistic_company_name);
							orderPackage.setLogistic_company_no(lcy.getCompanyNo());
							orderPackage.setStock_id(Integer.parseInt(stock_id));
							orderPackage.setLogistic_no(logistic_no);
							// orderPackageMapper.updateByPackageNo(orderPackage);
							orderPackagelList.add(orderPackage);
							// this.commit(orderModel, mapUpdate, orderDelivery, orderPackage, errorList);
						}
					}
					// 统一进行事物更新
					this.commit(orderModel, mapList, orderDeliverylList, orderPackagelList, errorList);
				}
			}
		} catch (Exception e) {
			// errorList.add(orderModel.getOrderNo());
			throw new Exception();
		}
		return s;
	}

	@Transactional
	private Object commit(OrderModel orderModel, List<Map<String, Object>> mapList, List<OrderDelivery> orderDeliverylList, List<OrderPackage> orderPackagelList, List<String> errorList) {
		try {
			orderMapper.updateOrder(orderModel);
			for (int i = 0; i < mapList.size(); i++) {
				orderMapper.updateOrderItemBymap(mapList.get(i));
				orderDeliveryMapper.updateDeliveryByMap(orderDeliverylList.get(i));
				orderPackageMapper.updateByPackageNo(orderPackagelList.get(i));
			}
		} catch (Exception e) {
			errorList.add(orderModel.getOrderNo());
		}
		return errorList;
	}

}
