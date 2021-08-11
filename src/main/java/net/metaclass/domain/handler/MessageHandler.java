package net.metaclass.domain.handler;

import net.metaclass.domain.broadcast.MessageBroadcaster;
import net.metaclass.domain.message.protocol.MessageWrapper;

public class MessageHandler<T> {

    private MessageBroadcaster messageBroadcaster;

    public MessageHandler(MessageBroadcaster messageBroadcaster) {
        this.messageBroadcaster = messageBroadcaster;
    }

    public void handleMessage(MessageWrapper<T> wrapper) {
        messageBroadcaster.broadcast(wrapper);
    }

}
