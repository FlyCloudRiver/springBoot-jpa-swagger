package com.jiang.demo.permission;

import com.jiang.demo.entity.Tokens;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.TokenRepository;
import com.jiang.demo.service.UserInfoService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/16
 */
@Order(1)//都是值越小的 aspect 越先执行。
@Aspect
@Component
public class HttpAspect2 {

    //打印日志信息
    private final static Logger logger= LoggerFactory.getLogger(HttpAspect2.class);

    @Autowired
    private TokenRepository tokenRepository;

    /**
     * 定义切点
     */
   /* @Pointcut("execution(public * com.jiang.demo.controller.UserInfoController.*(..))")*/
    /**
     * 定义拦截规则：拦截标有com.christ.annotation.Login类中注解的所有方法
     */
    @Pointcut("@annotation(com.jiang.demo.permission.Login)")
    public void privilege(){

    }

    @Around("privilege()")
    @ResponseBody
    public Object Interceptor(ProceedingJoinPoint point){

        //正在被通知的方法相关信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        //返回的结果
        Object result = null;
        //返回方法参数
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Cookie[] cookies = request.getCookies();
        try {
            //获取cookie
            Integer tokenId = null;
            String token=null;
            for (Cookie cookie : cookies) {
                if (cookie.getName() == "tookeId") {
                    tokenId = Integer.valueOf(cookie.getValue());
                }
                if(cookie.getName() == "token"){
                    token= cookie.getValue();
                }
            }
            //去查询token  密匙判断
            Integer token1 = tokenRepository.getToken(token);
            if(token1<1){
                throw new MyException(-4, "你还没登陆！");
            }

            Tokens tokens = tokenRepository.findById(tokenId).get();

            Date buildTime = tokens.getBuildtime();
            long time = buildTime.getTime();

            /*十分钟  操作需要登陆的方法  返回登陆过期消息*/
            Date nowTime = new Date();
            long time1 = nowTime.getTime();

            if (time1 - time <= 60000) {
                //时间过期
                throw new MyException(-3, "登陆过期！");
            }
            result = point.proceed();

        }catch (Exception e){
            throw new MyException(-4, "你还没登陆！");
        } catch (Throwable throwable) {
            result = new MyException(-2, "发生异常："+throwable.getMessage());
        }

        return result;
    }


}
