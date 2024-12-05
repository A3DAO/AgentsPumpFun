package com.agent.provider.util.disruptor;

import java.util.concurrent.Executor;

/**
 * Task message dispatcher.
 *
 * @author
 */
public interface Dispatcher<T> extends Executor {

    /**
     * Dispatch a task message.
     *
     * @param message
     * @return
     */
    boolean dispatch(final T message);

    /**
     * Shutdown
     */
    void shutdown();
}
