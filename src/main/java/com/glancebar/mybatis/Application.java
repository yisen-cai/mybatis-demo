package com.glancebar.mybatis;

import com.glancebar.mybatis.entity.User;
import com.glancebar.mybatis.enums.GenderEnum;
import com.glancebar.mybatis.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;


@SpringBootApplication
@MapperScan(basePackages = "com.glancebar.mybatis.mapper", annotationClass = Repository.class)
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner demo(UserMapper userMapper) {
        User user = new User("https://staff-test.oss-cn-beijing.aliyuncs.com/avatar/avatar.png",
                "user",
                "nickname",
                GenderEnum.MALE,
                new Date(System.currentTimeMillis()),
                "comment",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                false,
                true);
        long id = userMapper.insert(user);
        userMapper.findAll().forEach(System.out::println);
        return args -> {
            logger.info("Hello, Spring!");
        };
    }

}