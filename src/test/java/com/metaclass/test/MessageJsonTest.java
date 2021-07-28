package com.metaclass.test;

import net.metaclass.domain.Message;
import net.metaclass.domain.TextMessage;
import net.metaclass.domain.data.DataContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageJsonTest {

    @Test
    public void messageJsonConvertTest() {
        DataContext dataContextBuilder = DataContext.builder();
        dataContextBuilder.setData("x", 1.0);
        dataContextBuilder.setData("y", 2.5);
        dataContextBuilder.setData("z", 3.1);
        Message<TextMessage> message = new TextMessage("onPlayerMove", "id", "example_class", dataContextBuilder.to());
        String json = message.toJson();
        System.out.println(json);

        Message<TextMessage> fromJson = new TextMessage().parse(json);

        assertEquals(fromJson != null, true);
        assertEquals(json, fromJson.toJson());
    }

}
