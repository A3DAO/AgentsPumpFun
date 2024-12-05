package com.agent.common.exception;

import com.agent.common.constants.BaseConst;
import com.agent.common.constants.BizCodeEnum;
import com.agent.common.constants.HintConst;
import com.agent.common.model.BaseResponse;
import com.agent.common.util.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.agent.common.constants.BaseConst.DOT_STR;

/**
 * 统一异常拦截
 *
 * @author lll
 */
@ConditionalOnProperty(name = "controller.advice.on", havingValue = "true", matchIfMissing = true)
@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    public static final String NOT_EMPTY = "不能为空";
    public static final String NOT_NULL = "不能为null";

    /**
     * 响应异常统一处理器
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public BaseResponse validBizExceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                                 BizException bizException) {
        log.error("Advice handle 业务类型异常:{}", bizException.getMessage());
        return ResponseHelper.error(bizException);
    }

    /**
     * 全局未捕获异常处理器
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleError(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("Advice handle 全局未捕获异常", exception);
        return ResponseHelper.error(BizCodeEnum.BIZ_ERROR);
    }

    /**
     * 数据库异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public BaseResponse databaseExceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                                 SQLException e) {
        log.error("Advice handle 数据库异常", e);
        return ResponseHelper.error(BizCodeEnum.SERVER_ERROR_DATABASE);
    }

    /**
     * 数据库数据存取异常
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public BaseResponse databaseDataAccessExceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                                           DataAccessException ex) {
        log.error("Advice handle 数据库访问异常", ex);
        return ResponseHelper.error(BizCodeEnum.SERVER_ERROR_DATABASE);
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseBody
    public Object methodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response,
                                                  Exception ex) {
        log.error("Advice handle 接口参数验证异常 {}", ex.getMessage());
        BaseResponse result = ResponseHelper.error(BizCodeEnum.BIZ_ERROR_PARAM_INVALID);
        List<FieldError> fieldErrors;
        Map<String, String> error = new HashMap<>(16);
        if (ex instanceof BindException) {
            fieldErrors = ((BindException) ex).getBindingResult().getFieldErrors();
            fieldErrors.forEach(x -> {
                String value = error.get(x.getField());
                if (value != null) {
                    error.put(x.getField(), value);
                } else {
                    error.put(x.getField(), x.getDefaultMessage());
                }
            });
        } else if (ex instanceof ConstraintViolationException) {
            ((ConstraintViolationException) ex).getConstraintViolations().forEach(x -> {
                String value = error.get(x.getPropertyPath().toString());
                if (value != null) {
                    error.put(x.getPropertyPath().toString(), value);
                } else {
                    String message = x.getMessage();
                    // 错误提示加字段名
                    if (StringUtils.isNotBlank(message) && StringUtils.equalsAny(message, NOT_EMPTY, NOT_NULL)) {
                        message =
                                StringUtils.substringAfterLast(x.getPropertyPath().toString(), DOT_STR) + HintConst.LACK;
                    }
                    error.put(x.getPropertyPath().toString(), message);
                }
            });
        }
        result.setMsg(String.join(BaseConst.SEMICOLON_STR, error.values()));
        return result;
    }
}
