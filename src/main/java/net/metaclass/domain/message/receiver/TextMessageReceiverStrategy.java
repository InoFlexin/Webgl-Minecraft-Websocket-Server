package net.metaclass.domain.message.receiver;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.metaclass.domain.message.TextMessage;
import net.metaclass.domain.message.protocol.*;

public class TextMessageReceiverStrategy extends AbstractMessageReceiver<TextMessage> {

    @Override
    public MessageWrapper<TextMessage> receive(WebSocketFrameWrapper webSocketFrameWrapper) {
        String text = ((TextWebSocketFrame) webSocketFrameWrapper.getWebSocketFrame()).text();
        Message<TextMessage> textMessage = new TextMessage().parse(text);
        MessageWrapper<TextMessage> messageWrapper =
                new MessageWrapper<>(textMessage, webSocketFrameWrapper.getChannel());

        return messageWrapper;
    }

}
