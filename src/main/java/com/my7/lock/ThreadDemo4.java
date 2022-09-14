package com.my7.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * List集合线程不安全
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        // 创建ArrayList集合
//        List<String> list = new ArrayList<>();

        // Vector 解决方案一
//        List<String> list = new Vector<>();

        // Collections 解决方案二
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        // CopyOnWriteArrayList(写时复制技术) 解决方案三
        /*
        List<String> list = new CopyOnWriteArrayList<>();
           for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                // 从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }*/


        // 演示HashSet
//        Set<String> set = new HashSet<>();

/*        Set<String> set  = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                set.add(UUID.randomUUID().toString().substring(0,8));
                // 从集合获取内容
                System.out.println(set);
            },String.valueOf(i)).start();
        }*/

        // 演示HashMap
//        Map<String,String> map  = new HashMap();

        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // 向集合添加内容
                map.put(key,UUID.randomUUID().toString().substring(0,8));
                // 从集合获取内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
