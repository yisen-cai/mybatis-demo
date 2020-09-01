package com.glancebar.mybatis.service;

import com.glancebar.mybatis.entity.SysUser;

public interface UserService {
    SysUser getUser(Long userId);
}
