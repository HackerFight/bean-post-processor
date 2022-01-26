/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.qiuguan.boot.biz.ann;

import com.qiuguan.boot.biz.log.LoggerLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 流水账日志标注。
 *
 * @author ji.xuj@alipay.com
 * @version $Id: DigestLogAnnotated.java,v 0.1 2008-10-13 下午09:20:26 Toby Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DigestLogAnnotated {

    /** 流水账日志名 */
    String name();

    /** 流水账日志输出级别 */
    LoggerLevel level() default LoggerLevel.INFO;
}