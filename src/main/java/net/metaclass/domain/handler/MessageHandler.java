package net.metaclass.domain.handler;

import net.metaclass.domain.message.protocol.MessageWrapper;

public interface MessageHandler<T> {

    void handleMessage(MessageWrapper<T> wrapper);

}
