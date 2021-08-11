package net.metaclass.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.*;
import net.metaclass.domain.broadcast.MessageBroadcaster;
import net.metaclass.domain.broadcast.middleware.SessionMiddlewareImpl;
import net.metaclass.domain.handler.MessageHandler;
import net.metaclass.domain.message.exeception.UnsupportedWebSocketFrameException;
import net.metaclass.domain.message.protocol.AbstractMessageReceiver;
import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.message.protocol.WebSocketFrameWrapper;
import net.metaclass.domain.message.receiver.MessageReceiverFactory;
import net.metaclass.domain.message.protocol.MessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketHandler extends ChannelInboundHandlerAdapter {

    private MessageBroadcaster messageBroadcaster;
    private MessageHandler messageHandler;

    public WebSocketHandler() {
        this.messageBroadcaster = new MessageBroadcaster(new SessionMiddlewareImpl());
        this.messageHandler = new MessageHandler(messageBroadcaster);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof WebSocketFrame) {
            try {
                WebSocketFrame webSocketFrame = (WebSocketFrame) msg;
                AbstractMessageReceiver receiver = MessageReceiverFactory.getFactory().getReceiver(webSocketFrame);
                MessageWrapper messageWrapper = receiver.receive(ctx.channel(), webSocketFrame);

                messageHandler.handleMessage(messageWrapper);
            } catch (UnsupportedWebSocketFrameException e) {
                e.printStackTrace();
                // TODO: 에러 처리
            }
        }
    }
}
