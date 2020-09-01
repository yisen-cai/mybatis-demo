package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void selectAll() {
        List<SysUser> users = sysUserMapper.selectAll();
        users.forEach(System.out::println);
        Assertions.assertNotNull(users);
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void selectByUserIdAndIsEnabled() {
        SysUser user = sysUserMapper.selectByUserIdAndIsEnabled(1L, true);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }

    @Test
    void selectUserAndRoleByUserId() {
//        SysUser user = sysUserMapper.selectUserAndRoleByUserId(2L);
//        System.out.println(user);
//        Assertions.assertNotNull(user.getRole());
    }

    @Test
    void selectUserAndRoleByUserIdInAnotherWay() {
//        SysUser user = sysUserMapper.selectUserAndRoleByUserIdInAnotherWay(2L);
//        System.out.println(user);
//        Assertions.assertNotNull(user.getRole());
    }
}