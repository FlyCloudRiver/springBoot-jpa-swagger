package com.jiang.demo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: 江云飞
 * Date:   2019/4/16
 */

@Aspect
@Component
public class HttpAspect {

    //打印日志信息
    private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);


    @Before("execution(public * com.jiang.demo.controller.BigCategoryController.selectAll(..))")//某个方法之前
    //@Before("execution(public * com.jiang.demo.controller.BigCategoryController.*(..))")//所有方法之前
    public void log(){
        logger.info("打印日志信息");
        System.out.println("我开始拦截了");
    }

    @After("execution(public * com.jiang.demo.controller.BigCategoryController.selectAll(..))")
    public void doAfter(){

    }

    //省写后面一长串
    @Pointcut("execution(public * com.jiang.demo.controller.BigCategoryController.*(..))")
    public void log1(){

    }

    @Before("log1()")
    public void doBefore(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        logger.info("url={}",request.getRequestURL());
    }

    @After("log1()")
    public void doAfter2(){

    }
}
