package net.metaclass.domain;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public interface TextMessageReceiver {

    TextMessage receive(WebSocketFrame webSocketFrame);

}
