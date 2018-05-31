package com.onwl007.apiblog.util;

import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author onwl007@126.com
 * @date 2018/5/31 21:01
 * @desc 检查用户是否存在
 */
@Component
public class CheckUser {

    @Autowired
    private UserRepository userRepository;

    /**
     * 检查该用户是否存在 存在 true | 不存在 false
     *
     * @param user
     * @return
     */
    public boolean checkAuthor(User user) {
        User isExisted = userRepository.findUserByName(user.getName());
        return isExisted != null ? true : false;
    }
}
