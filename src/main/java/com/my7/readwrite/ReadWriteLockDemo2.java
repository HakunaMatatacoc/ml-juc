package com.my7.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// 演示读写锁降级
public class ReadWriteLockDemo2 {
    public static void main(String[] args) {
        // 可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // 锁降级
        // 1. 获取写锁
        writeLock.lock();
        System.out.println("michael");

        // 2. 获取读锁
        readLock.lock();
        System.out.println("---read");

        // 3. 释放写锁
        writeLock.unlock();

        // 4. 释放读锁
        readLock.unlock();
    }
}
