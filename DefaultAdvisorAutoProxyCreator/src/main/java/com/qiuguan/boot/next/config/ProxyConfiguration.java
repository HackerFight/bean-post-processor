package com.qiuguan.boot.next.config;

import com.qiuguan.boot.next.advice.MyInterceptor;
import com.qiuguan.boot.next.service.OrderService;
import com.qiuguan.boot.next.service.OrderServiceImpl;
import com.qiuguan.boot.pre.service.HelloService;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author created by qiuguan on 2022/1/26 16:30
 */
@Configuration
public class ProxyConfiguration {

    /**
     * 创建需要代理的对象
     * @return
     */
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl();
    }

    /**
     * 创建 Advice
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }


    @Bean("myAdvisor")
    public Advisor advisor(){
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        //如果指定，则匹配 matches 方法，如果不指定，则就是所有的bean都匹配，然后继续看方法匹配
        advisor.setClassFilter(new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return OrderService.class.isAssignableFrom(clazz);
            }
        });

        //匹配方法，如果不指定上面的ClassFilter, 则匹配所有bean 的 createOrder和 orderDetail方法，
        // 如果指定了，则匹配具体bean的 createOrder 方法 和 orderDetail 方法
        advisor.setMappedNames("createOrder","orderDetail");

        advisor.setAdvice(myInterceptor());

        return advisor;
    }

    /**
     * 不在通过 {@link org.springframework.aop.framework.ProxyFactoryBean} 去创建代理对象
     * 而是通过 {@link DefaultAdvisorAutoProxyCreator}, 会变的非常简单
     * 它会从容器中查找Advisor, 而spring 也给我们提供了很多直接用的 Advisor
     *
     * @see NameMatchMethodPointcutAdvisor
     * @see AspectJExpressionPointcutAdvisor
     * @see DefaultPointcutAdvisor
     * @see DefaultBeanFactoryPointcutAdvisor
     * @see RegexpMethodPointcutAdvisor
     *
     * 从命名上看，不难发现，这些Advisor 以及内置了 Pointcut 的功能，所以我们重点是关注 Advcie(这个一般都是我们自己写
     * 它就是拦截器）
     *
     * 一个Advisor 就是一个切面，里面包含一个 Pointcut 和 Advice
     * AspectJ 注解标注的 @AspectJ 是一个切面也就是Advisor, 这个注解标注的类，里面就是 @Pointcut 注解和 @Advice注解
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();

        //去发现特定前缀的 Advisor, 这两个要一起使用
        creator.setUsePrefix(true);
        creator.setAdvisorBeanNamePrefix("my");

        return creator;
    }
}
