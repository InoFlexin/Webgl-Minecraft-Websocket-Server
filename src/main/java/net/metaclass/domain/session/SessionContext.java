package net.metaclass.domain.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SessionContext {

    private Map<String, ChannelGroup> stringToChannel; // class_name to getting channels what channels connected
    private Map<Channel, Boolean> existsChannel;

    public SessionContext() {
        this.stringToChannel = new HashMap<>();
        this.existsChannel = new HashMap<>();
    }

    public boolean existsClass(String className) {
        return stringToChannel.containsKey(className);
    }

    public void registerClassSession(String className, Channel... channels) {
        ChannelGroup connectedChannels =
                stringToChannel.getOrDefault(className, new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));

        for(Channel channel : channels) {
            if(existsChannel(channel)) {
                continue;
            }

            connectedChannels.add(channel);
            existsChannel.put(channel, true);
        }

        stringToChannel.put(className, connectedChannels); // update sessions
    }

    public ChannelGroup getChannelsInClass(String className) {
        return stringToChannel.getOrDefault(className, new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));
    }

    public boolean existsChannel(Channel channel) {
        return existsChannel.containsKey(channel) && existsChannel.get(channel);
    }

    public void disconnect(Channel channel) {
        existsChannel.remove(channel);
    }

    @Override
    public String toString() {
        return "SessionContext{" +
                "stringToChannel=" + stringToChannel +
                ", existsChannel=" + existsChannel +
                '}';
    }

}
