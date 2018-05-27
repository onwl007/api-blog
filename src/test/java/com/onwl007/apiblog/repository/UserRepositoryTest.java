package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.ApiBlogApplication;
import com.onwl007.apiblog.domain.User;
import com.onwl007.apiblog.vo.Github;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiBlogApplication.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

}
