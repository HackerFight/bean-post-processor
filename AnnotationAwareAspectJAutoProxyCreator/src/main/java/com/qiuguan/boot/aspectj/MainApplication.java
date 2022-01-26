package com.qiuguan.boot.aspectj;

import com.qiuguan.boot.aspectj.service.UserService;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author created by qiuguan on 2022/1/26 17:30
 *
 * {@link https://blog.csdn.net/qq_41649001/article/details/107022756 }
 *
 * springboot 的自动配置包下的 META-INF 目录下的 spirng.factories 文件中导入了自动配置类
 * {@link org.springframework.boot.autoconfigure.aop.AopAutoConfiguration}
 *
 * 所以我们也不用在写 {@link org.springframework.context.annotation.EnableAspectJAutoProxy}
 *
 * 他会判断是否导入了 org.aspectj.weaver.Advice， 从而决定使用哪个AOP后置处理器
 * @see InfrastructureAdvisorAutoProxyCreator (基础的后置处理器，应该就是用来处理非 AspectJ 的）
 * @see AspectJAwareAdvisorAutoProxyCreator  （优先级第二的后置处理器，猜测应该是用来处理xml配置的 AspectJ)
 * @see AnnotationAwareAspectJAutoProxyCreator (优先级最高的后置处理器，用来处理注解类型的 AspectJ)
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MainApplication.class, args);

        UserService bean = ctx.getBean(UserService.class);
        bean.read(5, 6);
    }
}
