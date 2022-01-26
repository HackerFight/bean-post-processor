package com.qiuguan.boot.next.advice;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author created by qiuguan on 2022/1/26 16:30
 */
public class MyInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.printf("代理执行成功：target:%s", invocation.getThis() + "\n");
        Object proceed = invocation.proceed();
        return proceed;
    }
}
