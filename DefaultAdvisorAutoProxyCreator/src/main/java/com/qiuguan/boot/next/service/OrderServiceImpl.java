package com.qiuguan.boot.next.service;

/**
 * @author created by qiuguan on 2022/1/26 16:29
 */
public class OrderServiceImpl implements OrderService {


    @Override
    public String createOrder(String name) {
        return "创建了订单：" + name;
    }

    @Override
    public String orderDetail() {
        return "orderDetail: {orderId:1; orderName:Apple Phone 13}";
    }
}
