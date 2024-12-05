package com.agent.common.util;

import com.agent.common.constants.BizCodeEnum;
import com.agent.common.exception.BizException;
import com.agent.common.model.BaseResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * 响应工具类
 *
 * @author lll
 */
@UtilityClass
@Slf4j
public class ResponseHelper {

    /**
     * 生成基础的成功响应
     *
     * @return 数据为空的成功响应
     */
    public <T> BaseResponse<T> success() {
        return new BaseResponse<>();
    }

    /**
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 带数据的响应
     */
    public <T> BaseResponse<T> success(T data) {
        val response = new BaseResponse<T>();
        response.setData(data);
        return response;
    }

    /**
     * @param code    错误码
     * @param message 错误描述
     * @return 错误响应
     */
    public BaseResponse error(Integer code, String message) {
        val response = new BaseResponse<>();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    /**
     * 根据错误枚举生成错误响应
     *
     * @param bizCode 错误枚举
     * @return 响应
     */
    public BaseResponse error(BizCodeEnum bizCode) {
        return error(bizCode.getCode(), bizCode.getMessage());
    }

    /**
     * 根据错误异常生成错误响应
     *
     * @param bizException 异常
     * @return 响应
     */
    public BaseResponse error(BizException bizException) {
        return error(bizException.getCode(), bizException.getMessage());
    }

    /**
     * 处理基础响应,非0响应抛出异常，否则返回包装数据
     */
    public <T> T handleBaseResponse(BaseResponse<T> baseResponse) {
        if (0 != baseResponse.getCode()) {
            throw new BizException(baseResponse.getCode(), baseResponse.getMsg());
        }
        return baseResponse.getData();
    }
}
