package com.qiuguan.boot.test.processor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author created by qiuguan on 2022/1/26 16:30
 *
 * @see MethodBeforeAdvice 前置通知接口
 * @see MethodInterceptor 它其实就是环绕通知，代理执行目标方法
 * @see AfterReturningAdvice 返回通知接口
 * @see ThrowsAdvice 异常通知接口，这个接口没有定义方法，但是请看注释，它规定了方法的定义格式
 * @see AfterAdvice 后置通知接口, 主要是标记，没有啥实际作用
 */
public class MyMethodInterceptor implements MethodInterceptor {

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        count.incrementAndGet();

        System.out.println("进入代理模式：代理的bean 是：" + invocation.getThis() + "; count: " + count.get());

        Object proceed = invocation.proceed();

        return proceed;
    }
}
