package com.jiang.demo.permission;

/**
 * Author: 江云飞
 * Date:   2019/4/18
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Login {

    /**
     * 是否已登录
     * @return
     */
    boolean isLogin() default true;
}
