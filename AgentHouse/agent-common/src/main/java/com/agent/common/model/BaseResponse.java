package com.agent.common.model;

import com.agent.common.constants.BizCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * base response
 *
 * @author lll
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 8981879586773088995L;

    private Integer code = BizCodeEnum.BIZ_SUCCESS.getCode();

    private String msg = "Correct response";

    private Long ts = System.currentTimeMillis();

    private T data;

    public static <T> BaseResponse<T> of(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> failed(Integer code, String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>();
    }

    /**
     * 是否为错误类型的响应
     *
     * @return 匹配结果
     */
    public boolean isError() {
        return !BizCodeEnum.BIZ_SUCCESS.getCode().equals(code);
    }
}
