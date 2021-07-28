package net.metaclass.domain.session;

import io.netty.channel.Channel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class SessionContext {

    private Map<Channel, String> channelToName;
    private Map<String, List<Channel>> stringToChannel; // class_name to getting channels what channels connected

    public SessionContext() {
        this.channelToName = new HashMap<>();
        this.stringToChannel = new HashMap<>();
    }

    public boolean existsSession(Channel channel) {
        return channelToName.containsKey(channel);
    }

    public boolean existsClass(String className) {
        return stringToChannel.containsKey(className);
    }

    public void registerUserSession(Channel channel, String className) {
        if(!existsSession(channel)) {
            channelToName.put(channel, className);
        }
    }

    public void registerClassSession(String className, Channel... channels) {
        List<Channel> connectedChannels = stringToChannel.getOrDefault(className, new ArrayList<>());

        for(Channel channel : channels) {
            connectedChannels.add(channel);
        }
        stringToChannel.put(className, connectedChannels); // update sessions
    }

    public void disconnect(Channel channel) {
        String className = channelToName.get(channel);

        if(className != null) {
            List<Channel> channels = stringToChannel.get(className);

            channels.remove(channel);
            channelToName.remove(channel);
        }
    }

    public List<Channel> getChannelsInClass(String className) {
        return stringToChannel.getOrDefault(className, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "SessionContext{" +
                "channelToName=" + channelToName +
                ", stringToChannel=" + stringToChannel +
                '}';
    }
}
