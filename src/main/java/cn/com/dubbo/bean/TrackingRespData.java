package cn.com.dubbo.bean;

import com.google.gson.Gson;

public class TrackingRespData {
	
	private String imei;
	
	private String name;

	private int device_info;

	private int device_info_new;

	private int gps_time;

	private int sys_time;

	private int heart_time;

	private int server_time;

	private double lng;

	private double lat;

	private int course;

	private int speed;

	private String acc;

	private int acc_seconds;

	private int seconds;

    private int overdate;

    public int getOverdate() {
        return overdate;
    }

    public void setOverdate(int overdate) {
        this.overdate = overdate;
    }

    public void setImei(String imei){
		this.imei = imei;
	}
	
	public String getImei(){
		return this.imei;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDevice_info(int device_info){
		this.device_info = device_info;
	}
	
	public int getDevice_info(){
		return this.device_info;
	}
	
	public void setDevice_info_new(int device_info_new){
		this.device_info_new = device_info_new;
	}
	
	public int getDevice_info_new(){
		return this.device_info_new;
	}
	
	public void setGps_time(int gps_time){
		this.gps_time = gps_time;
	}
	
	public int getGps_time(){
		return this.gps_time;
	}
	
	public void setSys_time(int sys_time){
		this.sys_time = sys_time;
	}
	
	public int getSys_time(){
		return this.sys_time;
	}
	
	public void setHeart_time(int heart_time){
		this.heart_time = heart_time;
	}
	
	public int getHeart_time(){
		return this.heart_time;
	}
	
	public void setServer_time(int server_time){
		this.server_time = server_time;
	}
	
	public int getServer_time(){
		return this.server_time;
	}
	
	public void setLng(double lng){
		this.lng = lng;
	}
	
	public double getLng(){
		return this.lng;
	}
	
	public void setLat(double lat){
		this.lat = lat;
	}
	
	public double getLat(){
		return this.lat;
	}
	
	public void setCourse(int course){
		this.course = course;
	}
	
	public int getCourse(){
		return this.course;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setAcc(String acc){
		this.acc = acc;
	}
	
	public String getAcc(){
		return this.acc;
	}
	
	public void setAcc_seconds(int acc_seconds){
		this.acc_seconds = acc_seconds;
	}
	
	public int getAcc_seconds(){
		return this.acc_seconds;
	}
	
	public void setSeconds(int seconds){
		this.seconds = seconds;
	}
	
	public int getSeconds(){
		return this.seconds;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	
}
