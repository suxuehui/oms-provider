package cn.com.dubbo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.base.BaseMapper;
import cn.com.dubbo.model.LogisticChannelModel;
import cn.com.dubbo.model.LogisticCompany;

@Repository
public interface LogisticMapper extends BaseMapper<LogisticCompany, Integer> {

	/**
	 * 查询第三方的物流code
	 * 
	 * @param map
	 * @return
	 */
	public String findThirdCode(Map<String, Object> map);

	public void saveLogisticChannel(LogisticChannelModel logisticChannelModel);

	public List<LogisticCompany> findLogisticCompanyId(String companyName);

	public LogisticCompany getByPrimaryKey(Integer logisticCompanyId);

}
