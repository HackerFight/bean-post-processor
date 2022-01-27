package com.qiuguan.boot.next.advice;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author created by qiuguan on 2022/1/26 16:30
 *
 * @see MethodBeforeAdvice 前置通知接口
 * @see MethodInterceptor 它其实就是环绕通知，代理执行目标方法
 * @see AfterReturningAdvice 返回通知接口
 * @see ThrowsAdvice 异常通知接口，这个接口没有定义方法，但是请看注释，它规定了方法的定义格式
 * @see AfterAdvice 后置通知接口, 主要是标记，没有啥实际作用
 */
public class MyInterceptor implements MethodBeforeAdvice, /*MethodInterceptor,*/ AfterReturningAdvice,
        ThrowsAdvice, AfterAdvice {

    /**
     * 前置通知
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.printf("代理执行前置通知成功：methodName:%s, args:%s, target:%s\n", method.getName(), Arrays.asList(args), target);
    }

    /**
     * 环绕通知
     * @param returnValue
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
//    @Override
//    public Object invoke(MethodInvocation invocation) throws Throwable {
//        System.out.printf("代理执行成功：target:%s", invocation.getThis() + "\n");
//        Object proceed = invocation.proceed();
//        return proceed;
//    }


    /**
     * 返回通知
     * @param returnValue
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.printf("代理执行返回通知成功：methodName:%s, args:%s, returnValue:%s\n", method.getName(), Arrays.asList(args), returnValue);
    }


    /**
     * 异常通知
     * @param method
     * @param args
     * @param target
     * @param ex
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
        System.out.printf("代理执行异常通知成功：methodName:%s, args:%s, ex:%s\n", method.getName(), Arrays.asList(args), ex);

    }
}
