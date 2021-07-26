package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysUser;
import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long pkId);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);
}