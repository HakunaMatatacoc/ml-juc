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
 *
 *  阿里巴巴开发手册规定: 不允许使用Executors的方式手动创建线程池, 而是要通过ThreadPoolExecutor的方式,
 *  这样的处理方式让写的同学更加明确线程池的运行规则, 规避资源耗尽的风险
 *
 *  Executors返回的线程池对象的弊端如下:
 *      FixedThreadPool和SingleThreadPool:
 *          允许的请求队列长度为Integer.MAX_VALUE, 可能会堆积大量的请求, 从而导致OOM(out of memory 内存溢出)
 *       CachedThreadPool和ScheduledThreadPool:
 *          允许的创建线程数量为Integer.MAX_VALUE, 可能会创建大量的线程, 从而导致OOM
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
