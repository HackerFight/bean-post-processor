/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiuguan.boot.biz.util;

import com.qiuguan.boot.biz.log.BaseDigestLog;
import org.springframework.util.CollectionUtils;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.alipay.common.error.ErrorContext;

/**
 * 系统错误码摘要日志打印
 * <p>
 * 日志格式：[ialicore统一打印][接口或业务场景,是否错误根,错误码信息]
 * </p>
 * 
 * @author yanbest.zhangy
 * @version $Id: SystemErrorCodeUtil.java, v 0.1 2015年4月28日 下午2:25:36 yanbest.zhangy Exp $
 */
public final class SystemErrorCodeUtil {

    /** 系统错误码输出日志 system-errorcode.log */
    private static final Logger logger    = LoggerFactory.getLogger("SYSTEM-ERRORCODE");

    /** 日志内容分隔符 */
    public static final String  SEP       = ",";

    /** 修饰符 */
    private static final char   RIGHT_TAG = ']';

    /** 修饰符 */
    private static final char   LEFT_TAG  = '[';

    /**
     * 禁用构造函数
     */
    private SystemErrorCodeUtil() {
        // 禁用构造函数
    }

    /**
     * 根据摘要日志，输出错误码日志
     * 
     * @param digestLog    摘要日志基类
     */
    public static void printLog(BaseDigestLog digestLog) {
        if (digestLog == null || digestLog.isSuccess()) {
            return;
        }

        printLog(digestLog.getInvocationSignature(), digestLog.getErrorContext());
    }

    /**
     * 根据业务场景、错误码，输出错误码日志信息
     * 
     * @param scene         业务场景
     * @param errorContext  错误码
     */
    public static void printLog(String scene, ErrorContext errorContext) {
        if (errorContext == null || CollectionUtils.isEmpty(errorContext.getErrorStack())) {
            return;
        }

        String errorSourse = errorContext.getErrorStack().size() == 1 ? "Y" : "N";
        String errorCode = errorContext.toDigest();

        StringBuilder log = new StringBuilder();
        log.append(LEFT_TAG);
        log.append(scene).append(SEP);
        log.append(errorSourse).append(SEP);
        log.append(errorCode);
        log.append(RIGHT_TAG);

        LogUtil.info(logger, log);
    }
}
