package com.agent.provider.config.dds;

import cn.hutool.core.map.MapUtil;
import com.agent.common.constants.BizCodeEnum;
import com.agent.common.exception.BizException;
import com.agent.common.util.CacheUtil;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

import static com.agent.common.util.ThreadLocalUtil.LOOKUP_KEY_DEFAULT;

/**
 * 配置动态数据源
 *
 * @author lll
 */
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "agent")
public class DynamicDataSourceConfig {

    @Autowired
    private DruidDataSourceConfig druidConfig;

    private Map<String, DataSourceConfig> dynamicDataSource = MapUtil.newHashMap();

    @Bean
    public DynamicDataSource dynamicDataSource() {
        // 组装数据源
        Map<Object, Object> targetDataSources = MapUtil.newHashMap();
        for (Map.Entry<String, DataSourceConfig> entry : dynamicDataSource.entrySet()) {
            targetDataSources.put(entry.getKey(), buildDataSource(entry.getValue()));
        }

        // 设置默认数据源
        Object defaultDataSource = targetDataSources.get(LOOKUP_KEY_DEFAULT);
        if (Objects.isNull(defaultDataSource)) {
            log.error("未找到默认的数据库配置.");
            throw new BizException(BizCodeEnum.SERVER_ERROR_NO_DEFAULT_DATASOURCE);
        }

        // 获取支持链
        CacheUtil.datasourceChainList.addAll(dynamicDataSource.keySet());
        CacheUtil.datasourceChainList.remove(LOOKUP_KEY_DEFAULT);

        return new DynamicDataSource(defaultDataSource, targetDataSources);
    }

    /**
     * 根据配置构建数据源
     */
    private DruidDataSource buildDataSource(DataSourceConfig config) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(config.getUrl());
        druidDataSource.setUsername(config.getUsername());
        druidDataSource.setPassword(config.getPassword());
        druidDataSource.setMaxWait(druidConfig.getMaxWait());
        druidDataSource.setTimeBetweenConnectErrorMillis(druidConfig.getTimeBetweenConnectErrorMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidConfig.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidConfig.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidConfig.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidConfig.getTestOnBorrow());
        druidDataSource.setTestOnReturn(druidConfig.getTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidConfig.getPoolPreparedStatements());
        druidDataSource
                .setMaxPoolPreparedStatementPerConnectionSize(druidConfig.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setConnectionInitSqls(Lists.newArrayList(druidConfig.getConnectionInitSqls()));
        druidDataSource.setConnectionErrorRetryAttempts(druidConfig.getConnectionErrorRetryAttempts());
        druidDataSource.setBreakAfterAcquireFailure(druidConfig.getBreakAfterAcquireFailure());
        druidDataSource.setAsyncInit(druidConfig.getAsyncInit());
        return druidDataSource;
    }

    @Data
    public static class DataSourceConfig {
        private String url;
        private String username;
        private String password;
    }

}
