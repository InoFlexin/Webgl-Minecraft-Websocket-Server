package net.metaclass.domain.message.protocol;

import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageWrapper<T> {

    private Message<T> message;
    private Channel channel;

    @Override
    public String toString() {
        return "MessageWrapper{" +
                "message=" + message +
                ", channel=" + channel +
                '}';
    }
}
