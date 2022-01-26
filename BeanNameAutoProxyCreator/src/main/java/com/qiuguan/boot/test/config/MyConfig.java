package com.qiuguan.boot.test.config;

import com.qiuguan.boot.test.processor.Audiar;
import com.qiuguan.boot.test.processor.Car;
import com.qiuguan.boot.test.processor.MyMethodInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author created by qiuguan on 2022/1/25 15:12
 */
@Configuration
public class MyConfig {

    @Bean
    public MethodInterceptor myMethodInterceptor(){
        return new MyMethodInterceptor();
    }

    @Bean
    public Car car(){
        return new Car();
    }

    @Bean
    public Audiar audiar(){
        return new Audiar();
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        /**
         * 可以使用通配符，"*ar"匹配以 ar 结尾的beanName
         * 可以使用 *， 则所有beanName 都会创建代理
         * “userService,orderService” 匹配具体的beanName
         */
        //creator.setBeanNames("*ar");
        creator.setBeanNames("audiar");
        /**
         * 不配置拦截器，不会报错，只是被代理的对象在执行目标方法时，本质上就是一个普通方法了
         * 所以，我们肯定都要去定义拦截器，比如日志打印，耗时统计等等。
         * 可以指定多个
         */
        creator.setInterceptorNames("myMethodInterceptor");
        //强制使用 CGLIB 代理
        creator.setOptimize(true);

        return creator;
    }
}
