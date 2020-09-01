package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysPrivilege;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysPrivilegeMapper {
    int deleteByPrimaryKey(Long pkId);

    // return non increment id
    int insert(SysPrivilege record);

    @Insert("insert into sys_privilege (pk_id, privilege_name, privilege_url\n" +
            "      )\n" +
            "    values (#{pkId,jdbcType=BIGINT}, #{privilegeName,jdbcType=VARCHAR}, #{privilegeUrl,jdbcType=VARCHAR}\n" +
            "      )")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",
                keyProperty = "pkId",
                resultType = Long.class,
                before = false)
    int insertWithAnnotation(SysPrivilege record);


    // return increment id
    @Insert("insert into sys_privilege(privilege_name, privilege_url) value (#{privilegeName}, #{privilegeUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "pkId")
    int insertAndReturnAutoIncrementId(SysPrivilege sysPrivilege);

    SysPrivilege selectByPrimaryKey(Long pkId);

    /**
     * 用于在角色详情的嵌套查询，懒加载方式查询
     * @param roleId
     * @return
     */
    List<SysPrivilege> selectPrivilegesByRoleId(Long roleId);

    List<SysPrivilege> selectAll();

    int updateByPrimaryKey(SysPrivilege record);
}