/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.qiuguan.boot.biz.log;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 拦截器基类
 * 
 * @author peng.lanqp
 * @version $Id: BaseInterceptor.java, v 0.1 2014-6-4 上午10:05:56 peng.lanqp Exp $
 */
public abstract class BaseInterceptor implements MethodInterceptor {

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        //排除原生方法
        Method[] methods = Object.class.getMethods();

        for (Method m : methods) {
            if (invocation.getMethod().equals(m)) {
                return null;
            }
        }

        return businessInvoke(invocation);
    }

    /**
     * 子拦截器自行处理。
     * 
     * @param   invocation  调用方法
     * @return              调用结果
     * @throws  Throwable   调用异常
     */
    public abstract Object businessInvoke(MethodInvocation invocation) throws Throwable;
}
