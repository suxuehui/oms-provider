package cn.com.dubbo.service.inter;

import java.util.List;

import cn.com.dubbo.model.AreaModel;

public interface AreaService {
	
	//查询area
	public AreaModel findAreaByName(String areaName);
	
	//查询area
	public AreaModel findAreaById(int areaId);
	
	/**
	 * 查询所有区域，并入缓存
	 * 
	 * @return
	 */
	public List<AreaModel> getAllArea();
	
	public int findArea(int parentId,String areaName);
	
}