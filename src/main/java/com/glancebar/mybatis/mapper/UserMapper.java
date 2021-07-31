package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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


    @Select("select id, avatar, username, nickname, gender, birthday, user_comment as userComment, create_time as createTime, update_time as updateTime, deleted, active from user where id=#{id}")
    User findOneById(Long id);

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
    @Select("select id, avatar, username, nickname, gender, birthday, user_comment as userComment, create_time as createTime, update_time as updateTime, deleted, active from user")
    List<User> findAll();

    /**
     * @param query
     * @param active
     * @param deleted
     * @return
     */
    List<User> findAllByQuery(String query, Boolean active, Boolean deleted, int size, long offset);

    long countAllByQuery(String query, boolean active, boolean deleted);

    /**
     * @param user
     * @return
     */
    int updateUserById(User user);


    @Update("update user set deleted=1 where id = #{id}")
    int deleteById(Long userId);
}
