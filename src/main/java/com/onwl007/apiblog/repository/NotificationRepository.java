package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:48
 * @desc
 */
public interface NotificationRepository extends MongoRepository<Notification,String> {
}
