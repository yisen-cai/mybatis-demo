package com.glancebar.mybatis.service;

import com.glancebar.mybatis.entity.SysUser;
import com.glancebar.mybatis.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUser(Long userId) {
        SysUser user = sysUserMapper.selectUserRoleListByUserId(userId);
//        System.out.println("Invoke user.getRole()..........");
//        System.out.println(user.getRole());
        return user;
    }
}
