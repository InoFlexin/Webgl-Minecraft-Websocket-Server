package net.metaclass.domain.message.receiver;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import net.metaclass.domain.message.CloseMessage;
import net.metaclass.domain.message.exeception.UnsupportedWebSocketFrameException;
import net.metaclass.domain.message.protocol.MessageReceiver;

public class CloseMessageReceiverStrategy implements MessageReceiver<CloseMessage> {

    @Override
    public CloseMessage receive(WebSocketFrame webSocketFrame) throws UnsupportedWebSocketFrameException {
        return null;
    }
}
