package net.metaclass.domain.broadcast;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.metaclass.domain.broadcast.middleware.BroadcasterMiddleware;
import net.metaclass.domain.message.TextMessage;

import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.session.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageBroadcaster {

    private SessionContext sessionContext;
    private Set<BroadcasterMiddleware> middlewareSet;
    private Logger logger = LoggerFactory.getLogger(MessageBroadcaster.class);

    public MessageBroadcaster(SessionContext sessionContext, BroadcasterMiddleware... middlewares) {
        this.sessionContext = sessionContext;
        this.middlewareSet = new HashSet<>();

        initialize(middlewares);
    }

    private void initialize(BroadcasterMiddleware[] middlewares) {
        for(BroadcasterMiddleware middleware : middlewares) {
            middlewareSet.add(middleware);
        }
    }

    private void runMiddleWares(MessageWrapper messageWrapper) {
        for(BroadcasterMiddleware middleware : middlewareSet) {
            middleware.bridge(messageWrapper, sessionContext);
        }
    }

    public void broadcast(MessageWrapper messageWrapper) {
        runMiddleWares(messageWrapper);

        if(messageWrapper.getMessage() instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) messageWrapper.getMessage();
            String className = textMessage.getClassName();
            String json = textMessage.toJson();
            List<Channel> channels = sessionContext.getChannelsInClass(className);

            broadcast(channels, json);
            logger.info("Broadcast done. class_name=", className);
        }
    }

    public void broadcast(List<Channel> channels, String data) {
        TextWebSocketFrame send = new TextWebSocketFrame(data);
        logger.info("Start broadcast in same class");
        for(Channel channel : channels) {
            channel.writeAndFlush(send);
        }
    }

}
