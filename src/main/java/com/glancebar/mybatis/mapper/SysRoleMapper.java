package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Long pkId);

    SysRole selectRoleById(Long pkId);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
}