package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.GoodsChannelPrice;

public interface GoodsChannelService {
	
	public List<GoodsChannelPrice> queryGoodsByChannel(int multiChannelId);
	/**
	 */
	public void updateGoodsChannelPrice(GoodsChannelPrice goodsChannelPrice);
	
	//
	public GoodsChannelPrice findGoodsChannelPrice(int multiChannelId,String goodsNo_69);
	
	public GoodsChannelPrice findGCPByGoodsNo(int multiChannelId,String goodsNo);
	
}