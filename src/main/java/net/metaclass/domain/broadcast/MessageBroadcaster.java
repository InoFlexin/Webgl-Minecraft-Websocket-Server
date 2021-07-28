package net.metaclass.domain.broadcast;

import io.netty.channel.Channel;
import net.metaclass.domain.broadcast.middleware.BroadcasterMiddleware;
import net.metaclass.domain.message.TextMessage;

import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.session.SessionContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageBroadcaster {

    private SessionContext sessionContext;
    private Set<BroadcasterMiddleware> middlewareSet;

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
        }
    }

    public void broadcast(List<Channel> channels, String data) {
        for(Channel channel : channels) {
            channel.write(data);
        }
    }

}
