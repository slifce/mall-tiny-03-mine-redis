package com.macro.mall.tiny.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 与静态代理相比，动态代理的代理类不需要程序员自己手动定义，而是在程序运行时动态生成
 * 动态代理可以分为JDK动态代理和CgLib动态代理

 * JDK动态代理
 * JDK动态代理与静态代理一样，目标类需要实现一个代理接口，它的开发步骤如下：
 * 1.定义一个java.lang.reflect.InvocationHandler接口的实现类，重写invoke方法
 * 2.将InvocationHandler对象作为参数传入java.lang.reflect.Proxy的newProxyInstance方法中
 * 3.通过调用java.lang.reflect.Proxy的newProxyInstance方法获得动态代理对象
 * 4.通过代理对象调用目标方法
 *
 * 两种代理的区别
 * JDK动态代理和CgLib动态代理的主要区别：
 * JDK动态代理只能针对实现了接口的类的接口方法进行代理
 * CgLib动态代理基于继承来实现代理，所以无法对final类、private方法和static方法实现代理

 * Spring AOP的代理
 * Spring AOP中的代理使用的默认策略是：
 * 如果目标对象实现了接口，则默认采用JDK动态代理
 * 如果目标对象没有实现接口，则采用CgLib进行动态代理
 * 如果目标对象实现了接口，且强制CgLib代理，则采用CgLib进行动态代理
 * Created by Administrator on 2020/7/27.
 */
public class JdkProxySubject implements InvocationHandler{

    private Subject subject;

    public JdkProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before 前置通知");
        Object result = null;
        try {
            result = method.invoke(subject, args);
        }catch (Exception ex) {
            System.out.println("ex: " + ex.getMessage());
            throw ex;
        }finally {
            System.out.println("after 后置通知");
        }
        return result;
    }

    public static void main(String[] args) {
        //获取InvocationHandler对象 在构造方法中注入目标对象
        InvocationHandler handler = new JdkProxySubject(new RealSubject());
        //获取代理类对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(JdkProxySubject.class.getClassLoader(), new Class[]{Subject.class}, handler);
        //调用目标方法
        proxySubject.request();
        proxySubject.response();
    }

}
