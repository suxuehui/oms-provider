package cn.com.dubbo.service.order;

import java.util.Map;

public interface OrderRequestService {

	/**
	 * 订单解析入库
	 * 
	 * @author 常胜
	 * @date 2016-7-13 上午10:08:32
	 * @param str
	 * @return
	 * @return Map<String,Object>
	 */
	public Map<String, Object> insert(String str);

	/**
	 * 参数验证
	 * 
	 * @author 常胜
	 * @date 2016-7-13 上午10:08:03
	 * @param str
	 * @return
	 * @return boolean
	 */
	public boolean checkParam(String str);
}