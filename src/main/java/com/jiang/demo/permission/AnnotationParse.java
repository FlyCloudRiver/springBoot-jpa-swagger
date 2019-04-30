package com.jiang.demo.permission;

import java.lang.reflect.Method;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */
public class AnnotationParse {
    /***
     * 解析权限注解
     *
     *
     */
    public static String privilegeParse(Method method) throws Exception {
        //获取该方法
        if(method.isAnnotationPresent(Permission.class)){
            Permission annotation = method.getAnnotation(Permission.class);
            return annotation.authorities();
        }
        return null;
    }
}
