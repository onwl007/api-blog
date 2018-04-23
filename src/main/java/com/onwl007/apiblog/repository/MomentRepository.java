package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Moment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:47
 * @desc
 */
public interface MomentRepository extends MongoRepository<Moment,String> {
}
