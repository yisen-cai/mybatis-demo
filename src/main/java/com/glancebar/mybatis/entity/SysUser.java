package com.glancebar.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glancebar.mybatis.enums.Enabled;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class SysUser implements Serializable {

    private Long pkId;

    private String username;

    private String password;

    private String email;

    private String description;

    private String avatar;
    private List<SysRole> roles;

    private Date createTime;

    private Enabled isEnabled;
    private Boolean isDeleted;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Enabled getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Enabled isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "pkId=" + pkId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role=" + roles +
                ", createTime=" + createTime +
                ", isEnabled=" + isEnabled +
                ", isDeleted=" + isDeleted +
                '}';
    }
}