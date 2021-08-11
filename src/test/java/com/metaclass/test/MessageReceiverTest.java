package com.metaclass.test;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import net.metaclass.domain.data.DataContext;
import net.metaclass.domain.message.TextMessage;
import net.metaclass.domain.message.protocol.Message;
import net.metaclass.domain.message.protocol.MessageReceiver;
import net.metaclass.domain.message.protocol.MessageWrapper;
import net.metaclass.domain.message.protocol.WebSocketFrameWrapper;
import net.metaclass.domain.message.receiver.MessageReceiverFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageReceiverTest {

    private WebSocketFrame getTestWebSocketFrame() {
        DataContext dataContextBuilder = DataContext.builder();
        dataContextBuilder.setData("x", 1.0);
        dataContextBuilder.setData("y", 2.5);
        dataContextBuilder.setData("z", 3.1);

        Message<TextMessage> message = new TextMessage("onPlayerMove", "id", "example_class", dataContextBuilder.to());
        WebSocketFrame frame = new TextWebSocketFrame(message.toJson());
        return frame;
    }

    @Test
    public void creationMessageFrameWrapperTest() {
        WebSocketFrame webSocketFrame = getTestWebSocketFrame();
        WebSocketFrameWrapper webSocketFrameWrapper = new WebSocketFrameWrapper(null, webSocketFrame);
        System.out.println(((TextWebSocketFrame) webSocketFrameWrapper.getWebSocketFrame()).text());
        assertEquals(webSocketFrameWrapper != null, true);
    }

    @Test
    public void textMessageReceiveTest() {

        WebSocketFrame frame = getTestWebSocketFrame();
        String text = ((TextWebSocketFrame) frame).text();
        WebSocketFrameWrapper frameWrapper = new WebSocketFrameWrapper(null, frame);

        MessageReceiver receiver = MessageReceiverFactory.getFactory().getReceiver(frame);
        MessageWrapper wrapper = receiver.receive(frameWrapper);

        System.out.println(wrapper.getMessage());
        assertEquals(wrapper.getMessage().toJson(), text);
    }

}
