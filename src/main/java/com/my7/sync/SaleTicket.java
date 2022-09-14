package com.my7.sync;

// 第一步 创建资源类, 定义属性和操作方法
class Ticket {
    // 票数
    private int number = 30;

    // 操作方法: 卖票
    public synchronized void sale() throws InterruptedException {
        // 判断: 是否有票
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+" : 卖出: " + number-- + " 剩下: " + number);
            Thread.sleep(500);
        }
    }
}

public class SaleTicket {

    // 第二步 创建多个线程, 调用资源类的操作方法
    public static void main(String[] args) {
        // 创建Ticket对象
        Ticket ticket = new Ticket();

        //创建三个线程
        new Thread(() -> {
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                try {
                    ticket.sale();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AA").start();

        new Thread(() -> {
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                try {
                    ticket.sale();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BB").start();

        new Thread(() -> {
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                try {
                    ticket.sale();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CC").start();
    }
}
