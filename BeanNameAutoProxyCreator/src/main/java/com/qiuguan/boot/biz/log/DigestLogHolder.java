/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.qiuguan.boot.biz.log;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地线程变量缓存的日志对象。
 * 
 * @author chunmao.zhu
 * @author peng.lanqp
 * @version $Id: DigestLogHolder.java, v 0.2 2014-7-15 下午2:49:52 peng.lanqp Exp $
 */
public final class DigestLogHolder {

    /** 本地线程 */
    private static ThreadLocal<List<BaseDigestLog>> digestLogLocal = new ThreadLocal<List<BaseDigestLog>>();

    /**
     * 禁用构造函数
     */
    private DigestLogHolder() {
        // 禁用构造函数
    }

    /**
     * 取得摘要日志。
     * 
     * @return 摘要日志
     */
    public static List<BaseDigestLog> get() {
        return digestLogLocal.get();
    }

    /**
     * 置入操作日志上下文。
     * 
     * @param digestLog 摘要日志
     */
    public static void add(BaseDigestLog digestLog) {
        if (get() == null) {
            List<BaseDigestLog> logs = new ArrayList<BaseDigestLog>();
            digestLogLocal.set(logs);
        }

        get().add(digestLog);
    }

    /**
     * 清理上下文。
     */
    public static void clear() {
        digestLogLocal.set(null);
    }
}
