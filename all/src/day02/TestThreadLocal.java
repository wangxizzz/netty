package day02;

/**
 * <Description>
 *
 * @author wangxi
 */
public class TestThreadLocal {
    public static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                // 一个Thread里给两个threadLocal赋值，这样当前Thread的threadLocalMap就会存储两个值。
                threadLocal1.set(100);
                threadLocal2.set("ass");
                System.out.println(threadLocal1.get());
            }finally {
                threadLocal1.remove();
                threadLocal2.remove();
            }
        }).start();


        System.out.println("主线程" + threadLocal1.get());
    }


}

