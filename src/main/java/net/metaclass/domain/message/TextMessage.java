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

    @Override
    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }

    @Override
    public TextMessage parse(String json) {
        return  new GsonBuilder().create().fromJson(json, getClass());
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
