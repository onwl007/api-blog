package com.onwl007.apiblog.repository;

import com.google.gson.Gson;
import com.onwl007.apiblog.ApiBlogApplication;
import com.onwl007.apiblog.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author onwl007@126.com
 * @date 2018/5/28 20:17
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiBlogApplication.class)
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void testgetCommentByArticleId(){
        Pageable pageable=new PageRequest(0,10);
        Page<Comment> comments=commentRepository.findAllByArticle_Id("5b0a275dac807588b40ad379",pageable);
        Gson gson=new Gson();
        System.out.println(gson.toJson(comments));
    }
}
