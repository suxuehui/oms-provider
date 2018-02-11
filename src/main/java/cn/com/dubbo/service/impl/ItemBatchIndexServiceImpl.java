package cn.com.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.bean.ItemBatchIndex;
import cn.com.dubbo.mapper.ItemBatchIndexMapper;
import cn.com.dubbo.service.inter.ItemBatchIndexService;


@Service
public class ItemBatchIndexServiceImpl implements ItemBatchIndexService{

	private static final Logger logger = Logger.getLogger(ItemBatchIndexServiceImpl.class);
	
	@Resource
	private ItemBatchIndexMapper itemBatchIndexMapper;

	@Override
	public List<ItemBatchIndex> querySaleorderList(String multiChannelId){
		List<ItemBatchIndex> list = itemBatchIndexMapper.querySaleorderList(multiChannelId);
		return list;
	}
	
	@Override
	public void updateBillStatus(ItemBatchIndex itemIndex){
		itemBatchIndexMapper.updateBillStatus(itemIndex);
	}
	
	@Override
	public void batchUpdateBillStatus(List<ItemBatchIndex> itemIndexList){
		try {
			itemBatchIndexMapper.batchUpdateBillStatus(itemIndexList);
		} catch (Exception e) {
			logger.error("像202中间库同步库存成功，但是更新itemBatchIndexMapper状态失败，"+e.getMessage(),e);
			if(itemIndexList!=null&&itemIndexList.size()>0){
				for(ItemBatchIndex index : itemIndexList){
					updateBillStatus(index);
				}
			}
			
		}
		
	}
	
}
