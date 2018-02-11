package cn.com.dubbo.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.mapper.OrderDeliveryMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.mapper.OrderPackageMapper;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderItemModelExt;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderModelExt;
import cn.com.dubbo.model.RefundModel;
import cn.com.dubbo.service.order.RefundValidationService;
import cn.com.dubbo.util.MultipleDataSource;
import cn.com.dubbo.util.StringUtil;

@Service
public class RefundValidationServiceImp implements RefundValidationService {
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
	public RefundModel getRefund(String startCreated, String endCreated, String pageNo, String pageSize, String venderId, String orderNo) {
		MultipleDataSource.setDataSourceKey("DataSource");
		RefundModel s = new RefundModel();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int currentPage = (Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize);
			map.put("currentPage", currentPage);
			map.put("pageSize", Integer.parseInt(pageSize));
			map.put("startTime", startCreated);
			map.put("endTime", endCreated);
			map.put("multi_channel_id", venderId);
			if (StringUtil.isStringNotBlank(orderNo)) {
				map.put("orderNo", orderNo);
			}
			// 查询退款中和已退款订单
			List<OrderModel> list = orderMapper.queryRefundByMap(map);
			List<OrderModelExt> listext = new ArrayList<OrderModelExt>();
			for (OrderModel orderModel : list) {
				OrderModelExt ext = new OrderModelExt();
				// 拷贝订单信息到另一个model
				BeanUtils.copyProperties(orderModel, ext);
				// 根据订单编号查询商品详情
				List<OrderItemModel> item = orderMapper.queryOrderItemsStatus(orderModel.getOrderNo());
				List<OrderItemModelExt> listItem = new ArrayList<OrderItemModelExt>();
				for (OrderItemModel orderItemModel : item) {
					OrderItemModelExt orderItemModelExt = new OrderItemModelExt();
					// 拷贝订单详情到另一个model
					BeanUtils.copyProperties(orderItemModel, orderItemModelExt);
					listItem.add(orderItemModelExt);
				}
				ext.setGoods(listItem);
				listext.add(ext);
			}
			s.setTotalResults(list.size());
			s.setDeliverys(listext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
