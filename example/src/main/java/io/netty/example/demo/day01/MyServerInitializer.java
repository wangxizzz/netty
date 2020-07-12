package io.netty.example.demo.day01;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * <Description>
 *
 * @author wangxi
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        // Decoder是入站处理器，Encode是出站处理器(编码消息发送到网络)
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast(new LengthFieldPrepender(4));
        // 解码器，是一个入站处理器，把被封装成的字节流消息解码成String(在本例中),因此在MyServerHandler的泛型可以直接使用String
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        // 编码器
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new MyServerHandler());
    }
}

