package com.agent.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 系统启动完成后启动netty服务
 *
 * @author lll
 */
@Slf4j
@Component
public class WebSocketRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
