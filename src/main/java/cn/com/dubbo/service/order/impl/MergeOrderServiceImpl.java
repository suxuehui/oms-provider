package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.bean.OrderItem;
import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.mapper.OrderDeliveryMapper;
import cn.com.dubbo.mapper.OrderItemMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.model.LogisticCompany;
import cn.com.dubbo.model.MultiChannelModel;
import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderPackage;
import cn.com.dubbo.model.OrderPackageItem;
import cn.com.dubbo.service.order.MergeOrderService;

/**
 *
 */
@Service
public class MergeOrderServiceImpl implements MergeOrderService {

	// private static final Logger logger = Logger.getLogger(MergeOrderServiceImpl.class);

	@Resource
	private OrderDeliveryMapper orderDeliveryMapper;
	@Resource
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private LogisticMapper logisticMapper;

	// 订单合并
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void mergeOrder() {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		// 查询来源渠道
		List<MultiChannelModel> multi = orderDeliveryMapper.queryMultiChannel();
		/* 需要合并的订单处理开始 */
		for (int t = 0; t < multi.size(); t++) {
			// 根据渠道来源查询订单
			List<OrderModel> orderModels = orderDeliveryMapper.queryOrderInfoByGroup(multi.get(t).getMultiChannelId());
			// 根据收货地址，收货人，电话查询需要合并的订单号
			for (int j = 0; j < orderModels.size(); j++) {
				List<OrderModel> models = orderDeliveryMapper.queryOrderInfoByModel(orderModels.get(j));
				// 根据订单号到发货单表中查询需要合并的发货单信息
				for (int i = 0; i < models.size(); i++) {
					List<OrderDelivery> deliverys = orderDeliveryMapper.selectOrderDeliveryByNo(models.get(i).getOrderNo());
					// 根据发货单编号查询订单明细
					// List<OrderItem> orderItems = new ArrayList<OrderItem>();
					for (int k = 0; k < deliverys.size(); k++) {
						List<OrderItem> item = orderItemMapper.getOrderItem(deliverys.get(k).getOrder_delivery_no());
						orderItems.addAll(item);
					}
					// 将发货仓库进行比较
					String packageNo = null;
					for (int y = 0; y < orderItems.size(); y++) {
						OrderItem oItem = orderItemMapper.selectByPrimaryKey(orderItems.get(y).getOrder_item_id());
						for (int e = y + 1; e < orderItems.size(); e++) {
							OrderItem oIm = orderItemMapper.selectByPrimaryKey(orderItems.get(e).getOrder_item_id());
							// 筛选如果发货仓库号相等，需要进行合并
							oItem.setStock_id(1);
							oIm.setStock_id(1);
							if (oItem.getStock_id().equals(oIm.getStock_id())) {
								Map<String, Object> map = new HashMap<String, Object>();
								Map<String, Object> mapoIm = new HashMap<String, Object>();
								packageNo = "HB" + oItem.getOrder_delivery_no();
								map.put("packageNo", packageNo);
								map.put("deliveryNo", oIm.getOrder_delivery_no());
								orderDeliveryMapper.updateByMap(map);
								mapoIm.put("packageNo", packageNo);
								mapoIm.put("deliveryNo", oItem.getOrder_delivery_no());
								orderDeliveryMapper.updateByMap(mapoIm);
								orderItems.remove(e);

							}
						}
					}
				}
			}
		}
		if (orderItems.size() > 0) {
			for (int n = 0; n < orderItems.size(); n++) {
				// 获取发货信息
				OrderModel m = orderMapper.getByPrimaryKey(orderItems.get(n).getOrder_no());
				LogisticCompany company = logisticMapper.getByPrimaryKey(m.getLogLogisticCompanyId());
				OrderDelivery orderDelivery_temp = new OrderDelivery();
				orderDelivery_temp.setOrder_delivery_no(orderItems.get(n).getOrder_delivery_no());
				orderDelivery_temp.setIs_delete("N");
				OrderDelivery ext = orderDeliveryMapper.selectOrderDeliveryBydeliveryNo(orderDelivery_temp);
				// 插入包裹
				OrderPackage orderpk = new OrderPackage();
				BeanUtils.copyProperties(ext, orderpk);
				orderpk.setLogistic_company_name(company.getCompanyName());
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = dateFormat.format(date);
				orderpk.setAdd_time(time);
				orderpk.setPackage_no(orderItems.get(n).getOrder_delivery_no());
				OrderModel om = orderMapper.getByPrimaryKey(orderItems.get(n).getOrder_no());
				orderpk.setMulti_channel_id(om.getMultiChannelId());
				orderDeliveryMapper.insertPackageSelective(orderpk);
				OrderModel orderModel = new OrderModel();
				orderModel.setOrderStatus(5);
				orderModel.setOrderNo(orderItems.get(n).getOrder_no());
				// 更新订单状态为代发货
				orderMapper.updateOrderStatus(orderModel);
				// 根据包裹编号查询发货单
				List<OrderDelivery> listDel = orderDeliveryMapper.getDeliveryByPackageNo("HB" + orderpk.getPackage_no());
				if (listDel.size() > 0) {
					for (int x = 0; x < listDel.size(); x++) {
						List<OrderItem> listIt = orderItemMapper.getOrderItem(listDel.get(x).getOrder_delivery_no());
						if (listIt.size() > 0) {
							for (int z = 0; z < listIt.size(); z++) {
								// 插入包裹明细
								Date d = new Date();
								SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String t = dateFormat.format(d);
								OrderPackageItem item = new OrderPackageItem();
								BeanUtils.copyProperties(listIt.get(z), item);
								item.setPackage_id(orderpk.getPackage_id());
								item.setAdd_time(t);
								orderDeliveryMapper.insertPackageItemSelective(item);
							}
						}
					}
				}
			}
		}
		/* 需要合并的订单处理结束 */
		/* 普通订单处理开始 */
		List<OrderDelivery> deliveryList = orderDeliveryMapper.getList();
		for (int r = 0; r < deliveryList.size(); r++) {
			OrderPackage orderage = new OrderPackage();
			// 获取发货信息
			LogisticCompany company = logisticMapper.getByPrimaryKey(deliveryList.get(r).getLogistic_company_id());
			BeanUtils.copyProperties(deliveryList.get(r), orderage);
			// orderage.setLogistic_company_name(company.getCompanyName());
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(date);
			orderage.setAdd_time(time);
			orderage.setPackage_no(deliveryList.get(r).getOrder_delivery_no());
			OrderModel om = orderMapper.getByPrimaryKey(deliveryList.get(r).getOrder_no());
			orderage.setMulti_channel_id(om.getMultiChannelId());
			orderDeliveryMapper.insertPackageSelective(orderage);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("packageNo", deliveryList.get(r).getOrder_delivery_no());
			map.put("deliveryNo", deliveryList.get(r).getOrder_delivery_no());
			orderDeliveryMapper.updateByMap(map);
			OrderModel orderModel = new OrderModel();
			orderModel.setOrderStatus(5);
			orderModel.setOrderNo(deliveryList.get(r).getOrder_no());
			// 更新订单状态为代发货
			orderMapper.updateOrderStatus(orderModel);
			if (deliveryList.size() > 0) {
				List<OrderItem> listItm = orderItemMapper.getOrderItem(deliveryList.get(r).getOrder_delivery_no());
				if (listItm.size() > 0) {
					for (int z = 0; z < listItm.size(); z++) {
						// 插入包裹明细
						Date d = new Date();
						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String t = f.format(d);
						OrderPackageItem pacitem = new OrderPackageItem();
						BeanUtils.copyProperties(listItm.get(z), pacitem);
						pacitem.setOrder_item_id(listItm.get(z).getOrder_item_id());
						pacitem.setPackage_id(orderage.getPackage_id());
						pacitem.setAdd_time(t);
						orderDeliveryMapper.insertPackageItemSelective(pacitem);
					}
				}

			}
		}
	}
}
