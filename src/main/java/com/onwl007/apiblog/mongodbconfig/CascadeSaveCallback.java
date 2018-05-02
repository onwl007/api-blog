package com.onwl007.apiblog.mongodbconfig;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 22:43
 * @desc
 */
public class CascadeSaveCallback implements ReflectionUtils.FieldCallback {

    private Object source;
    private MongoTemplate mongoTemplate;

    public CascadeSaveCallback(Object source, MongoTemplate mongoTemplate) {
        super();
        this.source = source;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if(field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)){
            final Object fieldValue = field.get(source);

            if(fieldValue != null){
                FieldCallback fc = new FieldCallback();
                ReflectionUtils.doWithFields(fieldValue.getClass(), fc);

                mongoTemplate.save(fieldValue);
            }
        }
    }

}
