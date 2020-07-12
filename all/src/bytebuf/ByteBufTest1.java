package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * <Description>
 *  ByteBuf的基本特性与API操作
 * @author wangxi
 */
public class ByteBufTest1 {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("王hello world", Charset.forName("utf-8"));
        // 如果ByteBuf的内容存储在字节数组中，那么底层创建的就是堆上的Buffer，
        // 如果不是数组，那么就是在堆外，由操作系统分配的内存。
        if (buf.hasArray()) {
            byte[] content = buf.array();
            System.out.println(new String(content, Charset.forName("utf-8")));
            System.out.println(buf);
            System.out.println(buf.arrayOffset());  // 第一个字节的偏移量
            System.out.println(buf.capacity());
            System.out.println(buf.readerIndex());
            System.out.println(buf.writerIndex());
            System.out.println(buf.readableBytes());

            for (int i = 0; i < buf.readableBytes(); i++) {
                // 绝对的调用获取ByteBuf的方法，因此底层的readIndex与writeIndex不会改变
                // 因此循环每次调用buf.readableBytes()也不会改变。
                System.out.println((char)buf.getByte(i));
            }
            System.out.println(buf.getCharSequence(0, 4, Charset.forName("utf-8")));
        }
    }
}

