package cn.com.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.AreaMapper;
import cn.com.dubbo.model.AreaModel;
import cn.com.dubbo.redis.CacheUtil;
import cn.com.dubbo.redis.JedisConstant;
import cn.com.dubbo.service.inter.AreaService;
import cn.com.dubbo.util.StringUtil;


@Service
public class AreaServiceImpl implements AreaService{

	private static final Logger logger = Logger.getLogger(AreaServiceImpl.class);
	
	@Resource
	private AreaMapper areaMapper;

	@Override
	public AreaModel findAreaByName(String areaName){
		AreaModel model = areaMapper.findAreaByName(areaName);
		return model;
	}
	
	@Override
	public AreaModel findAreaById(int areaId){
		AreaModel model = areaMapper.findAreaById(areaId);
		return model;
	}
	
	@Override
	public List<AreaModel> getAllArea(){
		
		List<AreaModel> list = null;
		try {
			list = CacheUtil.readCache(JedisConstant.ALL_AREAS, new ArrayList<AreaModel>());
			if(null==list || list.size()==0){
				list = areaMapper.getAllArea();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int findArea(int parentId,String areaName){
		
		try {
			//先精确查询区域id
			String areaId = areaMapper.findArea(parentId,areaName);
			//如果没有，则模糊查询
			if(StringUtil.isBlank(areaId)||"0".equals(areaId)){
				areaId = areaMapper.findlikeArea(parentId,areaName);
			}
			if(!StringUtil.isBlank(areaId)){
				return new Integer(areaId).intValue();
			}
			
		} catch (Exception e) {
			logger.error("查询区域信息错误，传递的参数为：parentId ： "+parentId+" ,areaName :"+areaName+" "+e.getMessage(),e);
		}
		return 0;
	}
	
}
