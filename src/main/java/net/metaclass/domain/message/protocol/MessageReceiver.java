package net.metaclass.domain.message.protocol;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import net.metaclass.domain.message.TextMessage;
import net.metaclass.domain.message.exeception.UnsupportedWebSocketFrameException;

public interface MessageReceiver<T> {

    T receive(WebSocketFrame webSocketFrame);

}

