package com.agent.common.exception;

import com.agent.common.constants.BizCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author lll
 */
@Setter
@Getter
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 8770620175959486163L;

    /**
     * 错误代码
     */
    private Integer code;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
        this.code = BizCodeEnum.BIZ_ERROR.getCode();
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(BizCodeEnum bizCode) {
        super(bizCode.getMessage());
        this.code = bizCode.getCode();
    }

    public BizException(BizCodeEnum bizCode, String message) {
        super(String.format(bizCode.getMessage(), message));
        this.code = bizCode.getCode();
    }

    public BizException(Throwable cause) {
        super(cause);
        this.code = BizCodeEnum.BIZ_ERROR.getCode();
    }

    public static BizException inst(BizCodeEnum bizCode) {
        return new BizException(bizCode);
    }

    public static BizException inst(BizCodeEnum bizCode, String message) {
        return new BizException(bizCode, message);
    }
}
