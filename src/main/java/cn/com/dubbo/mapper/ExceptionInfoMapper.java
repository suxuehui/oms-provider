package cn.com.dubbo.mapper;

import org.springframework.stereotype.Repository;

import cn.com.dubbo.model.Employee;
import cn.com.dubbo.model.ExceptionInfo;

@Repository  
//@Transactional
public interface ExceptionInfoMapper extends BaseDaoMapper<ExceptionInfo> {
	public void insertExceptionInfo(ExceptionInfo exceptionInfo);
}