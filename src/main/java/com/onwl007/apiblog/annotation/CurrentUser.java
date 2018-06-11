package com.onwl007.apiblog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author onwl007@126.com
 * @date 2018/6/10 20:37
 * @desc 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
