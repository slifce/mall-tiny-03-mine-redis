package com.macro.mall.tiny.mutilthread;

/**
 * Created by Administrator on 2020/8/24.
 */
public class ThreadSafe02 {

    public static void main(String[] args) {
        SellTicket02 thread = new SellTicket02();
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

class SellTicket02 implements Runnable{
    //定义车票的数量
    private int ticket = 100;

    @Override
    public void run() {
        while(true) {
            //调用窗口售票方法
            sale();
            if(ticket == 0) {
                break;
            }
        }
    }
    //实现窗口售票
    public synchronized void sale() {   //该方法的同步监视器为ThreadSafe2的对象，它是唯一的，这里面也存放着对共享数据的操作
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
        }
    }
}
