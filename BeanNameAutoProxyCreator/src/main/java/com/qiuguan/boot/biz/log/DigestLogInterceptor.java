/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.qiuguan.boot.biz.log;

import java.lang.reflect.Method;
import java.util.List;

import com.qiuguan.boot.biz.ann.DigestLogAnnotated;
import com.qiuguan.boot.biz.util.ExceptionLogUtil;
import com.qiuguan.boot.biz.util.LogUtil;
import com.qiuguan.boot.biz.util.SystemErrorCodeUtil;
import org.aopalliance.intercept.MethodInvocation;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;

/**
 * 用户操作摘要日志拦截器。
 * 
 * @author chunmao.zhu
 * @author peng.lanqp
 * @author yanbest.zhangy 增加错误码日志打印
 * @author liangjun.zhong 新增支持在实现类上打注解
 * @version $Id: DigestLogInterceptor.java, v 0.4 2017年4月1日 下午5:50:53 liangjun.zhong Exp $
 */
public class DigestLogInterceptor extends BaseInterceptor {

    @Override
    public Object businessInvoke(final MethodInvocation invocation) throws Throwable {

        final long startTime = System.currentTimeMillis();

        //获取接口方法
        final Method interfaceMethod = invocation.getMethod();

        //是否需要记录流水账
        boolean needDigest = false;
        String loggerName = "";
        LoggerLevel loggerLevel = null;

        String invocationSignature = interfaceMethod.getDeclaringClass().getSimpleName() + "."
                                     + interfaceMethod.getName();

        Method annotatedMethod = null;
        if (interfaceMethod.isAnnotationPresent(DigestLogAnnotated.class)) {
            annotatedMethod = interfaceMethod;
        } else {

            //获取实现方法
            final Method implementMethod = invocation.getThis().getClass()
                .getMethod(interfaceMethod.getName(), interfaceMethod.getParameterTypes());
            if (implementMethod.isAnnotationPresent(DigestLogAnnotated.class)) {
                annotatedMethod = implementMethod;
                invocationSignature = invocation.getThis().getClass().getSimpleName() + "."
                                      + implementMethod.getName();
            }
        }

        if (annotatedMethod != null) {
            final DigestLogAnnotated annotated = annotatedMethod
                .getAnnotation(DigestLogAnnotated.class);
            needDigest = true;
            loggerName = annotated.name();
            loggerLevel = annotated.level();
        }

        //DigestLogHolder.clear();

        try {
            return invocation.proceed();
        } finally {

            //if (needDigest && DigestLogHolder.get() != null) {
            if (needDigest) {

                try {
                    List<BaseDigestLog> digestLogList = DigestLogHolder.get();
                    for (BaseDigestLog digestLog : digestLogList) {
                        digestLog.setElapse(System.currentTimeMillis() - startTime);
                        digestLog.setInvocationSignature(invocationSignature);

                        // 打印摘要日志
                        printLog(loggerName, loggerLevel, digestLog);

                        // 打印错误码日志
                        SystemErrorCodeUtil.printLog(digestLog);
                    }
                } finally {
                    DigestLogHolder.clear();
                }
            }
        }
    }

    /**
     * 输出日志
     * 
     * @param loggerName 日志名称
     * @param loggerLevel 日志级别
     * @param digestLog 日志内容
     */
    private void printLog(String loggerName, LoggerLevel loggerLevel, BaseDigestLog digestLog) {
        try {

            Logger digestLogger = LoggerFactory.getLogger(loggerName);
            if (loggerLevel == LoggerLevel.DEBUG) {

                if (digestLogger.isDebugEnabled()) {
                    LogUtil.debug(digestLogger, digestLog.toDigest());
                }

            } else if (loggerLevel == LoggerLevel.INFO) {

                if (digestLogger.isInfoEnabled()) {
                    LogUtil.info(digestLogger, digestLog.toDigest());
                }

            } else if (loggerLevel == LoggerLevel.WARN) {

                LogUtil.warn(digestLogger, digestLog.toDigest());

            } else if (loggerLevel == LoggerLevel.ERROR) {

                LogUtil.error(digestLogger, digestLog.toDigest());

            }
        } catch (Exception e) {
            ExceptionLogUtil.error(e, "digest log print exception:");
        }
    }

}
