package cn.com.dubbo.service.order;

import java.util.Map;

/**
 * 外部(第三方)库存更新接口
 */
public interface OutStockUpdateService {

	//平安库存更新接口
	public void paStockUpdate();
	
	//更新指定商品的库存
	public String paUpdateStock(Map<String, Integer> params);

}