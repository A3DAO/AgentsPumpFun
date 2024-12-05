package com.agent.common.constants;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务码枚举
 *
 * <pre>
 * 响应码新增原则:
 * 1. 在当前响应码不能满足用户提示需求，考虑新增
 * 2. 如果大类能够满足前端的错误处理需求，请勿新增
 * 3. 在不能明确响应的错误分类再考虑新增分类
 * </pre>
 *
 * @author lll
 */
@Getter
@Slf4j
public enum BizCodeEnum {

    /**
     * 业务正确响应
     */
    BIZ_SUCCESS(0, "BUSINESS_SUBMITTED_SUCCESSFULLY"),

    /**
     * 业务相关异常
     */
    BIZ_ERROR_EXCEPTION_REQUEST(5000, "EXCEPTION_REQUEST"), BIZ_ERROR_NOT_FOUND_ORDER(5001, "NOT_FOUND_ORDER"), BIZ_ERROR(5002, "business error"), SERVER_ERROR_DATABASE(5003, "database exception"), BIZ_ERROR_PARAM_INVALID(5004, "param invalid"),
    SERVER_ERROR_NO_DEFAULT_DATASOURCE(5005, "no default datasource"), BIZ_ERROR_EXISTING_USER(5006, "existing user"), BIZ_ERROR_NOT_EXISTING_USER(5007, "not existing user"),
    BIZ_ERROR_EXISTING_AGENT(5008, "existing agent"), BIZ_ERROR_NOT_EXISTING_AGENT(5009, "not existing agent"), BIZ_ERROR_EMPTY_CHARACTER(5010, "empty character"),
    BIZ_ERROR_EMPTY_MODEL(5011, "empty model"),
    /**
     * 置底
     */
    BIZ_BLANK_RSP(999999, "置底");

    private Integer code;
    private String message;

    BizCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取枚举
     *
     * @param code 错误码
     * @return 错误枚举
     */
    public static BizCodeEnum getEnumByCode(Integer code) {
        BizCodeEnum result = null;
        for (BizCodeEnum bizCodeEnum : BizCodeEnum.values()) {
            if (bizCodeEnum.code.equals(code)) {
                result = bizCodeEnum;
                break;
            }
        }
        return result;
    }

}
