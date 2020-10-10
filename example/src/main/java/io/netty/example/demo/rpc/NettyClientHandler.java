package io.netty.example.demo.rpc;

import java.util.concurrent.CompletableFuture;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.concurrent.Future;

@Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) {

		System.out.println("--- client already connected----");

	}


    /**
     * 其中msg已经是一个完整的协议帧了（因为DelimiterBasedFrameDecoder与StringDecoder已经做过解析），
     * 这里的channelRead（）方法把对msg消息的处理提交到AllChannelHandler管理的线程池，以便及时释放Netty的I/O线程
     */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		// 提交给线程池异步执行， 释放IO线程
		AllChannelHandler.channelRead(() -> {
			// 1.根据请求id，获取对应future
			CompletableFuture future = FutureMapUtil.remove(((String) msg).split(":")[1]);
			// 2.如果存在，则设置future结果
			if (null != future) {
			    // 如果没完成，手动设置结果
				future.complete(((String) msg).split(":")[0]);
			}
		});

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}