package io.netty.example.demo.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * <Description>
 * ByteBuf的数据读取
 * @author wangxi
 */
public class ByteBufTest0 {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.buffer(10);
        // 赋值
        for (int i = 0; i < buf.capacity(); i++) {
            buf.writeByte(i);
        }
        // 取出来(调用绝对方法，直接定位取数据)
//        for (int i = 0; i < buf.capacity(); i++) {
//            System.out.println(buf.getByte(i));   // 传入索引
//        }

        // 调用相对方法取数据,此时是根据底层的readIndex来读取的
        for (int i = 0; i < buf.capacity(); i++) {
            System.out.println(buf.readByte());
        }
    }
}

