package net.metaclass.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    WebSocketServerHandshaker handshaker;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            HttpHeaders headers = httpRequest.headers();

            System.out.println("Http Request Received");
            System.out.println("Connection: " + headers.get("Connection"));
            System.out.println("Upgrade: " + headers.get("Upgrade"));

            if(headers.get(HttpHeaderNames.CONNECTION).equalsIgnoreCase("Upgrade") &&
                    headers.get(HttpHeaderNames.UPGRADE).equalsIgnoreCase("WebSocket")) {
                ctx.pipeline().replace(this, "websocketHandler", new WebSocketHandler());

                System.out.println("WebSocketHandler added to the pipeline");
                System.out.println("Opened Channel: " + ctx.channel());
                System.out.println("Handshaking...");

                handleHandshake(ctx, httpRequest);
                System.out.println("Handshake is done");
            }
            else {
                System.out.println("Incoming request is unknown");
            }
        }
    }

    protected void handleHandshake(ChannelHandlerContext ctx, HttpRequest req) {
        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory(getWebSocketURL(req), null, true);
        handshaker = wsFactory.newHandshaker(req);

        if(handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }
        else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    protected String getWebSocketURL(HttpRequest req) {
        return "ws://" + req.headers().get("Host") + req.getUri();
    }
}
