package com.glancebar.mybatis.controller;

import com.glancebar.mybatis.entity.User;
import com.glancebar.mybatis.service.UserService;
import com.glancebar.mybatis.utils.OkResult;
import com.glancebar.mybatis.utils.PageResult;
import com.glancebar.mybatis.vo.UpdateUserVO;
import com.glancebar.mybatis.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author YISHEN CAI
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PageResult<User>> getUsers(@RequestParam(required = false, defaultValue = "") String query,
                                                     @RequestParam(required = false, defaultValue = "true") Boolean active,
                                                     @RequestParam(required = false, defaultValue = "false") Boolean delete,
                                                     @RequestParam(required = false, defaultValue = "5") Integer size,
                                                     @RequestParam(required = false, defaultValue = "0") Long offset) {
        return userService.getUsers(query, active, delete, size, offset);
    }


    @PostMapping
    public ResponseEntity<OkResult> addUser(@Valid @RequestBody UserVO userVO) {
        return userService.addUser(userVO);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<OkResult> updateUserById(@PathVariable Long userId, @Valid @RequestBody UpdateUserVO userVO) {
        return userService.updateUserById(userId, userVO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<OkResult> deleteUserById(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }
}
