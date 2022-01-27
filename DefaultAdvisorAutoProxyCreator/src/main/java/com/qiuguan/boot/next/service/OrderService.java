package com.qiuguan.boot.next.service;

/**
 * @author created by qiuguan on 2022/1/26 16:28
 */
public interface OrderService {

    /**
     * create order
     */
    String createOrder(String name);

    /**
     * order detail
     * @return
     */
    String orderDetail();
}
