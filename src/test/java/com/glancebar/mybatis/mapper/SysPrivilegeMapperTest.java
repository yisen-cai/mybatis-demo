package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysPrivilege;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysPrivilegeMapperTest {

    @Autowired
    private SysPrivilegeMapper sysPrivilegeMapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
        SysPrivilege sysPrivilege = new SysPrivilege();
        sysPrivilege.setPrivilegeName("new Name");
        sysPrivilege.setPrivilegeUrl("/path");
        int result = sysPrivilegeMapper.insert(sysPrivilege);
        System.out.println(sysPrivilege);
        System.out.println("Insert result is: " + result);
        Assertions.assertEquals(1, result);
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void selectAll() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void insertAndReturnAutoIncrementId() {
        SysPrivilege sysPrivilege = new SysPrivilege();
        sysPrivilege.setPrivilegeName("new Name");
        sysPrivilege.setPrivilegeUrl("/path");
        int resultId = sysPrivilegeMapper.insertAndReturnAutoIncrementId(sysPrivilege);
        System.out.println(sysPrivilege);
        System.out.println("Insert result is: " + resultId);
        Assertions.assertEquals(1, resultId);
    }
}