package com.agent.provider.util;

import java.util.UUID;

/**
 * MD5工具类
 *
 * @author pibigstar
 */
public class UUIDUtil {

    /**
     * 生成md5
     *
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}


