package net.metaclass.domain.message.protocol;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public abstract class AbstractMessageReceiver<T> implements MessageReceiver<T> {

    public MessageWrapper<T> receive(Channel channel, WebSocketFrame webSocketFrame) {
        return receive(new WebSocketFrameWrapper(channel, webSocketFrame));
    }

}
