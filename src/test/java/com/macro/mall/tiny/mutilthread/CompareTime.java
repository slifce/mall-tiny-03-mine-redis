package com.macro.mall.tiny.mutilthread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单线程循环耗时： = [8876]
 * 100000
 * 线程池循环耗时： = [44]
 * 100000
 * Created by Administrator on 2020/7/27.
 */
public class CompareTime {

    public static void normalRun() throws InterruptedException {
        long starTime = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<Integer>();
        for (int i = 0 ; i < 100000; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
            //join()方法标识阻塞当前主线程[T0线程]（并不是阻塞当前thread子线程）
            //Thread.join其实底层是通过wait/notifyall来实现线程的通信达到线程阻塞的目的；
            //当线程执行结束以后，会触发两个事情，第一个是设置native线程对象为null、
            //第二个是通过notifyall方法，让等待在thread对象锁上的wait方法被唤醒。

        }
        System.out.println("单线程循环耗时： = [" + (System.currentTimeMillis()-starTime) + "]");
        System.out.println(list.size());
    }

    public static void poolRun() throws InterruptedException {
        long starTime = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<Integer>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0 ; i < 100000 ; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("线程池循环耗时： = [" + (System.currentTimeMillis()-starTime) + "]");
        System.out.println(list.size());
    }

    public static void renameThread() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool(new ThreadFactory(){

            final AtomicInteger threadNumber = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                // Use our own naming scheme for the threads.
                Thread thread = new Thread(Thread.currentThread().getThreadGroup(), runnable,
                        "pool-spark" + threadNumber.getAndIncrement(), 0); //这里实现命名
                // Make workers daemon threads.
                thread.setDaemon(true);
                if (thread.getPriority() != Thread.NORM_PRIORITY) {
                    thread.setPriority(Thread.NORM_PRIORITY);
                }
                return thread;
            }
        });
        int sum = 0;
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return 1 + 1;
            }
        });
        Integer result = future.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        renameThread();
        normalRun();
        poolRun();
    }

}
