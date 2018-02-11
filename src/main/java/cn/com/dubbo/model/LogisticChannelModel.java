package cn.com.dubbo.model;

import java.io.Serializable;

/**
 * 物流渠道表
 */
public class LogisticChannelModel implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int logisticId;//内部物流公司id

    private String channelType;//对应的外部渠道类型

    private String channelCode;//第三方渠道对应的物流code
    
    private String companyName;//物流公司名称

    
	public int getLogisticId() {
		return logisticId;
	}

	public void setLogisticId(int logisticId) {
		this.logisticId = logisticId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    
}