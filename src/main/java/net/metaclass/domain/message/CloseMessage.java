package net.metaclass.domain.message;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.metaclass.domain.message.protocol.Message;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CloseMessage implements Message<CloseMessage> {

    private String reasonText;
    private int status;

    @Override
    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }

    @Override
    public CloseMessage parse(String json) {
        return new GsonBuilder().create().fromJson(json, getClass());
    }

    @Override
    public CloseMessage get() {
        return this;
    }

    @Override
    public String toString() {
        return "CloseMessage{" +
                "reasonText='" + reasonText + '\'' +
                ", status=" + status +
                '}';
    }

}
