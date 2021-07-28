package net.metaclass.domain.handler;

import net.metaclass.domain.message.CloseMessage;
import net.metaclass.domain.message.TextMessage;
import net.metaclass.domain.message.protocol.Message;
import net.metaclass.domain.message.protocol.MessageWrapper;

import java.util.Optional;

public class MessageHandlerFactory {

    private static MessageHandlerFactory factory;

    static public synchronized MessageHandlerFactory getFactory() {
        if(factory == null) {
            factory = new MessageHandlerFactory();
        }
        return factory;
    }

    private MessageHandlerFactory() {}

    public Optional<MessageHandler> getHandler(MessageWrapper wrapper) {
        Message message = wrapper.getMessage();

        if(message instanceof TextMessage) {
            return Optional.of(new TextMessageHandler());
        }
        else if(message instanceof CloseMessage) {
            return Optional.of(new CloseMessageHandler());
        }

        return Optional.empty();
    }

}
