package com.my7.lock;


import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sentSMS() throws Exception {
        // 停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    public synchronized void sentEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

/**
 * 线程8锁
 *
 * 1.标准访问, 先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * 2.停4秒在短信方法内, 先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * 3.新增普通的hello方法, 是先打短信还是hello
 * ------getHello
 * ------sendSMS
 *
 * 4.现在有两部手机, 先打印短信还是邮件
 * ------sendEmail
 * ------sendSMS
 *
 * 5.两个静态同步方法, 1部手机, 先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * 6.两个静态同步方法, 2部手机, 先打印短信还是邮件
 * ------sendSMS
 * ------sendEmail
 *
 * 7.一个静态同步方法, 1个普通同步方法, 1部手机, 先打印短信还是邮件
 * ------sendEmail
 * ------sendSMS
 *
 * 8.一个静态同步方法, 1个普通同步方法, 2部手机, 先打印短信还是邮件
 * ------sendEmail
 * ------sendSMS
 *
 * synchronized实现同步的基础: Java中的每一个对象都可以作为锁
 * 具体表现为以下三种形式:
 * 对于普通同步方法, 锁是当前实例对象
 * 对于静态同步方法, 锁是当前类的class对象
 * 对于同步方法块, 锁是synchronized括号里配置的对象
 */
public class Lock8 {

    public static void main(String[] args) throws InterruptedException {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try{
                phone.sentSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try{
//                phone.sentEmail();

//                phone.getHello();

                phone2.sentEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
