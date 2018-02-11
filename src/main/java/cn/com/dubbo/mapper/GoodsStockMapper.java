package cn.com.dubbo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.GoodsModel;
import cn.com.dubbo.model.GoodsStock;

@Repository
public interface GoodsStockMapper extends BaseMapper<GoodsStock, Integer>{
//	根据以上的goods_no，查询出
//	SELECT goods_id FROM goods WHERE goods_no=?		
//			SELECT stock_id FROM `goods_stock` WHERE goods_id=?
	
	/**
	 * @param goodsNo
	 * @return
	 */
	public List<GoodsModel> getGoodsIdByGoodsNo(String goodsNo);
	
	/**
	 * @param goodsId
	 * @return
	 */
	public List<GoodsStock> getStockIdByGoodsId(int goodsId);
	
}
