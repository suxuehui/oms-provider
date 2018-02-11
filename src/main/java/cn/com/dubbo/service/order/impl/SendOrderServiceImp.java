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
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.LogisticCompany;
import cn.com.dubbo.model.OrderDelivery;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderItemModelExt;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderModelExt;
import cn.com.dubbo.model.ToSendModel;
import cn.com.dubbo.service.order.SendOrderService;
import cn.com.dubbo.util.StringUtil;

@Service
public class SendOrderServiceImp implements SendOrderService {
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
	public ToSendModel getLogistics(String startCreated, String endCreated, String pageNo, String pageSize, String venderId) {
		ToSendModel s = new ToSendModel();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int currentPage = (Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize);
			map.put("currentPage", currentPage);
			map.put("pageSize", Integer.parseInt(pageSize));
			map.put("startTime", startCreated);
			map.put("endTime", endCreated);
			map.put("multi_channel_id", venderId);
			// 查询已发货和部分发货订单
			List<OrderModel> list = orderMapper.querySendByMap(map);
			List<OrderModelExt> listext = new ArrayList<OrderModelExt>();
			for (OrderModel orderModel : list) {
				OrderModelExt ext = new OrderModelExt();
				// 拷贝订单信息到另一个model
				BeanUtils.copyProperties(orderModel, ext);
				// 根据订单编号查询商品详情
				List<OrderItemModel> item = orderMapper.queryOrderItemsOutGroup(orderModel.getOrderNo());
				List<OrderItemModelExt> listItem = new ArrayList<OrderItemModelExt>();
				for (OrderItemModel orderItemModel : item) {
					OrderItemModelExt orderItemModelExt = new OrderItemModelExt();
					// 拷贝订单详情到另一个model
					BeanUtils.copyProperties(orderItemModel, orderItemModelExt);
					if (StringUtil.isStringNotBlank(orderItemModel.getGoodsNo())) {
						GoodsModel goods = goodsMapper.getGoodByGoodNo(orderItemModel.getGoodsNo());
						if (goods != null) {
							orderItemModelExt.setCid(goods.getGoodsClassId());
						}
					}

					// 查询商品发货状态，发货表里delivery_status为N表示已发货，查不到的表示未发货，为Y的表示已经将发货信息告知对方接口
					OrderDelivery delivery = orderDeliveryMapper.selectOrderDeliveryByStr(orderItemModel.getOrderDeliveryNo());
					if (delivery != null) {
						if (delivery.getDelivery_status().equals("N")) {
							orderItemModelExt.setDes("已发货");
							// 物流信息
							LogisticCompany logisticCompany = logisticMapper.getByPrimaryKey(delivery.getLogistic_company_id());
							if (logisticCompany != null) {
								orderItemModelExt.setLogistic_company_name(logisticCompany.getCompanyName());
								orderItemModelExt.setLogistic_no(delivery.getLogistic_no());
							}
						}
					} else {
						orderItemModelExt.setDes("未发货");
					}
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
