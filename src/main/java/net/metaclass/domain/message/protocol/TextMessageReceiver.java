package net.metaclass.domain.message.protocol;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import net.metaclass.domain.message.TextMessage;

public interface TextMessageReceiver {

    TextMessage receive(WebSocketFrame webSocketFrame);

}

