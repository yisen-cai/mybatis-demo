package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface SysUserMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long pkId);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);
}