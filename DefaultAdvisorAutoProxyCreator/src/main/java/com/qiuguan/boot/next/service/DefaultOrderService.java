package com.qiuguan.boot.next.service;

/**
 * @author created by qiuguan on 2022/1/26 16:46
 */
public class DefaultOrderService implements OrderService {

    @Override
    public void createOrder() {
        System.out.println("创建了默认的订单");
    }

    @Override
    public String orderDetail() {
        return "没有详情";
    }
}
