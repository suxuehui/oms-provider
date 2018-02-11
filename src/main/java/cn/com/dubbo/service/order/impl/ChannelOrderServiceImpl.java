package cn.com.dubbo.service.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.dubbo.mapper.ChannelMapper;
import cn.com.dubbo.model.MultiChannelOrderBatchModel;
import cn.com.dubbo.service.order.ChannelOrderService;

@Service("channelOrderService")
@Transactional
public class ChannelOrderServiceImpl implements ChannelOrderService {

	@Resource
	private ChannelMapper channelMapper;

	@Override
	public void saveChannelOrder(MultiChannelOrderBatchModel multiChannelOrderBatchModel) {
		// TODO Auto-generated method stub
		channelMapper.saveChannelOrder(multiChannelOrderBatchModel);
	}

	@Override
	public void saveBatchChannelOrder(List<MultiChannelOrderBatchModel> multiChannelOrderBatchModels) {
		// TODO Auto-generated method stub
		channelMapper.saveBatchChannelOrder(multiChannelOrderBatchModels);
	}

	@Override
	public MultiChannelOrderBatchModel findChannelOrderById(Long multiChannelOrderBatchId) {
		// TODO Auto-generated method stub
		return channelMapper.findChannelOrderById(multiChannelOrderBatchId);
	}

	@Override
	public int isChannelOrderById(Long multiChannelOrderBatchId) {
		// TODO Auto-generated method stub
		return channelMapper.isChannelOrderById(multiChannelOrderBatchId);
	}

	@Override
	public void updateChannelOrder(MultiChannelOrderBatchModel MultiChannelOrderBatchModel) {
		// TODO Auto-generated method stub
		channelMapper.updateChannelOrder(MultiChannelOrderBatchModel);
	}

	@Override
	public void updateBatchChannelOrder(List<MultiChannelOrderBatchModel> MultiChannelOrderBatchModels) {
		// TODO Auto-generated method stub
		channelMapper.updateBatchChannelOrder(MultiChannelOrderBatchModels);
	}

}
