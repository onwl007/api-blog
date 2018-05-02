package com.onwl007.apiblog.mongodbconfig;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 22:25
 * @desc
 */
public class FieldCallback implements ReflectionUtils.FieldCallback {

    private boolean isFound;

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(Id.class)) {
            isFound = true;
        }

    }

    public boolean isFound() {
        return isFound;
    }

}
