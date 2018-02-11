package cn.com.dubbo.model;

import java.util.List;

/**
 * 返回错误结果
 * 
 * @author 常胜
 * @email 271500238@qq.com
 * @date 2016-7-19 下午8:00:44
 */
public class BindResult {

	private List<String> error;

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}

}
