package com.agent.provider.util.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.lmax.disruptor.TimeoutHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Callback interface to be implemented for processing events
 * as they become available in the RingBuffer.
 *
 * @author
 */
@Slf4j
public class TaskHandler implements EventHandler<MessageEvent<Runnable>>, WorkHandler<MessageEvent<Runnable>>, TimeoutHandler, LifecycleAware {

    @Override
    public void onEvent(final MessageEvent<Runnable> event, final long sequence, final boolean endOfBatch) throws Exception {
        event.getMessage().run();
        event.reset();
    }

    @Override
    public void onEvent(final MessageEvent<Runnable> event) throws Exception {
        event.getMessage().run();
        event.reset();
    }

    @Override
    public void onTimeout(final long sequence) throws Exception {
        if (log.isWarnEnabled()) {
            log.warn("Task timeout on: {}, sequence: {}.", Thread.currentThread().getName(), sequence);
        }
    }

    @Override
    public void onStart() {
        if (log.isInfoEnabled()) {
            log.info("Task handler on start: {}.", Thread.currentThread().getName());
        }
    }

    @Override
    public void onShutdown() {
        if (log.isInfoEnabled()) {
            log.info("Task handler on shutdown: {}.", Thread.currentThread().getName());
        }
    }
}
