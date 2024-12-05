package com.agent.common.util;

import com.agent.common.exception.BizException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;


/**
 * BigDecimal工具类
 *
 * @author lll
 */
@UtilityClass
public class BigDecimalUtil {

    public BigDecimal getBigDecimal(BigDecimal val, int scale) {
        return val == null || val.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO
                : val.setScale(scale, BigDecimal.ROUND_DOWN);
    }

    public BigDecimal getBigDecimal(String val, int scale) {
        return val == null ? BigDecimal.ZERO : getBigDecimal(new BigDecimal(val), scale);
    }

    public String getString(BigDecimal val, int scale) {
        return getBigDecimal(val, scale).toPlainString();
    }

    public String getString(BigDecimal val) {
        return val == null ? BigDecimal.ZERO.toString() : val.toPlainString();
    }

    public String getString(String val, int scale) {
        return getBigDecimal(val, scale).toPlainString();
    }

    public BigDecimal getBigDecimal(String val) {
        return StringUtils.isEmpty(val) ? BigDecimal.ZERO : new BigDecimal(val);
    }

    public BigDecimal getMin(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2) < 0 ? bd1 : bd2;
    }

    public BigDecimal getMax(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2) > 0 ? bd1 : bd2;
    }

    public BigDecimal divide(BigDecimal bd1, BigDecimal bd2) {
        return divide(bd1, bd2, 8);
    }

    public BigDecimal divide(BigDecimal bd1, BigDecimal bd2, int scale) {
        if (bd2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        } else {
            return bd1.divide(bd2, scale, BigDecimal.ROUND_DOWN);
        }
    }

    public BigDecimal defaultIfNull(BigDecimal bd) {
        return defaultIfNull(bd, BigDecimal.ZERO);
    }

    public BigDecimal defaultIfNull(BigDecimal bd, BigDecimal defaultValue) {
        return Objects.isNull(bd) ? defaultValue : bd;
    }

    public BigDecimal bigIntegerToDecimal(BigInteger val, int nativeDecimal, int reservedDecimal) {
        return new BigDecimal(val).movePointLeft(nativeDecimal).setScale(reservedDecimal, BigDecimal.ROUND_DOWN);
    }


}
