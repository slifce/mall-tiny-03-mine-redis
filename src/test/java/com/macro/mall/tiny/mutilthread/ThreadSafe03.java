package com.macro.mall.tiny.mutilthread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/8/24.
 */
public class ThreadSafe03 {

    public static void main(String[] args) {
        SellTicket03 thread = new SellTicket03();
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

class SellTicket03 implements Runnable{
    //定义车票的数量
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true) {
            try {
                lock.lock();  //对操作共享数据的代码进行加锁
                //进行线程休眠，增加其他线程调用的机会
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(ticket > 0) {
                    System.out.println(Thread.currentThread().getName()+":"+"出售第"+ticket+"张车票");
                    //车票数量减一
                    ticket--;
                }else {
                    break;
                }
            }finally {
                lock.unlock(); //进行解锁
            }
        }
    }
}
