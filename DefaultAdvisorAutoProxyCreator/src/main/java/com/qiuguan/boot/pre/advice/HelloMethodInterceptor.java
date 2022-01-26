package com.qiuguan.boot.pre.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author created by qiuguan on 2022/1/26 15:59
 * 这里实现 {@link MethodInterceptor} 主要是去实现 {@link org.aopalliance.aop.Advice} 接口
 * @see org.springframework.aop.BeforeAdvice
 * @see org.springframework.aop.AfterAdvice
 * @see org.springframework.aop.AfterReturningAdvice
 * @see
 */
public class HelloMethodInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.printf("代理执行成功：target:%s", invocation.getThis() + "\n");
        Object proceed = invocation.proceed();
        return proceed;
    }
}
