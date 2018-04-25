package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:52
 * @desc
 */
public interface UserRepository extends MongoRepository<User,String> {

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    User findUserById(String id);

}
