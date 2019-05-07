package com.jiang.demo.permission;

import com.jiang.demo.entity.Tokens;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.TokenRepository;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
 * 登陆验证
 */
/*
* 基于Token的身份验证流程如下。

客户端使用用户名跟密码请求登录
服务端收到请求，去验证用户名与密码
验证成功后，服务端会签发一个 Token，再把这个 Token 发送给客户端
客户端收到 Token 以后可以把它存储起来，比如放在 Cookie 里或者 Local Storage 里
客户端每次向服务端请求资源的时候需要带着服务端签发的 Token
服务端收到请求，然后去验证客户端请求里面带着的 Token，如果验证成功，就向客户端返回请求的数据

* */
@Order(1)//都是值越小的 aspect 越先执行。
@Aspect
@Component
public class LoginAspect {

    //打印日志信息
    private final static Logger logger= LoggerFactory.getLogger(LoginAspect.class);

    @Autowired
    private TokenRepository tokenRepository;


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

        System.out.println("request"+request);
        System.out.println();
        Cookie[] cookies = request.getCookies();
        if(cookies.length==0){
            throw new MyException(-3, "你还没登陆！");
        }
            //获取cookie里面的token
            String token=null;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    token= cookie.getValue();
                }
            }
            System.out.println("token==="+token);
            //如果根据token查询找不到信息 抛出异常
            try {
                //根据token密匙查找整条数据
                Tokens tokens = tokenRepository.findTokensByToken(token);
                if(tokens==null){
                    throw new MyException(-6, "登陆状态不对");
                }

              /*  String username = tokens.getUserInfo().getUsername();
                System.out.println("username"+username);*/

                /*十分钟  操作需要登陆的方法  返回登陆过期消息*/
                //token保存的时间
                Date buildTime = tokens.getBuildtime();
                long time = buildTime.getTime();
                //当前时间
                Date nowTime = new Date();
                long time1 = nowTime.getTime();

                System.out.println("时间差："+(time1 - time));

                if (time1 - time >= 60000) {
                    //时间过期
                    throw new MyException(-4, "登陆过期，请重新登陆！");
                }
                //token信息存在  并且未过期  执行需要登陆的方法  返回数据
                result = point.proceed();

            }catch (Exception e){
                throw new MyException(-3, "你还没登陆！");
            } catch (Throwable throwable) {
                throw new MyException(-6, "接口出问题！");
            }

        return result;
    }


}
