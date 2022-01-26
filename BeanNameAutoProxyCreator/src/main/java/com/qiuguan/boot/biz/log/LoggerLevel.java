/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.qiuguan.boot.biz.log;


/**
 * 流水账日志输出级别。
 *
 * @author ji.xuj@alipay.com
 * @author peng.lanqp
 * @version $Id: LoggerLevel.java, v 0.2 2015年6月6日 下午1:08:41 peng.lanqp Exp $
 */
public enum LoggerLevel implements IEnum {

    /** DEBUG */
    DEBUG("debug level to print log"),

    /** INFO */
    INFO("info level to print log"),

    /** WARN */
    WARN("warn level to print log"),

    /** ERROR */
    ERROR("error level to print log");

    /** 英文描述*/
    private final String englishName;

    /**
     * 私有构造函数。
     * 
     * @param englishName 描述
     */
    private LoggerLevel(String englishName) {
        this.englishName = englishName;
    }

    /**
     * 通过枚举<code>code</code>获得枚举。
     * @param code 简码
     * @return     枚举
     */
    public static LoggerLevel getByCode(String code) {
        for (LoggerLevel each : values()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

    /**
     * @return Returns the code.
     */
    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getEnglishName() {
        return this.englishName;
    }

    /**
     * @return Returns the description.
     */
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getChineseName() {
        return null;
    }
}
