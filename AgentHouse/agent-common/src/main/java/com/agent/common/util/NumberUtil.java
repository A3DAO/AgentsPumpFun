package com.agent.common.util;

import lombok.experimental.UtilityClass;

/**
 * 数字工具类
 *
 * @author lll
 */
@UtilityClass
public class NumberUtil extends cn.hutool.core.util.NumberUtil {

    /**
     * 验证limit 参数
     *
     * @param limit
     * @return
     */
    public Long validLimit(Long limit) {
        return validLimit(limit, 100L, 500L);
    }

    /**
     * 验证limit参数
     *
     * @param limit
     * @param defaultLimit
     * @param maxLimit
     * @return
     */
    public Long validLimit(Long limit, Long defaultLimit, Long maxLimit) {
        return (limit == null || limit <= 0) ? defaultLimit : (limit > maxLimit ? maxLimit : limit);
    }
}
