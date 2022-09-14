package com.my7.lock;

import java.util.concurrent.locks.ReentrantLock;

// 第一步 创建资源类, 定义属性和操作方法

/**
 * 公平锁: 阳光普照, 效率相对低
 * 非公平锁: 线程饿死, 效率高
 */
class LTicket{
    // 票的数量
    private int number = 30;

    // 创建可重入锁

    // 非公平锁
//    private final ReentrantLock lock = new ReentrantLock();

    // 公平锁
    private final ReentrantLock lock = new ReentrantLock(true);

    // 卖票方法
    public void sale(){
        try {
            // 上锁
            lock.lock();

            // 判断是否有票
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + " : 卖出" + (number--) + " 剩余: " + number);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}

public class LSaleTicket {

    // 创建三个线程
    public static void main(String[] args) {
        // 第二步 创建多个线程, 调用资源类的操作方法
        LTicket ticket = new LTicket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CC").start();
    }

}
