package com.qiuguan.boot.aspectj.aspectj;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author created by qiuguan on 2022/1/26 17:33
 *
 * 切面也要放到容器中
 * 切面就是Advisor, 你看他的结构和Advisor一样
 * 增强器Advisor 就是切点和通知的容器，一般结构就是
 *   Advisor:
 *     -- Advice(本质上就是拦截器）
 *     -- Pointcut
 *          --- ClassFilter(用来匹配类）
 *          --- MethodMatcher(用来匹配方法）
 *
 * 切面 @Aspect 也是一样的，只不过换成了注解而已
 *    @Aspect()
 *       -- @Pointcut()
 *       -- @Before() .... (他们也是拦截器）
 */
@Aspect
public class LogAspect {

    /**
     * 切点
     * 关于切点表达式的写法，请自行查阅，内容很多，很强大
     */
    @Pointcut(value = "execution(* com.qiuguan.boot.aspectj.service.UserService.*(..))")
    public void pointcut(){}


    /**
     * 前置通知: 在目标方法执行之前执行
     * @param joinPoint 连接点
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        //方法参数
        Object[] args = joinPoint.getArgs();
        //方法名
        String name = joinPoint.getSignature().getName();

        System.out.printf("前置通知：methodName:%s, args:%s\n", name, Arrays.asList(args));
    }

    /**
     * 后置通知: 无论目标方法是否有异常，都会执行，但是无法访问目标方法的执行结果
     * @param joinPoint
     */
    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.printf("后置通知：methodName:%s\n", name);
    }

    /**
     * 返回通知: 在目标方法'正常结束'后执行
     * @param joinPoint
     * @param result, 用户接受返回值结果，必须和参数名一致
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        System.out.printf("返回通知：methodName:%s, 返回结果:%s\n", name, result);
    }

    /**
     * 异常通知: 在目标方法'发生异常'后执行
     * @param joinPoint
     * @param ex， 接受异常信息，必须和参数名一致。另外，用Exception 去接收，基本上就是捕获了所有异常，如果想捕获特定异常
     *          比如 {@link NullPointerException} , 则在参数这里直接写 {@link NullPointerException} 即可，这样其他
     *          类型的异常就会忽略
     */
    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        String name = joinPoint.getSignature().getName();
        System.out.printf("异常通知：methodName:%s, 异常信息:%s\n", name, ex);
    }

    /**
     * 环绕通知，它具有以上所有通知的功能
     * @param proceedingJoinPoint 必须接收这个参数
     */
    //@Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            //1.前置通知，目标方法之前执行
            System.out.printf("前置通知：methodName:%s\n", methodName);

            //2.执行目标方法
            result = proceedingJoinPoint.proceed();

            //3.返回通知
            System.out.printf("返回通知：methodName:%s, 返回结果:%s\n", methodName, result);
        } catch (Throwable throwable) {
            //4.异常通知
            System.out.printf("异常通知：methodName:%s, 异常信息:%s\n", methodName, throwable);
            throwable.printStackTrace();
        }

        //5.后置通知
        System.out.printf("后置通知：methodName:%s\n", methodName);

        return result;
    }
}
