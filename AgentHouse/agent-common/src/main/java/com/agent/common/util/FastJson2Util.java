package com.agent.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.alibaba.fastjson2.util.ParameterizedTypeImpl;

import lombok.experimental.UtilityClass;

/**
 * FastJson2 utils
 *
 * @author lll
 */
@UtilityClass
public class FastJson2Util {

    /**
     * Object 转 JSONStr
     */
    public static String toJSONStr(Object value) {
        return JSON.toJSONString(value);
    }

    /**
     * Json 转 Object
     */
    public static <T> T toObject(String content, Class<T> clazz) {
        return JSON.parseObject(content, clazz);
    }

    /**
     * Json 转 Object
     */
    public static <T> T toObject(String content, TypeReference<T> typeReference) {
        return JSON.parseObject(content, typeReference);
    }

    /**
     * Json 转 List
     */
    public static <E> List<E> toList(String content, Class<E> clazz) {
        return JSON.parseObject(content, makeJavaType(List.class, clazz));
    }

    /**
     * Json 转 Map
     */
    public static <V> Map<String, V> toMap(String content, Class<V> clazz) {
        return JSON.parseObject(content, makeJavaType(Map.class, String.class, clazz));
    }

    private static ParameterizedType makeJavaType(Type rawType, Type... typeArguments) {
        return new ParameterizedTypeImpl(typeArguments, null, rawType);
    }
}
