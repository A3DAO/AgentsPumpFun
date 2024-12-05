package com.agent.common.util;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import lombok.experimental.UtilityClass;

/**
 * CacheUtil
 *
 * @author lll
 */
@UtilityClass
public class CacheUtil {

    /**
     * 动态数据源支持的链
     */
    public List<String> datasourceChainList = CollectionUtil.newArrayList();

}
