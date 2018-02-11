package cn.com.dubbo.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.AreaModel;

@Repository
public interface AreaMapper extends BaseMapper<AreaModel, Integer>{
	
	//查询byName
	public AreaModel findAreaByName(String areaName);
	
	public AreaModel findAreaById(int areaId);

	public List<AreaModel> getAllArea();
	
	/**
	 * 精确查询区域信息
	 * @param parentId
	 * @param areaName
	 * @return
	 */
	public String findArea(int parentId,String areaName);
	
	/**
	 * 模糊查询区域信息
	 * @param parentId
	 * @param areaName
	 * @return
	 */
	public String findlikeArea(int parentId,String areaName);
	
}
