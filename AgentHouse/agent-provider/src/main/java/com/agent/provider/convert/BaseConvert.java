package com.agent.provider.convert;

import com.agent.common.util.DateUtil;
import org.mapstruct.MapperConfig;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 对象拷贝基类
 *
 * @author lll
 */
@MapperConfig
public interface BaseConvert {

    /**
     * date2Timestamp
     *
     * @param date
     * @return
     */
    @Named(value = "date2Timestamp")
    default Long date2Timestamp(Date date) {
        return DateUtil.date2Timestamp(date);
    }

    /**
     * timestamp2Date
     *
     * @param timestamp
     * @return
     */
    @Named(value = "timestamp2Date")
    default Date timestamp2Date(Long timestamp) {
        return DateUtil.timestamp2Date(timestamp);
    }

    /**
     * date2Second
     *
     * @param date
     * @return
     */
    @Named(value = "date2Second")
    default Long date2Second(Date date) {
        return DateUtil.date2Second(date);
    }

    /**
     * second2Date
     *
     * @param seconds
     * @return
     */
    @Named(value = "second2Date")
    default Date second2Date(Long seconds) {
        return DateUtil.second2Date(seconds);
    }

    /**
     * BigDecimal to String
     *
     * @param value
     * @return
     */
    @Named(value = "bigDecimalToString")
    default String bigDecimalToString(BigDecimal value) {
        if (value == null) {
            return "0";
        } else {
            return value.stripTrailingZeros().toPlainString();
        }
    }

}
