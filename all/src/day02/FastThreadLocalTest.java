package day02;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @Author wangxi
 * @Time 2019/12/15 17:17
 */
public class FastThreadLocalTest {
    public static void main(String[] args) {
        FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();
        fastThreadLocal.set("aaa");
        System.out.println(fastThreadLocal.get());
        fastThreadLocal.remove();
    }
}
