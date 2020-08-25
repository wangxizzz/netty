package io.netty.example.demo.时间轮;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxi created on 2020/8/24 22:58
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
//        fun01();
        fun02();
    }

    public static void fun01() throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);

        System.out.println("start:" + LocalDateTime.now().format(formatter));

        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task :" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }


    public static void fun02() throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);

        System.out.println("start:" + LocalDateTime.now().format(formatter));

        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("task1:" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);

        hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(
                formatter)), 4, TimeUnit.SECONDS);

        Thread.sleep(10000);
    }

    /**
     * fun02得出的结论：
     * 可以看到，当前一个任务执行时间过长的时候，会影响后续任务的到期执行时间的。
     * 也就是说其中的任务是串行执行的。所以，要求里面的任务都要短平快。
     */
}
