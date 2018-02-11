package cn.com.dubbo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.GoodsChannelPrice;
import cn.com.dubbo.model.GoodsGroupItemModel;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.GoodsSaleInfoModel;
import cn.com.dubbo.model.GoodsStockInfo;

@Repository
public interface GoodsMapper extends BaseMapper<GoodsModel, Integer> {

	/**
	 * 根据商品69码查询商品
	 * 
	 * @param goodNo
	 * @return
	 */
	public GoodsModel getGoodByGoodNo69(String goodNo69);

	/**
	 * 根据商品编码查询商品
	 * 
	 * @param goodNo
	 * @return
	 */
	public GoodsModel getGoodByGoodNo(String goodNo);

	public List<GoodsModel> getGoodListByGoodNo69(String goodNo69);

	/**
	 * 查询商品组合信息
	 * 
	 * @param goodNo69
	 * @return
	 */
	public List<GoodsGroupItemModel> queryGroupItemByNo69(String goodNo69);
	
	/**
	 * 查询商品组合信息
	 * 
	 * @param goodsNo
	 * @return
	 */
	public List<GoodsGroupItemModel> queryGroupItemByGoodsNo(String goodsNo);
	

	/**
	 * 查询组合码的条目
	 * 
	 * @param goodNo69
	 * @return
	 */
	public int findGroupNum(String goodNo69);

	/**
	 * 查询商品库存和成本价
	 * 
	 * @param goodNo69
	 * @return
	 */
	public GoodsSaleInfoModel findSaleInfoByNo69(String goodNo69);

	/**
	 * 更新商品库存和成本价
	 */
	public void updateGoodsSaleInfo(GoodsSaleInfoModel saleInfo);

	/**
	 * 批量更新 商品库存和成本价
	 * 
	 * @param saleInfoList
	 */
	public void updateBatchGoodsSaleInfo(List<GoodsSaleInfoModel> saleInfoList);

	/**
	 * 查询不同渠道对应的商品
	 * 
	 * @param multiChannelId
	 * @return
	 */
	public List<GoodsChannelPrice> queryGoodsByChannel(int multiChannelId);

	/**
	 * @param multiChannelId
	 * @return
	 */
	public List<GoodsSaleInfoModel> querySaleInfo(int multiChannelId);

	
	//-----------下面为新增方法---库存备用的方法--------开始-----------
	/**
	 * 查询商品信息
	 * @param goodsNo
	 * @return
	 */
	public GoodsStockInfo findGoodsStock(String goodsNo);
	
	/**
	 * 查询商品信息
	 * @param goodsNo
	 * @return
	 */
	public void updateGoodsStock(GoodsStockInfo stockInfo);
	
	//-----------下面为新增方法---库存备用的方法--------结束-----------
	
	/**
	 * 根据商品编码查询库存
	 * @author hhr
	 * @param goodNo
	 * @return
	 */
	public String findGoodStockNumber(String goodNo);

}
