package com.onwl007.apiblog.repository;

import com.google.gson.Gson;
import com.onwl007.apiblog.ApiBlogApplication;
import com.onwl007.apiblog.domain.Category;
import com.onwl007.apiblog.vo.Extend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 20:49
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiBlogApplication.class)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testSave(){
        Category category=new Category();
        category.setName("code");
        category.setList(1);
        category.setCreateAt(new Date());
        category.setDescription("Talk is cheap,show me your code");
        Extend extend=new Extend();
        extend.setKey("icon");
        extend.setValue("code");
        category.setExtend(extend);
        categoryRepository.save(category);
    }

    @Test
    public void testFindAllByOrderByListAsc(){
        Gson gson=new Gson();
        List<Category> categories=categoryRepository.findAllByOrderByListAsc();
        System.out.println(gson.toJson(categories));
    }
}
