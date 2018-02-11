package cn.com.dubbo.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.dubbo.mapper.LogisticMapper;
import cn.com.dubbo.service.inter.LogisticService;


@Service
public class LogisticServiceImpl implements LogisticService{

	@Resource
	private LogisticMapper logisticMapper;
	
	@Override
	public String findThirdCode(String channelType, String logisticId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelType", channelType);
		map.put("logisticId", logisticId);
		String code = logisticMapper.findThirdCode(map);
		return code;
	}

	
	
}
