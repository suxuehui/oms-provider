package cn.com.dubbo.mapper;


import java.util.List;
import org.springframework.stereotype.Repository;
import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.GoodsChannelPrice;

@Repository
public interface GoodsChannelPriceMapper extends BaseMapper<GoodsChannelPrice, Integer>{
	/**
	 * 查询不同渠道对应的商品
	 * @param multiChannelId
	 * @return
	 */
	public List<GoodsChannelPrice> queryGoodsByChannel(int multiChannelId);
	/**
	 */
	public void updateGoodsChannelPrice(GoodsChannelPrice goodsChannelPrice);
	
	//
	public GoodsChannelPrice findGoodsChannelPrice(int multiChannelId,String goodsNo_69);
	
	
	public GoodsChannelPrice findGCPByGoodsNo(int multiChannelId, String goodsNo);
}
