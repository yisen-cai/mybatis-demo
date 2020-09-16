package com.glancebar.mybatis.mapper;

import com.glancebar.mybatis.entity.SysRole;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.FifoCache;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheNamespace(
//        eviction = FifoCache.class,
//        flushInterval = 60000,
//        size = 512,
//        readWrite = true
//)
// 如果缓存设置在Mapper之中，参照缓存
//@CacheNamespaceRef(SysRoleMapper.class)
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Long pkId);

    SysRole selectRoleById(Long pkId);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
}