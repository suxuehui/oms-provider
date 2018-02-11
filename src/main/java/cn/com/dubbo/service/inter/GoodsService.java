package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.GoodsChannelPrice;
import cn.com.dubbo.model.GoodsGroupItemModel;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.GoodsSaleInfoModel;
import cn.com.dubbo.model.GoodsStockInfo;

public interface GoodsService {
	
	/**
	 * 根据商品编号
	 * @param goodNo69
	 * @return
	 */
	public GoodsModel findGoodNo(String goodNo);
	
	/**
	 * 根据商品69码查询商品
	 * @param goodNo69
	 * @return
	 */
	public GoodsModel findGood69(String goodNo69);
	
	/**
	 * 查询商品组合信息,(即TM码查询)
	 * @param goodNo69
	 * @return
	 */
	public List<GoodsGroupItemModel> queryGroupItemByNo69(String goodNo69);
	
	/**
	 * 查询商品组合信息,(即编码查询)
	 * @param goodNo69
	 * @return
	 */
	public List<GoodsGroupItemModel> queryGroupItemByGoodsNo(String goodsNo);
	
	/**
	 * 查询组合码的条目
	 * @param goodNo69
	 * @return
	 */
	public int findGroupNum(String goodNo69);
	
	/**
	 * 查询商品的库存和成本价
	 * @param goodNo69
	 * @return
	 */
	public GoodsSaleInfoModel findSaleInfoByNo69(String goodNo69);
	
	/**
	 * 更新商品库存和成本价
	 */
	public void updateGoodsSaleInfo(GoodsSaleInfoModel saleInfo);
	
	/**
	 * 批量更新商品库存和成本价
	 */
	public void updateBatchGoodsSaleInfo(List<GoodsSaleInfoModel> saleInfoList);
	
	/**
	 * 查询不同渠道对应的商品
	 * @param multiChannelId
	 * @return
	 */
	public List<GoodsChannelPrice> queryGoodsByChannel(int multiChannelId);
	
	/**
	 * 
	 * @param goodNo69
	 * @return
	 */
	public List<GoodsSaleInfoModel> querySaleInfo(int multiChannelId);

	/**
	 * 根据商品编码查询库存
	 * @author hhr
	 * @param goodNo
	 * @return
	 */
	public String findGoodStockNumber(String goodNo);
	
	
	//-----------下面为新增方法---库存备用的方法--------开始-----------
	/**
	 * 查询商品信息
	 * @param goodsNo
	 * @return
	 */
	public GoodsStockInfo findGoodsStock(String goodsNo);
	
	/**
	 * 更新商品库存信息
	 * @param goodsNo 编码
	 * @param usableStock 可用库存
	 * @param soldTotalNum 卖出库存
	 * @param deliverNum 已发货库存
	 */
	public void updateGoodsStock(String goodsNo,int usableStock,
			int soldTotalNum,int deliverNum);
	
	//-----------下面为新增方法---库存备用的方法--------结束-----------
	
	
}