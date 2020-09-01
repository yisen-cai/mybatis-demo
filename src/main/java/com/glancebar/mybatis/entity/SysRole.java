package com.glancebar.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class SysRole implements Serializable {
    private Long pkId;

    private String roleName;

    private Boolean isEnabled;

    private Long createBy;

    private Date createTime;

    private List<SysPrivilege> privileges;

    public List<SysPrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<SysPrivilege> privileges) {
        this.privileges = privileges;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "pkId=" + pkId +
                ", roleName='" + roleName + '\'' +
                ", isEnabled=" + isEnabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }
}