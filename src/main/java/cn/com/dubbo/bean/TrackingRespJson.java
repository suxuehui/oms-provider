package cn.com.dubbo.bean;

import java.util.List;

public class TrackingRespJson extends BaseRespJson{

	private List<TrackingRespData> data ;

	public void setData(List<TrackingRespData> data){
		this.data = data;
	}
	
	public List<TrackingRespData> getData(){
		return this.data;
	}

}
