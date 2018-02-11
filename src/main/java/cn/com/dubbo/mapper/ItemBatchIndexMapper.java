package cn.com.dubbo.mapper;


import java.util.List;

import cn.com.dubbo.bean.ItemBatchIndex;

public interface ItemBatchIndexMapper {
    int insert(ItemBatchIndex record);

    int insertSelective(ItemBatchIndex record);
    
    public List<ItemBatchIndex> querySaleorderList(String multiChannelId);
    
    public void updateBillStatus(ItemBatchIndex itemIndex);
    
    public int saveWaresIntoItem(ItemBatchIndex itemIndex);
    
    public int updateByGrounpNo(ItemBatchIndex itemIndex);

    //批量更新数据
	void batchUpdateBillStatus(List<ItemBatchIndex> itemIndexList);
    
}