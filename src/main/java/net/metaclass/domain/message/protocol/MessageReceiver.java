package net.metaclass.domain.message.protocol;

public interface MessageReceiver<T> {

    MessageWrapper<T> receive(WebSocketFrameWrapper webSocketFrameWrapper);

}

