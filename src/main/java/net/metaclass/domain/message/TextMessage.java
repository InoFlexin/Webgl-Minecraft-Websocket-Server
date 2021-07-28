package net.metaclass.domain.message;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.metaclass.domain.message.protocol.Message;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextMessage implements Message<TextMessage> {

    private String event;
    private String sender;
    private String className;
    private Map<String, Object> data;

    public void copy(TextMessage message) {
        this.event = message.getEvent();
        this.sender = message.getSender();
        this.className = message.getClassName();
        this.data = message.getData();
    }

    @Override
    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }

    @Override
    public TextMessage parse(String json) {
        TextMessage textMessage = new GsonBuilder().create().fromJson(json, getClass());
        copy(textMessage);

        return this;
    }

    @Override
    public TextMessage get() {
        return this;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "event='" + event + '\'' +
                ", sender='" + sender + '\'' +
                ", className='" + className + '\'' +
                ", data=" + data +
                '}';
    }
}
