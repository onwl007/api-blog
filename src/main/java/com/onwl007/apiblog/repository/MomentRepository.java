package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Moment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:47
 * @desc
 */
public interface MomentRepository extends MongoRepository<Moment,String> {

    /**
     * 查询动态列表并按照创建时间降序排列
     * @return
     */
    List<Moment> findAllByOrderByCreateAtDesc();

    /**
     * 根据id查找动态
     * @return
     */
    Moment findMomentById(String id);

}
