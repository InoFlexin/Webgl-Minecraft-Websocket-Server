package net.metaclass.domain.message.receiver;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import net.metaclass.domain.message.TextMessage;
import net.metaclass.domain.message.exeception.UnsupportedWebSocketFrameException;
import net.metaclass.domain.message.protocol.Message;
import net.metaclass.domain.message.protocol.MessageReceiver;

public class TextMessageReceiverStrategy implements MessageReceiver<TextMessage> {

    @Override
    public TextMessage receive(WebSocketFrame webSocketFrame) throws UnsupportedWebSocketFrameException {
        if(!(webSocketFrame instanceof TextWebSocketFrame)) {
            throw new UnsupportedWebSocketFrameException(webSocketFrame);
        }

        String text = ((TextWebSocketFrame) webSocketFrame).text();
        Message<TextMessage> textMessage = new TextMessage().parse(text);

        return textMessage.get();
    }

}
