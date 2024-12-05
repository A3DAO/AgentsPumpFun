package com.agent.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * agent Application
 *
 * @author lll
 */
@Slf4j
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.agent.provider", "com.agent.common"})
public class Application {

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        SpringApplication.run(Application.class, args);
    }
}
