package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YISHEN CAI
 */
@Repository
public interface UserMapper {

    /**
     * @param user
     */
    long insert(User user);


    /**
     * 注解方式
     *
     * @param user
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user (id, avatar, username, nickname, gender, birthday, user_comment, deleted, active, " +
            "create_time, update_time) values(#{id}, #{avatar}, #{username}, #{nickname}, #{gender}, #{birthday}, " +
            "#{userComment}, #{deleted}, #{active}, #{createTime}, #{updateTime})")
    void insertUser(User user);


    /**
     * @return
     */
    @Select("select id, avatar, username, nickname, gender, birthday, user_comment, create_time, update_time, deleted, active from user")
    List<User> findAll();
}
