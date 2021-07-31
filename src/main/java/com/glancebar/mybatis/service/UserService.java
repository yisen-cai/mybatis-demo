package com.glancebar.mybatis.service;

import com.glancebar.mybatis.entity.User;
import com.glancebar.mybatis.utils.OkResult;
import com.glancebar.mybatis.utils.PageResult;
import com.glancebar.mybatis.vo.UpdateUserVO;
import com.glancebar.mybatis.vo.UserVO;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

/**
 * @author YISHEN CAI
 */
public interface UserService {

    ResponseEntity<OkResult> addUser(UserVO userVO);


    ResponseEntity<PageResult<User>> getUsers(String query, boolean active, boolean deleted, int size, long offset);

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<OkResult> deleteById(Long id);

    ResponseEntity<OkResult> updateUserById(Long id, UpdateUserVO userVO);
}
