package com.agent.common.model;

import com.agent.common.constants.RedisOpEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Redis 操作（增/删/改）
 *
 * @author lll
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisOperation {

    /**
     * 数据类型
     */
    private RedisOpEnum redisOp;

    /**
     * 是否删除操作
     */
    private Boolean deleted;

    /**
     * redis key
     */
    private String redisKey;

    /**
     * hash类型中的key
     */
    private String hashKey;

    /**
     * value
     */
    private Object value;

}
