/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.qiuguan.boot.biz.log;

import com.alipay.common.error.ErrorContext;
import com.qiuguan.boot.biz.util.StringUtil;

/**
 * 摘要日志基类。
 * 
 * @author chunmao.zhu
 * @author peng.lanqp
 * @author yanbest.zhangy 增加版本位预留
 * @author liangjun.zhong
 * @version $Id: BaseDigestLog.java, v 0.4 2017年4月3日 下午9:11:52 liangjun.zhong Exp $
 */
public abstract class BaseDigestLog {

    /** 处理是否成功 */
    private boolean      success = false;

    /** 错误上下文 */
    private ErrorContext errorContext;

    /** 调用者（拦截器设置） */
    private String       invocationSignature;

    /** 执行时长（拦截器设置） */
    private long         elapse;

    // ~~~ 功能方法

    /**
     * 构造单据信息摘要
     * 
     * @return 单据信息摘要
     */
    public String toDigest() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("[");

        // 构造调用信息
        composeInvokeInfo(buffer);

        // 构造业务信息
        composeBizInfo(buffer);

        buffer.append("]");

        return buffer.toString();
    }

    /**
     * 构造服务调用处理摘要
     * 
     * @param buffer 摘要信息
     */
    protected void composeInvokeInfo(StringBuilder buffer) {
        buffer.append("(");
        {
            fillBuilderInfo(buffer, invocationSignature);
            fillBuilderInfo(buffer, elapse + "ms");
            fillBuilderInfo(buffer, success ? "Y" : "N");
            fillBuilderInfo(buffer, fetchResultCode());
            //fillBuilderLastInfo(buffer, version);
        }
        buffer.append(")");
    }

    /**
     * 构造业务信息摘要
     * 
     * @param buffer 摘要信息
     */
    abstract protected void composeBizInfo(StringBuilder buffer);

    /**
     * 填充摘要信息
     * 
     * @param buffer 摘要信息缓冲区
     * @param info 摘要信息
     */
    protected void fillBuilderInfo(StringBuilder buffer, String info) {
        buffer.append(StringUtil.defaultIfBlank(info, "-")).append(",");
    }

    /**
     * 填充最后一个摘要信息，不需要以逗号分隔了
     * 
     * @param buffer 摘要信息缓冲区
     * @param info 摘要信息
     */
    protected void fillBuilderLastInfo(StringBuilder buffer, String info) {
        buffer.append(StringUtil.defaultIfBlank(info, "-"));
    }

    /**
     * 填充摘要信息
     *
     * @param buffer 摘要信息缓冲区
     * @param money 金额信息
     */
//    protected void fillBuilderInfo(StringBuilder buffer, MultiCurrencyMoney money) {
//        if (money == null) {
//            fillBuilderInfo(buffer, "-");
//            fillBuilderInfo(buffer, "-");
//        } else {
//            fillBuilderInfo(buffer, money.getAmount().toString());
//            fillBuilderInfo(buffer, money.getCurrencyValue());
//        }
//    }

    /**
     * 填充最后一个摘要信息，不需要以逗号分隔了
     *
     * @param buffer 摘要信息缓冲区
     * @param money 金额信息
     */
//    protected void fillBuilderLastInfo(StringBuilder buffer, MultiCurrencyMoney money) {
//        if (money == null) {
//            fillBuilderInfo(buffer, "-");
//            fillBuilderLastInfo(buffer, "-");
//        } else {
//            fillBuilderInfo(buffer, money.getAmount().toString());
//            fillBuilderLastInfo(buffer, money.getCurrencyValue());
//        }
//    }

    /**
     * 提取错误码
     *
     * @return 错误码
     */
    private String fetchResultCode() {
        return (errorContext == null) ? "-" : errorContext.toDigest();
    }

    // ~~~ getter and setter

    /**
     * Getter method for property <tt>elapse</tt>.
     * 
     * @return property value of elapse
     */
    public long getElapse() {
        return elapse;
    }

    /**
     * Setter method for property <tt>elapse</tt>.
     * 
     * @param elapse value to be assigned to property elapse
     */
    public void setElapse(long elapse) {
        this.elapse = elapse;
    }

    /**
     * Getter method for property <tt>invocationSignature</tt>.
     * 
     * @return property value of invocationSignature
     */
    public String getInvocationSignature() {
        return invocationSignature;
    }

    /**
     * Setter method for property <tt>invocationSignature</tt>.
     * 
     * @param invocationSignature value to be assigned to property invocationSignature
     */
    public void setInvocationSignature(String invocationSignature) {
        this.invocationSignature = invocationSignature;
    }

    /**
     * Getter method for property <tt>success</tt>.
     * 
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     * 
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>errorContext</tt>.
     * 
     * @return property value of errorContext
     */
    public ErrorContext getErrorContext() {
        return errorContext;
    }

    /**
     * Setter method for property <tt>errorContext</tt>.
     * 
     * @param errorContext value to be assigned to property errorContext
     */
    public void setErrorContext(ErrorContext errorContext) {
        this.errorContext = errorContext;
    }


}
