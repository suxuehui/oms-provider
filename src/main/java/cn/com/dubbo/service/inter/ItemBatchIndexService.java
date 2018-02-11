package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.bean.ItemBatchIndex;


public interface ItemBatchIndexService {
	
	public List<ItemBatchIndex> querySaleorderList(String multiChannelId);
	
	public void updateBillStatus(ItemBatchIndex itemIndex);
	
	//批量更新数据
	public void batchUpdateBillStatus(List<ItemBatchIndex> itemIndexList);
	
}