package com.qiuguan.boot.test.processor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author created by qiuguan on 2022/1/25 15:11
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
