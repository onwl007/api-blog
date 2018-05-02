package com.onwl007.apiblog.mongodbconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 22:42
 * @desc MongoDB级联控制
 */
@Service
public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source=event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new CascadeSaveCallback(source, mongoTemplate));
        super.onBeforeConvert(event);
    }
}
