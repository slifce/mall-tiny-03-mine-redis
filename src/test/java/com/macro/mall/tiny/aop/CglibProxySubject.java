package com.macro.mall.tiny.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CgLib动态代理
 * CgLib动态代理的原理是对指定的业务类生成一个子类，并覆盖其中的业务方法来实现代理。它的开发步骤：
 * 1.定义一个org.springframework.cglib.proxy.MethodInterceptor接口的实现类，重写intercept方法
 * 2.获取org.springframework.cglib.proxy.Enhancer类的对象
 * 3.分别调用Enhancer对象的setSuperclass和setCallback方法，使用create方法获取代理对象
 * 4.通过代理对象调用目标方法
 * Created by Administrator on 2020/7/27.
 */
public class CglibProxySubject implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before 前置通知");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(obj, args);
        }catch (Exception ex) {
            System.out.println("ex: " + ex.getMessage());
            throw ex;
        }finally {
            System.out.println("after 后置通知");
        }
        return result;
    }

    public static void main(String[] args) {
        //获取Enhancer 对象
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类（目标类）
        enhancer.setSuperclass(RealSubject.class);
        //设置回调方法
        enhancer.setCallback(new CglibProxySubject());
        //获取代理对象
        Subject proxySubject = (Subject)enhancer.create();

        //调用目标方法
        proxySubject.request();
        proxySubject.response();
    }
}
