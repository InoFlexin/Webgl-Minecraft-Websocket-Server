package net.metaclass.domain.message.exeception;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class UnsupportedWebSocketFrameException extends RuntimeException {

    public UnsupportedWebSocketFrameException(WebSocketFrame webSocketFrame) {
        super("Invalid received WebSocketFrame, Only support frame is TextWebSocketFrame, CloseWebSocketFrame \n" +
                " Current received WebSocketFrame: " + webSocketFrame.getClass().getName());
    }

}
