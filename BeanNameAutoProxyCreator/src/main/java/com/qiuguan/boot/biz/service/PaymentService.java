package com.qiuguan.boot.biz.service;

import com.qiuguan.boot.biz.ann.DigestLogAnnotated;
import com.qiuguan.boot.biz.consants.DigestLogConstant;

/**
 * @author created by qiuguan on 2022/1/25 18:20
 */
public interface PaymentService {

    /**
     * 支付
     * @param payRequest
     */
    @DigestLogAnnotated(name = DigestLogConstant.PAYMENT_SERVICE)
    void pay(PayRequest payRequest);
}
