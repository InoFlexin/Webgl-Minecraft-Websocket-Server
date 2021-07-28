package net.metaclass.domain.message.receiver;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.metaclass.domain.message.TextMessage;
import net.metaclass.domain.message.protocol.Message;
import net.metaclass.domain.message.protocol.MessageReceiver;
import net.metaclass.domain.message.protocol.WebSocketFrameWrapper;

public class TextMessageReceiverStrategy implements MessageReceiver<TextMessage> {

    @Override
    public TextMessage receive(WebSocketFrameWrapper webSocketFrameWrapper) {
        String text = ((TextWebSocketFrame) webSocketFrameWrapper.getWebSocketFrame()).text();
        Message<TextMessage> textMessage = new TextMessage().parse(text);

        return textMessage.get();
    }

}
