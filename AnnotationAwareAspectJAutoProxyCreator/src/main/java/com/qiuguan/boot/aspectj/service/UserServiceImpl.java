package com.qiuguan.boot.aspectj.service;

/**
 * @author created by qiuguan on 2022/1/26 17:32
 */
public class UserServiceImpl implements UserService {

    @Override
    public int read(int a, int b) {
        a = 9 / 0;
        return a + b;
    }
}
