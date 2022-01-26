package com.qiuguan.boot.aspectj.config;

import com.qiuguan.boot.aspectj.aspectj.LogAspect;
import com.qiuguan.boot.aspectj.service.UserService;
import com.qiuguan.boot.aspectj.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author created by qiuguan on 2022/1/26 17:47
 */
@Configuration
public class AspectConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
