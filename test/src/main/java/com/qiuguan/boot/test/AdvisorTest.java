package com.qiuguan.boot.test;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author created by qiuguan on 2022/1/26 15:40
 */
public class AdvisorTest {

    public static void main(String[] args) {

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns(".*put.*");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.printf("当前存在的key为 %s, 值为 %s", args[0], args[1]);
            }
        });

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HashMap<String, Object>());
        proxyFactory.addAdvisor(advisor);

        HashMap<String, Object> proxy = (HashMap) proxyFactory.getProxy();

        proxy.put("hello", "world");

    }
}
