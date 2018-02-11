package cn.com.dubbo.service.order;

/**
 * 同步物流信息给第三方，平安商城
 * 
 */
public interface PsEMSUpdateService {

	//发送物流信息
	public void orderSendEMS(String multiChannel);
}