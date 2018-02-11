package cn.com.dubbo.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName: MultipleDataSource 
 * @Description: 数据源切换工具类:dataSource为application-jdbc.xml中的数据源ID
 * @author Lichen.Zheng
 * @date 2016-8-23 下午4:19:54 
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
