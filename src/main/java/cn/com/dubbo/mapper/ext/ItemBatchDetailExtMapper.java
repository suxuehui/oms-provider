package cn.com.dubbo.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dubbo.bean.ext.ItemBatchDetail;
import cn.com.dubbo.bean.ext.ItemBatchDetailExample;

public interface ItemBatchDetailExtMapper {
	int countByExample(ItemBatchDetailExample example);

	int deleteByExample(ItemBatchDetailExample example);

	int deleteByPrimaryKey(Integer orderDataId);

	int insert(ItemBatchDetail record);

	int insertSelective(ItemBatchDetail record);

	List<ItemBatchDetail> selectByExample(ItemBatchDetailExample example);

	ItemBatchDetail selectByPrimaryKey(Integer orderDataId);

	int updateByExampleSelective(@Param("record") ItemBatchDetail record, @Param("example") ItemBatchDetailExample example);

	int updateByExample(@Param("record") ItemBatchDetail record, @Param("example") ItemBatchDetailExample example);

	int updateByPrimaryKeySelective(ItemBatchDetail record);

	int updateByPrimaryKey(ItemBatchDetail record);

	int updateList(List<ItemBatchDetail> list);

	// 郑立臣添加，更新常说所说字段
	int updateOrder(ItemBatchDetail itemBatchDetail);

	int updateByPojo(ItemBatchDetail record);

	void updateOne(ItemBatchDetail batchDetail);

}