package com.onwl007.apiblog.domain;

import com.onwl007.apiblog.vo.OptionLinks;
import org.springframework.data.annotation.Id;

/**
 * @author onwl007@126.com
 * @date 2018/4/21 14:47
 * @desc 设置
 */
public class Option {

    @Id
    private String id;
    private String welcome;
    private String descriprion;
    private String hobby;
    private String skill;
    private String music;
    private String location;
    private String company;
    private OptionLinks links;
    private String musicId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public OptionLinks getLinks() {
        return links;
    }

    public void setLinks(OptionLinks links) {
        this.links = links;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }
}
