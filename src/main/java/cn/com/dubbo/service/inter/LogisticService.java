package cn.com.dubbo.service.inter;

public interface LogisticService {
	

	/**
	 * 查询物流编号对应的第三方的code
	 * @param channel
	 * @param logisticId
	 */
	public String findThirdCode(String channelType,String logisticId);
	
	
}