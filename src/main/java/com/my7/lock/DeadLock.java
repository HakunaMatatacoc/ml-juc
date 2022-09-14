package com.my7.lock;

import java.util.concurrent.TimeUnit;

/**
 * 1.什么是死锁?
 *  两个或者两个以上线程在执行过程中, 因为争夺资源而造成一种互相等待的现象, 如果没有外力干涉, 他们无法再执行下去
 *
 * 2.产生死锁原因?
 *  2.1 系统资源不足
 *  2.2 进程推进顺序不合适
 *  2.3 资源分配不当
 *
 * 3.验证是否是死锁
 *  3.1 jps (类似linux ps -ef)
 *  3.2 jstack (jvm自带堆栈跟踪工具) 用法: jstack 进程id
 */
public class DeadLock {

    // 创建两个对象
    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a){
                System.out.println(Thread.currentThread().getName() + " 持有锁a, 试图获取锁b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (b){
                    System.out.println(Thread.currentThread().getName() + "获取锁b");
                }
            }
        },"A").start();

        new Thread(() -> {
            synchronized (b){
                System.out.println(Thread.currentThread().getName() + " 持有锁b, 试图获取锁a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (a){
                    System.out.println(Thread.currentThread().getName() + "获取锁a");
                }
            }
        },"B").start();
    }
}
