package com.onwl007.apiblog.mongodbconfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 22:28
 * @desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CascadeSave {
}
