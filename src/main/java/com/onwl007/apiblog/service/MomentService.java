package com.onwl007.apiblog.service;

import com.onwl007.apiblog.domain.Moment;
import com.onwl007.apiblog.repository.MomentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 22:54
 * @desc
 */
@Service
public class MomentService {

    @Autowired
    MomentRepository momentRepository;

    /**
     * 查询动态列表并按照创建时间降序排列
     * @return
     */
    public List<Moment> listMoments(){
        return momentRepository.findAllByOrderByCreateAtDesc();
    }

    public Moment getMomentById(String id){
        return momentRepository.findMomentById(id);
    }

    /**
     * 创建或更新动态
     * @param moment
     */
    public void createMoment(Moment moment){
        momentRepository.save(moment);
    }

    /**
     * 根据id删除动态
     * @param id
     */
    public void deleteMoment(String id){
        momentRepository.deleteById(id);
    }
}
