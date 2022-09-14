package com.my7.sync;

// 第一步 创建资源类, 定义属性和操作方法
class Share{
    // 初始值
    private int number = 0;

    // +1 的方法
    public synchronized void incr() throws InterruptedException {
        // 第二步 判断 干活 通知
        // 多线程会有虚假唤醒的情况
//        if(number != 0){ // 判断number值是否是0, 如果不是0, 等待
//            this.wait();// 在哪里睡, 就在哪里醒
//        }

        // 解决虚假唤醒
        while(number != 0){ // 判断number值是否是0, 如果不是0, 等待
            this.wait();// 在哪里睡, 就在哪里醒
        }

        // 如果number值是0, 就+1
        number++;
        System.out.println(Thread.currentThread().getName() + " :: " +number);
        // 通知其他线程
        this.notifyAll();
    }

    // -1 的方法
    public synchronized void decr() throws InterruptedException {

        // 判断
        if(number != 1){
            this.wait();
        }

        // 干活
        number--;
        System.out.println(Thread.currentThread().getName() + " :: " +number);
        // 通知其他线程
        this.notifyAll();
    }
}

public class ThreadDemo1 {

    // 第三步 创建多个线程, 调用资源类的操作方法
    public static void main(String[] args) {
        Share share = new Share();

        // 创建线程
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        },"AA").start();

        // 创建线程
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        },"BB").start();

        // 创建线程
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        },"CC").start();

        // 创建线程
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        },"DD").start();
    }
}
