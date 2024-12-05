package com.agent.provider.config.dds;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.agent.common.util.ThreadLocalUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态切换数据库的数据源，
 *
 * @author lll
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 配置多个链数据源的信息以及默认数据源
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       目标数据源
     */
    public DynamicDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalUtil.getLookupKey();
    }

}
