package com.agent.provider.util;

import com.alibaba.fastjson2.JSON;
import com.lmax.disruptor.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Disruptor exception handler.
 *
 * @author lll
 */
@Slf4j
public class LogExceptionHandler<T> implements ExceptionHandler<T> {

    protected final String name;
    private final OnEventException<T> onEventException;

    public LogExceptionHandler(String name) {
        this(name, null);
    }

    public LogExceptionHandler(String name, OnEventException<T> onEventException) {
        this.name = name;
        this.onEventException = onEventException;
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        log.error("Fail to start {} disruptor", this.name, ex);
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        log.error("Fail to shutdown {} disruptor", this.name, ex);
    }

    @Override
    public void handleEventException(Throwable ex, long sequence, T event) {
        log.error("Handle {} disruptor event error, event is {}", this.name, JSON.toJSONString(event), ex);
        if (this.onEventException != null) {
            this.onEventException.onException(event, ex);
        }
    }

    public interface OnEventException<T> {

        void onException(T event, Throwable ex);
    }
}
