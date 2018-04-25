package com.onwl007.apiblog.service;

import com.onwl007.apiblog.domain.Option;
import com.onwl007.apiblog.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:56
 * @desc
 */
@Service
public class OptionService {

    @Autowired
    OptionRepository optionRepository;

    /**
     * 保存或更新option
     * @param option
     */
    public void saveOption(Option option){
        optionRepository.save(option);
    }


    /**
     * 查询一条数据
     * @return
     */
    public Option getOption(){
        return optionRepository.findAll().get(0);
    }
}
