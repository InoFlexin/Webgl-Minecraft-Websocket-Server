package net.metaclass.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    private WebSocketServerHandshaker handshaker;

    private static Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            HttpHeaders headers = httpRequest.headers();

            logger.info("HttpRequest Received");
            logger.info("Connection: " + headers.get("Connection"));
            logger.info("Upgrade: " + headers.get("Upgrade"));

            if(headers.get(HttpHeaderNames.CONNECTION).equalsIgnoreCase("Upgrade") &&
                    headers.get(HttpHeaderNames.UPGRADE).equalsIgnoreCase("WebSocket")) {
                ctx.pipeline().replace(this, "websocketHandler", new WebSocketHandler());

                logger.info("WebSocketHandler added to the pipeline");
                logger.info("Opened Channel: " + ctx.channel());
                logger.info("Handshaking...");

                handleHandshake(ctx, httpRequest);
                logger.info("Handshake is done");
            }
            else {
                logger.info("Incoming request is unknown");
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
