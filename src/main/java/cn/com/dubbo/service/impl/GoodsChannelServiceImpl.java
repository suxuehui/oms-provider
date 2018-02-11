package cn.com.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.GoodsChannelPriceMapper;
import cn.com.dubbo.model.GoodsChannelPrice;
import cn.com.dubbo.redis.CacheUtil;
import cn.com.dubbo.service.inter.GoodsChannelService;
import cn.com.dubbo.util.StringUtil;


@Service
public class GoodsChannelServiceImpl implements GoodsChannelService{

	@Resource
	private GoodsChannelPriceMapper goodsChannelMapper;
	
	@Override
	public List<GoodsChannelPrice> queryGoodsByChannel(int multiChannelId) {
		List<GoodsChannelPrice> list = goodsChannelMapper.queryGoodsByChannel(multiChannelId);
		return list;
	}

	@Override
	public void updateGoodsChannelPrice(GoodsChannelPrice goodsChannelPrice) {
		
		goodsChannelMapper.updateGoodsChannelPrice(goodsChannelPrice);
	}

	@Override
	public GoodsChannelPrice findGoodsChannelPrice(int multiChannelId,String goodsNo_69) {
		GoodsChannelPrice goodsPrice = CacheUtil.readCache("GoodsChannelPrice"+goodsNo_69, new GoodsChannelPrice());
		if(goodsPrice==null||StringUtil.isBlank(goodsPrice.getGoodsNo_69())){
			goodsPrice = goodsChannelMapper.findGoodsChannelPrice(multiChannelId,goodsNo_69);
			CacheUtil.setTCache("GoodsChannelPrice"+goodsNo_69, goodsPrice, 60*10);
		}
		return goodsPrice;
	}
	
	@Override
	public GoodsChannelPrice findGCPByGoodsNo(int multiChannelId,String goodsNo) {
		GoodsChannelPrice goodsPrice = CacheUtil.readCache("GoodsChannelPrice"+goodsNo, new GoodsChannelPrice());
		if(goodsPrice==null||StringUtil.isBlank(goodsPrice.getGoodsNo())){
			goodsPrice = goodsChannelMapper.findGCPByGoodsNo(multiChannelId,goodsNo);
			CacheUtil.setTCache("GoodsChannelPrice"+goodsNo, goodsPrice, 60*10);
		}
		return goodsPrice;
	}
	
}
