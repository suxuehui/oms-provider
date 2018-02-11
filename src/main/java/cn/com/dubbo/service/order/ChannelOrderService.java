package cn.com.dubbo.service.order;

import java.util.List;

import cn.com.dubbo.model.MultiChannelOrderBatchModel;

public interface ChannelOrderService {
	
	/**
	 * 保存渠道订单
	 * @param multiChannelOrderBatchModel
	 */
	public void saveChannelOrder(MultiChannelOrderBatchModel multiChannelOrderBatchModel);
	
	/**
	 * 批量保存渠道订单
	 * @param multiChannelOrderBatchModels
	 */
	public void saveBatchChannelOrder(List<MultiChannelOrderBatchModel> multiChannelOrderBatchModels);
	
	/**
	 * 根据渠道订单ID查询渠道订单
	 * @param multiChannelOrderBatchId
	 * @return
	 */
	public MultiChannelOrderBatchModel findChannelOrderById(Long multiChannelOrderBatchId);
	
	/**
	 * 根据渠道订单ID查询渠道订单是否存在
	 * @param multiChannelOrderBatchId
	 * @return 0：不存在，大于等于1：存在
	 */
	public int isChannelOrderById(Long multiChannelOrderBatchId);
	
	/**
	 * 更新渠道订单
	 * @param MultiChannelOrderBatchModel
	 */
	public void updateChannelOrder(MultiChannelOrderBatchModel MultiChannelOrderBatchModel);
	
	/**
	 * 批量更新渠道订单
	 * @param MultiChannelOrderBatchModels
	 */
	public void updateBatchChannelOrder(List<MultiChannelOrderBatchModel> MultiChannelOrderBatchModels);
	
}
