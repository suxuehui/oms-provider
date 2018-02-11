package cn.com.dubbo.service.order;

/**
 * 同步物流信息给第三方（平安，jd）
 * 
 */
public interface EMSUpdateService {

	//发送物流信息
	public void orderSendEMS(String multiChannel);
}