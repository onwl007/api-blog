package com.onwl007.apiblog.repository;

import com.onwl007.apiblog.domain.Option;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:51
 * @desc
 */
public interface OptionRepository extends MongoRepository<Option,String> {
}
