package com.macro.mall.tiny.queue.improve;

/**
 * 具体执行相关业务的业务类
 * @author whd
 * Created by Administrator on 2020/8/17.
 */
public class DelayOrderWorker  implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        //相关业务逻辑处理
        System.out.println(Thread.currentThread().getName()+" do something ……");
    }
}
