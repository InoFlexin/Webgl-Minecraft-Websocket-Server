package net.metaclass.domain.message.receiver;

import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import net.metaclass.domain.message.CloseMessage;
import net.metaclass.domain.message.protocol.*;

public class CloseMessageReceiverStrategy extends AbstractMessageReceiver<CloseMessage> {

    @Override
    public MessageWrapper<CloseMessage> receive(WebSocketFrameWrapper webSocketFrameWrapper) {
        CloseWebSocketFrame closeWebSocketFrame = (CloseWebSocketFrame) webSocketFrameWrapper.getWebSocketFrame();
        Message<CloseMessage> message = new CloseMessage(closeWebSocketFrame.reasonText(), closeWebSocketFrame.statusCode());
        MessageWrapper<CloseMessage> messageWrapper =
                new MessageWrapper<>(message, webSocketFrameWrapper.getChannel());

        return messageWrapper;
    }
}
