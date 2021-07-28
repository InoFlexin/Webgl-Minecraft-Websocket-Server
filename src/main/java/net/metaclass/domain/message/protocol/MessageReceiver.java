package net.metaclass.domain.message.protocol;

public interface MessageReceiver<T> {

    T receive(WebSocketFrameWrapper webSocketFrameWrapper);

}

