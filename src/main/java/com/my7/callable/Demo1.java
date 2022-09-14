package com.my7.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程多种方式
 * 第一种: 继承Thread类
 * 第二种: 实现Runnable接口
 * 第三种: 实现Callabel接口
 * 第四种: 线程池方式
 *
 * Runnable接口和Callable接口区别:
 *  1. 是否有返回值
 *  2. 是否抛出异常
 *  3. 实现方法名不同, 一个是run方法, 一个是call方法
 */

// 实现Runnable接口
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

// 实现Callable接口
class MyThread2 implements Callable{
    @Override
    public Object call() throws Exception {
        return 200;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Runnable接口创建线程
        new Thread(new MyThread1(), "AA").start();

        // Callable接口创建线程 (不正确)
//        new Thread(new MyThread2(),"BB").start();

        // 找一个类, 既和Runnable有关系, 又和Callable有关系
        // Runnable接口有实现类 FutureTask
        // FutureTask构造可以传递Callable

        // FutureTask
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());

        // Lambda表达式
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });

        // 创建一个线程
        new Thread(futureTask2, "lucy").start();

        while(!futureTask2.isDone()){
            System.out.println("wait......");
        }

        // 调用FutureTask的get方法
        System.out.println(futureTask2.get());

        System.out.println(Thread.currentThread().getName() + " is over");

        // FutureTask原理 未来任务
        /**
         *
         */
    }


}
