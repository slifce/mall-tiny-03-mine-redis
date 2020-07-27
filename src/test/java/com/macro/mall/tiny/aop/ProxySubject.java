package com.macro.mall.tiny.aop;

/**
 * 静态代理
 * 静态代理主要通过将目标类与代理类实现同一个接口，让代理类持有真实类对象，
 * 然后在代理类方法中调用真实类方法，在调用真实类方法的前后添加我们所需要的功能扩展代码来达到增强的目的
 *
 * 假如我们的Subject接口要增加其它的方法，则ProxySubject代理类也必须同时代理这些新增的方法。
 * 同时我们也看到，request方法和response方法所织入的代码是一样的，这会使得代理类中出现大量冗余的代码，
 * 非常不利于扩展和维护。为了解决静态代理的这些缺陷，于是有了动态代理
 * Created by Administrator on 2020/7/27.
 */
public class ProxySubject implements Subject {

    private  Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("request... before 前置增强");
        subject.request();
        System.out.println("request... after 后置增强");
    }

    @Override
    public void response() {
        System.out.println("response... before 前置增强");
        subject.response();
        System.out.println("response... after 后置增强");
    }

    public static void main(String[] args) {
        //目标对象
        Subject realSubject = new RealSubject();
        //代理对象 通过构造器注入目标对象
        ProxySubject proxySubject = new ProxySubject(realSubject);
        proxySubject.request();
        proxySubject.response();
    }
}
