package com.qiuguan.boot;

import com.qiuguan.boot.next.service.OrderService;
import com.qiuguan.boot.pre.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author created by qiuguan on 2022/1/26 15:54
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(MainApplication.class, args);

        /**
         * 测试 pre 包的逻辑
         * 这个包下的逻辑主要是代理对象通过 {@link org.springframework.aop.framework.ProxyFactoryBean}
         * 去创建
         */
        //preTest(ctx);

        nextTest(ctx);

    }

    private static void nextTest(ConfigurableApplicationContext ctx) {
        OrderService bean = ctx.getBean(OrderService.class);
        bean.createOrder("Iphone13");

        System.out.println("\n\n");

//        String s = bean.orderDetail();
//        System.out.println(s);
    }


    private static void preTest(ConfigurableApplicationContext ctx) {

        //返回的是一个代理对象
        HelloService bean = (HelloService) ctx.getBean("helloProxy");
        //这个方法会匹配到代理
        //System.out.println(bean.hello());

        //这个方法就是普通方法
        bean.sayGoodBye();
    }
}
