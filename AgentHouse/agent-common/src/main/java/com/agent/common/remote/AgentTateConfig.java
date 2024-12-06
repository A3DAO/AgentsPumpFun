package com.agent.common.remote;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.querymap.BeanQueryMapEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "network.agent.tate")
public class AgentTateConfig {

    private String apiUrl;

    @Bean
    public com.agent.common.remote.AgentTateApi agentTateApi() {
        return Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).client(new OkHttpClient())
            .retryer(new Retryer.Default(100L, 1000L, 1)).logger(new Slf4jLogger()).logLevel(Logger.Level.FULL)
            .queryMapEncoder(new BeanQueryMapEncoder()).target(com.agent.common.remote.AgentTateApi.class, apiUrl);
    }

}
