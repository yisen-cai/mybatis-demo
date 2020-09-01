package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysPrivilege;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface SysPrivilegeMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(SysPrivilege record);

    SysPrivilege selectByPrimaryKey(Long pkId);

    List<SysPrivilege> selectAll();

    int updateByPrimaryKey(SysPrivilege record);
}