package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.User;
import com.glancebar.mybatis.enums.GenderEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private User user = new User("https://staff-test.oss-cn-beijing.aliyuncs.com/avatar/avatar.png",
            "user",
            "nickname",
            GenderEnum.MALE,
            new Date(System.currentTimeMillis()),
            "comment",
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()),
            false,
            true);

    @Test
    void insert() {
        long id = userMapper.insert(user);
        Assertions.assertNotEquals(id, 0);
    }

    @Test
    void insertUser() {
        userMapper.insertUser(user);
        Assertions.assertNotNull(user.getId());
    }

    @Test
    void findAll() {
        List<User> users = userMapper.findAll();
        Assertions.assertTrue(users.size() > 0);
        users.forEach(System.out::println);
    }
}