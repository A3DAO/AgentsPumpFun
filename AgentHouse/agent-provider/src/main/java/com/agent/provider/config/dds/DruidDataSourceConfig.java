package com.agent.provider.config.dds;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源连接配置
 *
 * @author lll
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceConfig {
    private Long maxWait;
    private Long timeBetweenConnectErrorMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private Boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String connectionInitSqls;
    private Integer connectionErrorRetryAttempts;
    private Boolean breakAfterAcquireFailure;
    private Boolean asyncInit;

}
