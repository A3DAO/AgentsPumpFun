package com.agent.common.websocket;

import lombok.Getter;
import okhttp3.*;

/**
 * websocket service
 *
 * @author lll
 */
public class WebSocketService extends WebSocketListener {

    @Getter
    private WebSocket webSocket;

    private ResponseCallback callback;

    public WebSocket createWebSocket(String url, OkHttpClient httpClient, ResponseCallback callback) {
        this.callback = callback;
        Request request = new Request.Builder().url(url).build();
        this.webSocket = httpClient.newWebSocket(request, this);
        return this.webSocket;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        callback.onOpen();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        callback.onMessage(text);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
        callback.onFailure(throwable);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        callback.onClosed(code, reason);
    }

}
