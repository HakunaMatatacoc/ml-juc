package com.my7.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * <h1></h1>
 * <p>
 *
 * </p>
 *
 * @author jian.li on 2022/9/24 8:43
 */

class MyTask extends RecursiveTask<Integer> {

    // 拆分差值不能超过10, 计算10以内的运算
    private static final Integer VALUE = 10;
    private int begin; //拆分开始值
    private int end; // 拆分结束值
    private int result; // 返回结果

    // 创建有参数的构造
    public MyTask(int begin, int end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        // 判断相加两个数值是否大于10
        if((end-begin) <= VALUE){
            // 相加操作
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else{ // 进一步拆分
            // 获取中间值
            int middle = (begin + end)/2;

            // 拆分左边
            MyTask task1 = new MyTask(begin, middle);
            // 拆分右边
            MyTask task2 = new MyTask(middle + 1, end);

            // 调用方法拆分
            task1.fork();
            task2.fork();

            // 合并结果
            result = task1.join() + task2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyTask对象
        MyTask myTask = new MyTask(0,100);

        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        // 获取最终合并之后的结果
        Integer result = forkJoinTask.get();
        System.out.println(result);

        // 关闭池对象
        forkJoinPool.shutdown();
    }

}
