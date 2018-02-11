package cn.com.dubbo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.GoodsSaleInfoModel;

@Repository
public interface GoodsSaleInfoMapper extends BaseMapper<GoodsSaleInfoModel, Integer>{

	//批量查询产品条目数
	Integer checkProductInventory(List<GoodsSaleInfoModel> list);

	//批量更改商品的库存信息
	void batchUpdateProductInventory(List<GoodsSaleInfoModel> list);

	//查询单条
	GoodsSaleInfoModel queryGoodsSaleInfo(String goodsNo);

	//插入商品(单)
	Integer insertGoodsSaleInfo(GoodsSaleInfoModel goodsSale);

	//修改商品(单)
	void updateGoodsSaleInfo(GoodsSaleInfoModel goodsSale);
 
	
	
}
