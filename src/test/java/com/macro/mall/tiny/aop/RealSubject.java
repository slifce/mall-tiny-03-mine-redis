package com.macro.mall.tiny.aop;

/**
 * 目标类
 * Created by Administrator on 2020/7/27.
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("执行目标方法request......");
    }

    @Override
    public void response() {
        System.out.println("执行目标方法response......");
    }
}
