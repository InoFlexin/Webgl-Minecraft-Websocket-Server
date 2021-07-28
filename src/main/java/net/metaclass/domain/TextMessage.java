package net.metaclass.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class TextMessage implements Message<TextMessage> {

    private String event;
    private String sender;
    private String className;
    private Map<String, Object> data;

    public TextMessage(String event, String sender, String className, Map<String, Object> data) {
        this.event = event;
        this.sender = sender;
        this.className = className;
        this.data = data;
    }

    @Override
    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }

    @Override
    public TextMessage parse() {

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
