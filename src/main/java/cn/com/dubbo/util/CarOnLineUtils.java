package cn.com.dubbo.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.com.dubbo.bean.AccessTokenRespJson;
import cn.com.dubbo.bean.TrackingRespJson;

import com.google.gson.Gson;

public class CarOnLineUtils {

	private static CarOnLineUtils carOnLineUtils = new CarOnLineUtils();
	
	private CarOnLineUtils() {
		super();
	}
	
	private CarOnLineUtils(AccessTokenRespJson accessToken) {
		super();
	}

	private CarOnLineUtils(String account,String password) {
		super();
	}

	public static CarOnLineUtils getInstanse(){
		return carOnLineUtils;
	}
	
	/**
	 * 获取accessToken
	 * @param account 登录汽车在线用户名
	 * @param password 登录密码
	 * @return
	 */
	public AccessTokenRespJson getAccessToken(String account,String password,String access_token){
		Map<String,String> param = new HashMap<String,String>();
		long time = System.currentTimeMillis() / 1000;
		if(StringUtils.isEmpty(access_token)){
			param.put("time", String.valueOf(time));
			param.put("account", account);
//			param.put("signature", MD5Utils.MD5(MD5Utils.MD5(password) + time));
			param.put("signature", password + time);
			try {
//				String resp = HttpClientUtils.doPost("http://api.gpsoo.net/1/auth/access_token", param);
//				AccessTokenRespJson respJson = new Gson().fromJson(resp, AccessTokenRespJson.class);
//				return respJson;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new AccessTokenRespJson();
	}
	
	/**
	 * 跟踪
	 * 获取单个/批量设备最新位置信息 
	 * @param account 申请appkey时所用的汽车在线的登录账号
	 * @param imeis 设备imei号，多个中间用英文逗号隔开； 如果设备过多，建议采用POST方式(一次最多100个IMEI)
	 * @param access_token
	 * @return
	 */
	public TrackingRespJson getTrackingResp(String account,String imeis,String access_token){
		long time = System.currentTimeMillis() / 1000;
		Map<String,String> param = new HashMap<String,String>();
		param.put("time", String.valueOf(time));
		param.put("account", account);
		param.put("access_token", access_token);
		param.put("map_type", "BAIDU");
		param.put("imeis", imeis);
		try {
	//		String respJson = HttpClientUtils.doPost("http://api.gpsoo.net/1/devices/tracking", param);
	//		return new Gson().fromJson(respJson, TrackingRespJson.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TrackingRespJson();
	}
	
}
