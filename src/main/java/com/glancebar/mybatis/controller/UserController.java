package com.glancebar.mybatis.controller;

import com.glancebar.mybatis.entity.SysUser;
import com.glancebar.mybatis.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userId}")
    public SysUser getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
