package com.qiuguan.boot;

import com.qiuguan.boot.test.processor.Audiar;
import com.qiuguan.boot.test.test.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author created by qiuguan on 2022/1/25 17:39
 *
 * xml 配置，主要是熟悉xml配置
 *
 * biz 模块，是参考了实际的业务需求
 * test 自己用来测试的，检查是否创建了代理等等，可以debug
 */
@ImportResource(value = "classpath:applicationContext.xml")
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MainApplication.class, args);


        Audiar bean = ctx.getBean(Audiar.class);
        bean.drive();

        TestService bean1 = ctx.getBean(TestService.class);
        bean1.test();
    }
}
