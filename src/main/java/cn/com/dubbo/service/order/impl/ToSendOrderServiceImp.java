package cn.com.dubbo.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.GoodsMapper;
import cn.com.dubbo.mapper.OrderMapper;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.OrderItemModel;
import cn.com.dubbo.model.OrderItemModelExt;
import cn.com.dubbo.model.OrderModel;
import cn.com.dubbo.model.OrderModelExt;
import cn.com.dubbo.model.ToSendModel;
import cn.com.dubbo.service.order.ToSendOrderService;
import cn.com.dubbo.util.StringUtil;

@Service
public class ToSendOrderServiceImp implements ToSendOrderService {
	@Resource
	OrderMapper orderMapper;
	@Resource
	GoodsMapper goodsMapper;

	@Override
	public ToSendModel getToSend(String startCreated, String endCreated, String pageNo, String pageSize, String stockId, String venderId) {
		ToSendModel s = new ToSendModel();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int currentPage = (Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize);
			map.put("currentPage", currentPage);
			map.put("pageSize", Integer.parseInt(pageSize));
			map.put("startTime", startCreated);
			map.put("endTime", endCreated);
			map.put("multi_channel_id", 3);
			List<OrderModel> list = orderMapper.queryByMap(map);
			List<OrderModelExt> listext = new ArrayList<OrderModelExt>();
			for (OrderModel orderModel : list) {
				OrderModelExt ext = new OrderModelExt();
				BeanUtils.copyProperties(orderModel, ext);
				List<OrderItemModel> item = orderMapper.queryOrderItemsOutGroup(orderModel.getOrderNo());
				List<OrderItemModelExt> listItem = new ArrayList<OrderItemModelExt>();
				for (OrderItemModel orderItemModel : item) {
					OrderItemModelExt orderItemModelExt = new OrderItemModelExt();
					BeanUtils.copyProperties(orderItemModel, orderItemModelExt);
					if (StringUtil.isStringNotBlank(orderItemModel.getGoodsNo())) {
						GoodsModel goods = goodsMapper.getGoodByGoodNo(orderItemModel.getGoodsNo());
						if (goods != null) {
							orderItemModelExt.setCid(goods.getGoodsClassId());
						}
					}
					orderItemModelExt.setDes("未发货");
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
