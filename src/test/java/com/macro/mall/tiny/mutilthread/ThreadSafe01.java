package com.macro.mall.tiny.mutilthread;

/**
 * Created by Administrator on 2020/8/24.
 */
public class ThreadSafe01 {
    public static void main(String[] args) {
        SellTicket01 thread = new SellTicket01();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();
    }
}

class SellTicket01 implements Runnable{
    //定义车票的数量
    private int ticket = 100;

    @Override
    public void run() {
        while(true) {
            synchronized(ThreadSafe01.class) {   //同步监视器为ThreadSafe1.class，它只加载一次，是唯一的，该同步代码块里包含着共享数据的操作
                if(ticket > 0) {
                    System.out.println(Thread.currentThread().getName()+":"+"出售第"+ticket+"张车票");
                    //车票数量减一
                    ticket--;
                    //进行线程休眠，增加其他线程调用的机会
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
        }
    }
}