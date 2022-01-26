/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.qiuguan.boot.biz.log;


/**
 * 普通支付服务摘要日志
 *
 * @author liangjun.zlj@alibaba-inc.com
 * @author liangjun.zhong
 * @author peng.lanqp
 * @version $Id: PaymentServiceDigestLog.java, v 0.6 2016年9月28日 下午3:35:32 liangjun.zhong Exp $
 */
public class PaymentServiceDigestLog extends BaseDigestLog {

    /** 业务单据号 */
    private String             transId;

    /**
     * 交易类型
     * @see com.alipay.fc.common.lang.enums.TransType
     */
    private String             transType;

    /** 收银付款请求ID */
    private String             cashierRequestId;

    /** 业务金额 */
    //private MultiCurrencyMoney transAmount;

    /** 终端类型 */
    private String             terminalType;

    /** 付款方ID */
    private String             payerUserId;

    /** 商户ID (对于收单场景需要填写，其他业务选填) */
    private String             merchantId;

    /** 产品码：云产品定义的产品编码，各业务域指定 */
    private String             productCode;

    /** 应用层业务事件码。 */
    private String             pdEventCode;

    /** 支付选项，组合支付时使用“|”分隔 */
    private String             payOptions;

    /** 发行国家 */
    private String             issuingCountry;

    /**
     * @see BaseDigestLog#composeBizInfo(StringBuilder)
     */
    @Override
    protected void composeBizInfo(StringBuilder buffer) {
        buffer.append("(");
        {
            fillBuilderInfo(buffer, transId);
            fillBuilderInfo(buffer, cashierRequestId);
           // fillBuilderInfo(buffer, transAmount);
            fillBuilderInfo(buffer, terminalType);
            fillBuilderInfo(buffer, payerUserId);
            fillBuilderInfo(buffer, merchantId);
            fillBuilderInfo(buffer, productCode);
            fillBuilderInfo(buffer, pdEventCode);
            fillBuilderInfo(buffer, payOptions);
            fillBuilderInfo(buffer, transType);
            fillBuilderLastInfo(buffer, issuingCountry);
        }
        buffer.append(")");
    }

    /**
     * Setter method for property <tt>cashierRequestId</tt>.
     * 
     * @param cashierRequestId value to be assigned to property cashierRequestId
     */
    public void setCashierRequestId(String cashierRequestId) {
        this.cashierRequestId = cashierRequestId;
    }

    /**
     * Setter method for property <tt>transId</tt>.
     * 
     * @param transId value to be assigned to property transId
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * Setter method for property <tt>transType</tt>.
     * 
     * @param transType value to be assigned to property transType
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * Setter method for property <tt>transAmount</tt>.
     * 
     * @param transAmount value to be assigned to property transAmount
     */
    //public void setTransAmount(MultiCurrencyMoney transAmount) {
//        this.transAmount = transAmount;
//    }

    /**
     * Setter method for property <tt>terminalType</tt>.
     * 
     * @param terminalType value to be assigned to property terminalType
     */
    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    /**
     * Setter method for property <tt>payerUserId</tt>.
     * 
     * @param payerUserId value to be assigned to property payerUserId
     */
    public void setPayerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
    }

    /**
     * Setter method for property <tt>payOptions</tt>.
     * 
     * @param payOptions value to be assigned to property payOptions
     */
    public void setPayOptions(String payOptions) {
        this.payOptions = payOptions;
    }

    /**
     * Setter method for property <tt>productCode</tt>.
     * 
     * @param productCode value to be assigned to property productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * Setter method for property <tt>pdEventCode</tt>.
     * 
     * @param pdEventCode value to be assigned to property pdEventCode
     */
    public void setPdEventCode(String pdEventCode) {
        this.pdEventCode = pdEventCode;
    }

    /**
     * Setter method for property <tt>merchantId</tt>.
     * 
     * @param merchantId value to be assigned to property merchantId
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * Setter method for property <tt>issuingCountry</tt>.
     * 
     * @param issuingCountry value to be assigned to property issuingCountry
     */
    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

}
