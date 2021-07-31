package com.glancebar.mybatis.vo;

import com.glancebar.mybatis.entity.User;
import com.glancebar.mybatis.utils.GenderEnumTypeHandler;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author YISHEN CAI
 */
public class UpdateUserVO {

    private String username;
    private String nickname;
    private String avatar;
    private String userComment;
    private Integer gender;
    private Boolean active;
    private Boolean deleted;
    private Long birthday;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public User updateAndGetEntity(User user) {
        if (this.username != null) {
            user.setUsername(this.username);
        }
        if (this.nickname != null) {
            user.setNickname(this.nickname);
        }
        if (this.avatar != null) {
            user.setAvatar(this.avatar);
        }
        if (this.userComment != null) {
            user.setUserComment(this.userComment);
        }
        if (this.gender != null) {
            user.setGender(GenderEnumTypeHandler.genderEnumMap.get(this.gender));
        }
        // TODO: delete & active
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if (birthday != null) {
            user.setBirthday(new Date(birthday));
        }
        return user;
    }
}
