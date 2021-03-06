package org.zerock.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j
@Component
public class LogAdvice {

    @Before("execution(* org.zerock.service.SampleService*.*(..))")
    public void logBefore(){
        log.info("===============");
    }

    @Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2){

        log.info("str1: " + str1);
        log.info("str2: " + str2);
    }

    // 예외를 발생시킨 경우
    @AfterThrowing(pointcut= "execution(* org.zerock.service.SampleService*.*(..))", throwing = "exception")
    public void logException(Exception exception){

        log.info("Exception..........!!");
        log.info("exception: " + exception);

    }

    // Around
    @Around("execution(* org.zerock.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp){

        Long start = System.currentTimeMillis();

        log.info("Target: " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        // invoke method
        Object result = null;

        try{
            result = pjp.proceed();
        } catch(Throwable e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        log.info("TIME: " + (end - start));

        // result 는?
        log.info("result: " + result);
        return result;
    }



}
