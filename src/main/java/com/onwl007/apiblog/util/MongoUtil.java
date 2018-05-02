package com.onwl007.apiblog.util;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * @author onwl007@126.com
 * @date 2018/5/1 18:55
 * @desc
 */
@Component
public class MongoUtil {

    /**
     * 校验ObjectId是否有效
     *
     * @param id
     * @return
     */
    public Boolean isValidObjectId(String id) {
        return ObjectId.isValid(id) ? true : false;
    }
}
