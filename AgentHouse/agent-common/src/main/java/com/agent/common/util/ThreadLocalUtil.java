package com.agent.common.util;

import lombok.experimental.Accessors;

/**
 * 线程变量
 *
 * @author lll
 */
@Accessors(chain = true)
public class ThreadLocalUtil {

    public static final String LOOKUP_KEY_DEFAULT = "default";

    /**
     * 查询数据库缓存
     */
    private static ThreadLocal<String> lookupKey = new ThreadLocal<>();

    public static String getLookupKey() {
        return lookupKey.get();
    }

    public static void setLookupKey(String key) {
        lookupKey.set(key);
    }

    public static void removeLookupKey() {
        lookupKey.remove();
    }

}
