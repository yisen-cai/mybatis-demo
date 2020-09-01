package com.glancebar.mybatis.service;

import com.glancebar.mybatis.entity.SysUser;
import com.glancebar.mybatis.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final SysUserMapper sysUserMapper;

    public UserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public SysUser getUser(Long userId) {
        SysUser user = sysUserMapper.selectUserAndRoleByUserIdInAnotherWay(userId);
        System.out.println("Invoke user.getRole()..........");
        System.out.println(user.getRole());
        return user;
    }
}
