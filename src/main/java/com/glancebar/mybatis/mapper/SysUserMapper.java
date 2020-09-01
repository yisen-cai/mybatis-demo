package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Component
public interface SysUserMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long pkId);

    List<SysUser> selectAll();

    SysUser selectUserAndRoleByUserId(Long pkId);

    SysUser selectUserAndRoleByUserIdInAnotherWay(Long pkId);

    int updateByPrimaryKey(SysUser record);

    //   Query with multiple params
    //   Default is param1 param2
    //   where pk_id = #{param1} and is_enabled = #{param2}
    //   Use param name must use @Param("userId")
    SysUser selectByUserIdAndIsEnabled(@Param("userId") Long userId, @Param("isEnabled") Boolean isEnabled);
}