package com.my7.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 三个condition是为了实现指定唤醒
// 第一步 创建资源类
class ShareResource{
    // 定义标志位
    private int flag = 1; // 1 AA  | 2 BB |  3 CC

    // 创建lock锁
    private Lock lock = new ReentrantLock();

    // 创建三个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 打印5次, 参数第几轮
    public void print5(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try{
            // 判断
            while(flag != 1){
                // 等待
                c1.await();
            }

            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " : 轮数 : " + loop);
            }

            // 通知
            flag = 2;
            c2.signal(); // 通知BB线程
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    // 打印10次, 参数第几轮
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try{
            while (flag != 2){
                c2.await();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " : 轮数 : " + loop);
            }

            flag = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }

    // 打印15次, 参数第几轮
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try{
            while (flag != 3){
                c3.await();
            }

            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " : 轮数 : " + loop);
            }

            flag = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CC").start();
    }
}
