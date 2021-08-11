package net.metaclass.domain.message.receiver;

import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import net.metaclass.domain.message.exeception.UnsupportedWebSocketFrameException;
import net.metaclass.domain.message.protocol.MessageReceiver;

public class MessageReceiverFactory {

    private static MessageReceiverFactory factory;

    public static synchronized MessageReceiverFactory getFactory() {
        if(factory == null) {
            factory = new MessageReceiverFactory();
        }
        return factory;
    }
    private MessageReceiverFactory() {}

    public <T> MessageReceiver getReceiver(WebSocketFrame webSocketFrame) throws UnsupportedWebSocketFrameException {
        if(webSocketFrame instanceof TextWebSocketFrame) {
            return new TextMessageReceiverStrategy();
        }
        else if(webSocketFrame instanceof CloseWebSocketFrame) {
            return new CloseMessageReceiverStrategy();
        }
        else {
            throw new UnsupportedWebSocketFrameException(webSocketFrame);
        }
    }

}
