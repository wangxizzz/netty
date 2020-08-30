package io.netty.example.demo.时间轮;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author wangxi created on 2020/8/27 23:34
 * @version v1.0
 *
 * Timer是jdk中提供的一个定时器工具，使用的时候会在主线程之外起一个单独的线程执行指定的计划任务
 * 可以指定执行一次或者反复执行多次。
 *
 * 每一个Timer仅对应唯一一个线程。
 * Timer不保证任务执行的十分精确。
 * Timer类的线程安全的。
 */
public class TestTimer {
    public static void main(String args[]) {
        System.out.println("About to schedule task.");
        new Reminder(2);
        System.out.println("Task scheduled.");
    }

    public static class Reminder {
        Timer timer;

        public Reminder(int sec) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("Time's up!");
                    //timer.cancel();  timer线程退出
                }
            }, sec * 1000);
        }
    }
}
