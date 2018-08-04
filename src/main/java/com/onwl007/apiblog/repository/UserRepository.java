package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:52
 * @desc
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 查询管理员
     *
     * @param role
     * @return
     */
    User findUserByRole(int role);

    /**
     * 根据用户名称查询
     *
     * @param name
     * @return
     */
    User findUserByName(String name);

    Optional<User> findByName(String name);

}
