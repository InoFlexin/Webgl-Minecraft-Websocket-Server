package net.metaclass.domain.broadcast.middleware;

import net.metaclass.domain.message.CloseMessage;
import net.metaclass.domain.message.TextMessage;
import net.metaclass.domain.message.protocol.Message;
import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.session.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionMiddlewareImpl implements BroadcasterMiddleware {

    private static Logger logger = LoggerFactory.getLogger(SessionMiddlewareImpl.class);

    @Override
    public void bridge(MessageWrapper wrapper, SessionContext sessionContext) {
        Message message = wrapper.getMessage();

        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            sessionContext.registerClassSession(textMessage.getClassName(), wrapper.getChannel());
        }
        else if(message instanceof CloseMessage) {
            sessionContext.disconnect(wrapper.getChannel());
        }
    }

}
