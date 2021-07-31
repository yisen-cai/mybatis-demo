package com.glancebar.mybatis.entity;

import com.glancebar.mybatis.enums.GenderEnum;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * @author YISHEN CAI
 */
public class User {
    private Long id;
    private String avatar;
    private String username;
    private String nickname;
    private GenderEnum gender;
    private Date birthday;
    private String userComment;
    private Timestamp createTime;
    private Timestamp updateTime;
    private boolean deleted;
    private boolean active;

    public User() {
    }

    public User(Long id, String avatar, String username, String nickname, GenderEnum gender, Date birthday, String userComment, Timestamp createTime, Timestamp updateTime, boolean deleted, boolean active) {
        this.id = id;
        this.avatar = avatar;
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.userComment = userComment;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.active = active;
    }

    public User(String avatar, String username, String nickname, GenderEnum gender, Date birthday, String userComment, Timestamp createTime, Timestamp updateTime, boolean deleted, boolean active) {
        this.avatar = avatar;
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.userComment = userComment;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", userComment='" + userComment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", active=" + active +
                '}';
    }
}
