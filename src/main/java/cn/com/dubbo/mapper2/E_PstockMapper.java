package cn.com.dubbo.mapper2;

import java.util.List;
import java.util.Map;

import cn.com.dubbo.model2.E_Pstock;

public interface E_PstockMapper extends BaseDaoMapper2<E_Pstock>{
	
	//查询商品入库总数
	public E_Pstock findGoodsTotalNum(String goodsNo);
	
	//未销账的库存
	public String findNOffStock(String goodsNo);
	
	//查询某商品的库存列表
	public List<E_Pstock> queryStockList(String goodsNo);
	
	//查询库存中间表orderbatchno是否存在
	public String findBatchNo(Map<String,Object> params);
	
}