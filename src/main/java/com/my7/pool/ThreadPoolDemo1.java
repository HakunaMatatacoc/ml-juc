package com.my7.pool;

import java.util.concurrent.*;

// 演示线程池三种常用分类
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 一池N线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5); // 5个窗口

        // 一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor(); // 一个窗口

        // 一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            // 10个顾客请求
            for (int i = 1; i <= 10; i++) {
                // 执行
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭
            threadPoolExecutor.shutdown();
        }

    }
}
