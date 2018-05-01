package com.onwl007.apiblog.service;

import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:57
 * @desc
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * 查询所有用户
     * @return
     */
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    /**
     * 根据用户id查询
     * @param id
     * @return
     */
    public User getUserById(String id){
        return userRepository.findUserById(id);
    }

    /**
     * 查询管理员
     * @param role
     * @return
     */
    public User getUserByRole(int role){
        return userRepository.findUserByRole(role);
    }

    /**
     * 更新或保存用户
     * @param user
     */
    public void updateUser(User user){
        userRepository.save(user);
    }

    /**
     * 根据id删除用户
     * @param id
     */
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
