package net.metaclass.domain.message.receiver;

import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import net.metaclass.domain.message.CloseMessage;
import net.metaclass.domain.message.protocol.Message;
import net.metaclass.domain.message.protocol.MessageReceiver;
import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.message.protocol.WebSocketFrameWrapper;

public class CloseMessageReceiverStrategy implements MessageReceiver<CloseMessage> {

    @Override
    public MessageWrapper<CloseMessage> receive(WebSocketFrameWrapper webSocketFrameWrapper) {
        CloseWebSocketFrame closeWebSocketFrame = (CloseWebSocketFrame) webSocketFrameWrapper.getWebSocketFrame();
        Message<CloseMessage> message = new CloseMessage(closeWebSocketFrame.reasonText(), closeWebSocketFrame.statusCode());
        MessageWrapper<CloseMessage> messageWrapper =
                new MessageWrapper<>(message, webSocketFrameWrapper.getChannel());

        return messageWrapper;
    }
}
