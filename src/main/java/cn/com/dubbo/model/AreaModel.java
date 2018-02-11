package cn.com.dubbo.model;

import java.io.Serializable;

/**
 * 地区查询
 */
public class AreaModel implements Serializable {
  
	private static final long serialVersionUID = 591193130278760068L;

	private int areaId;

    private int parentId;

    private String areaName;
    
    private int sort;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}