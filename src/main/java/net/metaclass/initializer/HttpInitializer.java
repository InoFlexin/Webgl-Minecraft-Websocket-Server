package net.metaclass.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import net.metaclass.handler.HttpServerHandler;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext;

    public HttpInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(sslContext.newHandler(ch.alloc()));
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("httphandler", new HttpServerHandler());
    }

}
