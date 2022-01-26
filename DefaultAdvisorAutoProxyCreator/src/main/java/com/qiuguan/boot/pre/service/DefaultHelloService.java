package com.qiuguan.boot.pre.service;

/**
 * @author created by qiuguan on 2022/1/26 15:57
 */
public class DefaultHelloService implements HelloService {

    @Override
    public String hello() {
        return "DEFAULT HELLO SERVICE....";
    }

    @Override
    public void sayGoodBye() {
        System.out.println("good bye.....");
    }
}
