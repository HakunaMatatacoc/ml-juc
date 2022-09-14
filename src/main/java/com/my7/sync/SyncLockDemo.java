package com.my7.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁(递归锁)
 * <p>
 * synchronized(隐式)和Lock(显式)都是可重入锁
 */
public class SyncLockDemo {

    public synchronized void add(){
        add();
    }

    public static void main(String[] args) {

        // Lock演示可重入锁
        Lock lock = new ReentrantLock();

        // 创建线程
        new Thread(() -> {
            try{
                // 上锁
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 外层");

                try{
                    // 上锁
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 内层");
                }finally {
                    // 释放锁, 单独线程没问题, 若不释放会影响其他线程, 例如下面aa就一直在等待
//                    lock.unlock();
                }
            }finally {
                // 释放锁
                lock.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            lock.lock();
            System.out.println("aaaa");
            lock.unlock();
        },"aa").start();

        // 栈溢出
//        new SyncLockDemo().add();

        // synchronized
//        Object o = new Object();
//        new Thread(() -> {
//            synchronized (o) {
//                System.out.println(Thread.currentThread().getName() + " 外层");
//
//                synchronized (o){
//                    System.out.println(Thread.currentThread().getName() + " 中层");
//
//                    synchronized (o){
//                        System.out.println(Thread.currentThread().getName() + " 内层");
//                    }
//                }
//            }
//        }, "t1").start();
    }
}
