package cn.com.dubbo.service.order;

import java.util.List;

import cn.com.dubbo.bean.TmpHhr;


public interface ProductInventoryService {
	
	//从e店宝获取产品库存信息
	public String queryProductInventory(Integer page_size,Integer page_no,String date_type,String begin_time,String end_time,String product_no,
										String standard,String bar_code,String sort_no,String store_id,String isuit,String iscut_store);
	//从e店宝同步产品库存
	public String updateGoodsSaleInfo(Integer page_size,Integer page_no,String date_type,String begin_time,String end_time,String product_no,
			String standard,String bar_code,String sort_no,String store_id,String isuit,String iscut_store);
	
	//从e店宝同步产品库存
	public String updateGoodsSaleInfo(String result);
		
	//查询temp_hhr表中的数据,更新物流信息和订单信息
	public List<TmpHhr> queryTmpHhr();
	 
}