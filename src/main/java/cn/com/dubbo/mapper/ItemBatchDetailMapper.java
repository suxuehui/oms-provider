package cn.com.dubbo.mapper;

import java.util.List;

import cn.com.dubbo.bean.ItemBatchDetail;

public interface ItemBatchDetailMapper {
    int deleteByPrimaryKey(Integer orderDataId);

    int insert(ItemBatchDetail record);

    int insertSelective(ItemBatchDetail record);

    ItemBatchDetail selectByPrimaryKey(Integer orderDataId);

    int updateByPrimaryKeySelective(ItemBatchDetail record);

    int updateByPrimaryKey(ItemBatchDetail record);
    
    List<ItemBatchDetail> queryGoodsNo(ItemBatchDetail itemBatchDetail);
    
   	int saveWaresIntoItem(List<ItemBatchDetail> li);
   	
   	List<ItemBatchDetail> queryAmount(List<ItemBatchDetail> li);
   	
   	int queryItemId(ItemBatchDetail itemBatchDetail);
   	
   	Integer  saveWrongNoSales(ItemBatchDetail itemBatchDetail);
   	
   	List<String> selectRepeatWare(List<ItemBatchDetail> ls);
   	
    int saveNoSaleWares(List<ItemBatchDetail> orderItem);

	int updateByGrounpNo(List<ItemBatchDetail> li);
   	
}