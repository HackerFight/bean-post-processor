package com.qiuguan.boot.pre.config;

import com.qiuguan.boot.pre.advice.HelloMethodInterceptor;
import com.qiuguan.boot.pre.service.DefaultHelloService;
import com.qiuguan.boot.pre.service.HelloService;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author created by qiuguan on 2022/1/26 15:57
 */
@Configuration
public class ProxyConfig {

    @Bean
    public HelloService helloService(){
        return new DefaultHelloService();
    }

    @Bean
    public HelloMethodInterceptor helloInterceptor(){
        return new HelloMethodInterceptor();
    }


    @Bean(name = "helloAdvisor")
    public Advisor advisor(){
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        //advisor.setClassFilter();
        /**
         * 如果指定ClassFilter,则就是匹配ClassFilter过滤通过后bean的 hello 方法
         * 如果没有指定ClassFilter, 则去匹配所有bean的 hello 方法
         */
        advisor.setMappedNames("hello");
        advisor.setAdvice(helloInterceptor());

        return advisor;
    }

    /**
     *
     * 那么，如何让上面的增强器工作呢？需要借助 ProxyFactoryBean（方式一，比较麻烦，不好拓展）
     * 注意看主配置类的测试
     *
     * 创建代理对象
     *
     * ctx.getBean(HelloService.class); 是报错的，会有两个bean, 一个是 “helloService", 一个是 ”helloProxy“
     *
     * 「可以看到我们对每个生成的代理对象都要配置对应的ProxyFactoryBean，然后从容器中获取代理对象来使用」。
     * 当代理对象很少时还能应付，当代理对象很多时，那还不得累到吐血。有没有什么简单的办法呢？
     *
     * Spring肯定也想到了这个问题，所以他提供了如下一个类DefaultAdvisorAutoProxyCreator来实现自动代理，
     * 我们将这个类放入Spring容器即可, 请参考 next 包
     */
    @Bean(name = "helloProxy")
    public ProxyFactoryBean proxyFactoryBean(HelloService helloService){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(helloService);
        proxyFactoryBean.setInterceptorNames("helloAdvisor");

        return proxyFactoryBean;
    }
}
