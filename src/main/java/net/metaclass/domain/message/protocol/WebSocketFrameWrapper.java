package net.metaclass.domain.message.protocol;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WebSocketFrameWrapper {

    private Channel channel;
    private WebSocketFrame webSocketFrame;

    @Override
    public String toString() {
        return "WebSocketFrameWrapper{" +
                "channel=" + channel +
                ", webSocketFrame=" + webSocketFrame +
                '}';
    }
}
