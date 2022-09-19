package com.my7.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <h1>线程池七个参数</h1>
 * <p>
 * int corePoolSize  常驻线程数量(核心)
 * int maximumPoolSize  最大线程数量
 * long keepAliveTime  线程存活时间(值)
 * TimeUnit unit       线程存活时间(单位)
 * BlockingQueue<Runnable> workQueue  阻塞队列
 * ThreadFactory threadFactory  线程工厂
 * RejectedExecutionHandler handler  拒绝策略
 * </p>
 *
 * @author jian.li on 2022/9/18 23:28
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
