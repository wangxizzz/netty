package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/**
 * <Description>
 *  CompositeByteBuf例子
 * @author wangxi
 */
public class ByteBufTest2 {
    public static void main(String[] args) {
        CompositeByteBuf bufs = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer(8);
        bufs.addComponents(heapBuf, directBuf);

        // 也可以删除一个组件
        //bufs.removeComponent(0);
        Iterator<ByteBuf> iterator = bufs.iterator();
        bufs.forEach(System.out::println);
    }
}

