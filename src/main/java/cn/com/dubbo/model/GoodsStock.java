package cn.com.dubbo.model;

import java.io.Serializable;

public class GoodsStock implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private	String	goods_stock_id;//数据ID（与药易通保持一致）
	private	int		stock_id;//仓库ID
	private	int	goods_id;//商品ID
	private	String	batch_number;//批号
	private String	batch_stock;//批号库存，药易通库存
	private	String	occupancy_stock;//占用库存
	private	String	available_stock;//已审核订单占用库存
	public String getGoods_stock_id() {
		return goods_stock_id;
	}
	public void setGoods_stock_id(String goods_stock_id) {
		this.goods_stock_id = goods_stock_id;
	}
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getBatch_number() {
		return batch_number;
	}
	public void setBatch_number(String batch_number) {
		this.batch_number = batch_number;
	}
	public String getBatch_stock() {
		return batch_stock;
	}
	public void setBatch_stock(String batch_stock) {
		this.batch_stock = batch_stock;
	}
	public String getOccupancy_stock() {
		return occupancy_stock;
	}
	public void setOccupancy_stock(String occupancy_stock) {
		this.occupancy_stock = occupancy_stock;
	}
	public String getAvailable_stock() {
		return available_stock;
	}
	public void setAvailable_stock(String available_stock) {
		this.available_stock = available_stock;
	}
	
}
