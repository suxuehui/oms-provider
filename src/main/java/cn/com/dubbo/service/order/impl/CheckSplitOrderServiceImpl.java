package cn.com.dubbo.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dubbo.bean.OrderDeliveryModel;
import cn.com.dubbo.bean.OrderDeliveryModelExample;
import cn.com.dubbo.bean.OrderSplitErrorModel;
import cn.com.dubbo.mapper.OrderDeliveryModelMapper;
import cn.com.dubbo.mapper.OrderInfoModelMapper;
import cn.com.dubbo.mapper.OrderItemModelMapper;
import cn.com.dubbo.mapper.OrderSplitErrorModelMapper;
import cn.com.dubbo.service.order.CheckSplitOrderService;

/**
 * 检查订单拆分是否正确
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-8-10 下午1:59:33
 */
@Service
public class CheckSplitOrderServiceImpl implements CheckSplitOrderService {

	private static final Logger logger = Logger.getLogger(CheckSplitOrderServiceImpl.class);
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Resource
	private OrderDeliveryModelMapper orderDeliveryModelMapper;
	@Autowired
	private OrderSplitErrorModelMapper orderSplitErrorModelMapper;

	/**
	 * 根据订单明细不同仓库和发货单不同仓库进行比较，如果该订单订单明细和发货单表记录相同说明拆分正确
	 */
	@Override
	public void checkSplitOrder() {
		try {
			int pageSize = 50;
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 前一天 86400000 毫秒 最大值24，超过24加L
			String begin = sdf.format(date.getTime() - 8640000000l);
			String end = sdf.format(date);
			OrderDeliveryModelExample example = new OrderDeliveryModelExample();
			cn.com.dubbo.bean.OrderDeliveryModelExample.Criteria criteria = example.createCriteria();
			criteria.andAddTimeBetween("2016-07-10 18:25:12", end);
			// criteria.andOrderNoEqualTo("BFT493055710408");
			example.setOrderByClause("add_time desc");
			example.setOffset(pageSize);
			// 查询总数进行分页
			int recordCount = orderDeliveryModelMapper.countByExample(example);// 总数
			logger.info("总数：" + recordCount);
			int size = recordCount / pageSize;// 总条数/每页显示的条数=总页数
			int mod = recordCount % pageSize;// 最后一页的条数
			if (mod != 0) {
				size++;
			}
			for (int i = 0; i < size; i++) {
				logger.info("进入循环");
				int startOffset = i * pageSize;
				example.setStartoffset(startOffset);
				// 根据分页参数进行订单查询
				List<OrderDeliveryModel> listInfo = orderDeliveryModelMapper.selectByExampleForPaging(example);
				logger.info("分页条数：" + listInfo.size());
				for (int j = 0; j < listInfo.size(); j++) {
					List<OrderDeliveryModel> deliverys = orderDeliveryModelMapper.selectByExampleGroup(listInfo.get(j).getOrderNo());
					for (int t = 0; t < deliverys.size(); t++) {
						OrderDeliveryModel delivery = deliverys.get(t);
						if (delivery.getCount() != 1) {
							OrderSplitErrorModel model = new OrderSplitErrorModel();
							model.setOrderNo(delivery.getOrderNo());
							model.setAddUserId("149");
							model.setDescription("拆单错误");
							model.setAddTime(sdf.format(new Date()));
							orderSplitErrorModelMapper.insert(model);
						}
					}
				}
			}
		} catch (Exception e) {
			OrderSplitErrorModel modelError = new OrderSplitErrorModel();
			modelError.setAddUserId("149");
			modelError.setDescription("检查订单拆分程序运行异常");
			modelError.setAddTime(sdf.format(new Date()));
			orderSplitErrorModelMapper.insert(modelError);
			logger.error("检查订单拆分运行异常");
		}
	}
}
