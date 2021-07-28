package net.metaclass.domain.message.receiver;

import net.metaclass.domain.message.CloseMessage;
import net.metaclass.domain.message.protocol.MessageReceiver;
import net.metaclass.domain.message.protocol.WebSocketFrameWrapper;

public class CloseMessageReceiverStrategy implements MessageReceiver<CloseMessage> {

    @Override
    public CloseMessage receive(WebSocketFrameWrapper webSocketFrameWrapper) {
        return null;
    }
}
