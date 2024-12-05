package com.agent.provider.util.disruptor;

import com.lmax.disruptor.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author
 */
@Slf4j
public class LoggingExceptionHandler implements ExceptionHandler<Object> {

    @Override
    public void handleEventException(final Throwable ex, final long sequence, final Object event) {
        if (log.isWarnEnabled()) {
            log.warn("Exception processing: {} {}, {}.", sequence, event, ex.getMessage());
        }
        ex.printStackTrace();
    }

    @Override
    public void handleOnStartException(final Throwable ex) {
        if (log.isWarnEnabled()) {
            log.warn("Exception during onStart(), {}.", ex.getMessage());
        }
        ex.printStackTrace();
    }

    @Override
    public void handleOnShutdownException(final Throwable ex) {
        if (log.isWarnEnabled()) {
            log.warn("Exception during onShutdown(), {}.", ex.getMessage());
        }
        ex.printStackTrace();
    }
}
