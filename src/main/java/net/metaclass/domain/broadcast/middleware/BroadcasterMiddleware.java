package net.metaclass.domain.broadcast.middleware;

import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.session.SessionContext;

public interface BroadcasterMiddleware {

    void bridge(MessageWrapper wrapper, SessionContext sessionContext);

}
