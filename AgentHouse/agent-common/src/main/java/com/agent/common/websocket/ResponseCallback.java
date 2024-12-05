package com.agent.common.websocket;

public interface ResponseCallback {

    void onOpen();

    void onMessage(String message);

    void onFailure(Throwable cause);

    void onClosed(int code, String reason);

}
