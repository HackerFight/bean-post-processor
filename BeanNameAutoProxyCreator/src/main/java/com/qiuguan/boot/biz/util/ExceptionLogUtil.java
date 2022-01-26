/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.qiuguan.boot.biz.util;

import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;

/**
 * 捕捉到异常的时候，我们通常会使用<code>logger.error("xxxx",e)</code>方式打印日常堆栈日志<br>
 * 但是这种方式会造成错误日志打印两遍，精益求精，日志也追求极致，错误日志尽量使用本工具类输出。
 * 
 * @author peng.lanqp
 * @author wuhui
 * @version $Id: ExceptionLogUtil.java, v 0.2 2014-5-14 下午6:12:15 peng.lanqp Exp $
 */
public final class ExceptionLogUtil {

    /** errorLogger，sofa框架默认生成的 */
    private static final Logger logger = LoggerFactory.getLogger("ERROR");

    /**
     * 禁用构造函数
     */
    private ExceptionLogUtil() {
        // 禁用构造函数
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param e 异常堆栈
     * @param message 错误日志上下文信息描述，尽量带上业务特征
     */
    public static void error(Throwable e, Object... message) {
        LogUtil.error(logger, e, message);
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param message 错误日志上下文信息描述，尽量带上业务特征
     */
    public static void error(Object... message) {
        logger.error(LogUtil.getLogString(message));
    }

}
