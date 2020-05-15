package com.foo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class ServiceLogAspect {
    public static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);
    /**
     * AOP通知：
     * 1.前置通知：在方法调用前执行
     * 2.后置通知：在方法正常调用后执行
     * 3.环绕通知：在方法调用之前和之后执行
     * 4.异常通知：如果方法调用过程中发生异常则通知
     * 5.最终通知：在方法调用之后执行
     */
    /**
     * 切面表达式
     * execution 代表执行的表达式主题
     * 第一处 * 代表方法返回类型 *代表所有类型（public）
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名 *代表所有类
     * 第五处 *(..) * 代表类中的方法名 (..)表示方法中的任何参数
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.foo.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("===== 开始执行 {}.{} ====="
                ,joinPoint.getTarget().getClass().getName()
                ,joinPoint.getSignature().getName());
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 执行目标service
        Object result = joinPoint.proceed();
        // 结束时间
        long endTime = System.currentTimeMillis();
        // 时间差
        long takeTime = endTime-startTime;
        if(takeTime>3000){
            log.error("===== 执行结束 耗时:{}ms =====",takeTime);
        }else if (takeTime>2000){
            log.error("===== 执行结束 耗时:{}ms =====",takeTime);
        }else {
            log.info("===== 执行结束 耗时:{}ms =====",takeTime);
        }
        return result;
    }
}
