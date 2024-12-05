package com.agent.provider.util.disruptor;

/**
 * message Event
 *
 * @author
 */
public class MessageEvent<T> {

    private T message;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public void reset() {
        this.message = null;
    }
}
