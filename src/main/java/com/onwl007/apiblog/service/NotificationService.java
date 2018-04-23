package com.onwl007.apiblog.service;

import com.onwl007.apiblog.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:56
 * @desc
 */
@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
}
