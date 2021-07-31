package com.glancebar.mybatis.service;

import com.glancebar.mybatis.entity.User;
import com.glancebar.mybatis.exceptions.InternalServerErrorException;
import com.glancebar.mybatis.exceptions.NotFoundException;
import com.glancebar.mybatis.mapper.UserMapper;
import com.glancebar.mybatis.utils.OkResult;
import com.glancebar.mybatis.utils.PageResult;
import com.glancebar.mybatis.vo.UpdateUserVO;
import com.glancebar.mybatis.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author YISHEN CAI
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<OkResult> addUser(UserVO userVO) {
        User user = userVO.getEntity();
        userMapper.insertUser(user);
        logger.info("user " + user.getId() + " created!");
        URI uri = null;
        try {
            uri = new URI("/api/users/" + user.getId());
        } catch (URISyntaxException e) {
            logger.error("URI syntax error");
            throw new InternalServerErrorException(e.getMessage());
        }
        return ResponseEntity.created(uri).body(new OkResult("用户创建成功"));
    }

    @Override
    public ResponseEntity<PageResult<User>> getUsers(String query, boolean active, boolean deleted, int size, long offset) {
        long total = userMapper.countAllByQuery(query, active, deleted);
        List<User> users = userMapper.findAllByQuery(query, active, deleted, size, offset);
        return ResponseEntity.ok(new PageResult<>(total, false, users));
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        User user = userMapper.findOneById(id);
        if (user != null) {
            return ResponseEntity.ok(userMapper.findOneById(id));
        }
        throw new NotFoundException("请求的用户未找到");
    }

    @Override
    public ResponseEntity<OkResult> deleteById(Long id) {
        int res = userMapper.deleteById(id);
        if (res > 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        logger.error("啥都没干");
        throw new UnknownError();
    }

    @Override
    public ResponseEntity<OkResult> updateUserById(Long id, UpdateUserVO userVO) {
        User entity = userMapper.findOneById(id);
        entity = userVO.updateAndGetEntity(entity);
        int res = userMapper.updateUserById(entity);
        if (res > 0) {
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(new OkResult("修改用户信息成功"));
        }
        logger.error("啥都没干");
        throw new UnknownError();
    }
}
