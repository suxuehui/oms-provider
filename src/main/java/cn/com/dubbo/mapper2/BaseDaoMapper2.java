package cn.com.dubbo.mapper2;

import java.util.List;
import java.util.Map;

/**
 * 基础Mapper，需泛型支持
 * @author haohaiyang
 *
 * @param <T>
 */
public interface BaseDaoMapper2<T> {

	public List<T> findAll(Map<String,Object> param);
	
	public T findById(Map<String,Object> param);
	
	public void save(T t);
	
	public void update(T t);
}
