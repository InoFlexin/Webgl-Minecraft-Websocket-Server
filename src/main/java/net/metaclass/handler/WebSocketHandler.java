package net.metaclass.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.*;
import net.metaclass.domain.message.exeception.UnsupportedWebSocketFrameException;
import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.message.protocol.WebSocketFrameWrapper;
import net.metaclass.domain.message.receiver.MessageReceiverFactory;
import net.metaclass.domain.message.protocol.MessageReceiver;

public class WebSocketHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof WebSocketFrame) {
            try {
                WebSocketFrame webSocketFrame = (WebSocketFrame) msg;
                MessageReceiver receiver = MessageReceiverFactory.getFactory().getReceiver(webSocketFrame);
                WebSocketFrameWrapper webSocketFrameWrapper = new WebSocketFrameWrapper(ctx.channel(), webSocketFrame);
                MessageWrapper dto = receiver.receive(webSocketFrameWrapper);
            } catch (UnsupportedWebSocketFrameException e) {
                e.printStackTrace();
                // TODO: 에러 처리
            }
        }
    }
}
