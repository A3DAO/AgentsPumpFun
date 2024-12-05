package com.agent.provider.interceptor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 系统状态拦截器, 拦截所有请求
 *
 * @author lll
 **/
@Slf4j
@Data
public class SystemStatusInterceptor implements HandlerInterceptor {

    // @Autowired
    //private ClientRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /*
        String status = redisService.getSystemStatus();
        if (status != null && !StringUtils.equals(ZERO_STR, status)) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_SYSTEM_MAINTENANCE);
        }
        */

        return true;
    }

}
