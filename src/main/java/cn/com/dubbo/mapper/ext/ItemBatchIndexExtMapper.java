package cn.com.dubbo.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.bean.ext.ItemBatchIndex;
import cn.com.dubbo.bean.ext.ItemBatchIndexExample;

public interface ItemBatchIndexExtMapper {
	int countByExample(ItemBatchIndexExample example);

	int deleteByExample(ItemBatchIndexExample example);

	int deleteByPrimaryKey(Long batchItemId);

	int insert(ItemBatchIndex record);

	int insertSelective(ItemBatchIndex record);

	List<ItemBatchIndex> selectByExample(ItemBatchIndexExample example);

	ItemBatchIndex selectByPrimaryKey(Long batchItemId);

	int updateByExampleSelective(@Param("record") ItemBatchIndex record, @Param("example") ItemBatchIndexExample example);

	int updateByExample(@Param("record") ItemBatchIndex record, @Param("example") ItemBatchIndexExample example);

	int updateByPrimaryKeySelective(ItemBatchIndex record);

	int updateByPrimaryKey(ItemBatchIndex record);

	int updateList(List<ItemBatchIndex> list);

	ItemBatchIndex selectByBean(ItemBatchIndex itemBatchIndex);

	void updateOne(ItemBatchIndex indexs);
}