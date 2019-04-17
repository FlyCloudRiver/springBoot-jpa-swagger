package com.jiang.demo.permission;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Author: 江云飞
 * Date:   2019/4/17
 */


@Target(ElementType.METHOD) //Target注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分
@Retention(RetentionPolicy.RUNTIME) //Retention注解决定MyAnnotation注解的生命周期
@Documented
public @interface Permission {
    String authorities() default "Admin";
}
