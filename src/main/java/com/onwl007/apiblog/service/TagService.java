package com.onwl007.apiblog.service;

import com.onwl007.apiblog.domain.Tag;
import com.onwl007.apiblog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onwl007@126.com
 * @date 2018/4/23 21:41
 * @desc
 */
@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    /**
     * 查询所有的标签并按照创建时间倒序排列
     *
     * @return
     */
    public List<Tag> listTags() {
        return tagRepository.findAllByOrderByCreateAtDesc();
    }

    /**
     * 根据标签id查询
     *
     * @param id
     * @return
     */
    public Tag getTagById(String id) {
        return tagRepository.findTagById(id);
    }

    /**
     * 根据标签的名称查询
     *
     * @param name
     * @return
     */
    public Tag getTagByName(String name) {
        return tagRepository.findTagByName(name);
    }

    /**
     * 创建或更新标签
     *
     * @param tag
     */
    public void createTag(Tag tag) {
        tagRepository.save(tag);
    }

    /**
     * 根据标签id删除
     *
     * @param id
     */
    public void deleteById(String id) {
        tagRepository.deleteById(id);
    }
}
