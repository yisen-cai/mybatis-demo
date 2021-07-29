package com.glancebar.mybatis.utils;

import com.glancebar.mybatis.enums.GenderEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Enabled枚举类型映射数据库处理器
 */
public class GenderEnumTypeHandler implements TypeHandler<GenderEnum> {
    // 使用一个Map来存放所有的映射，方便转换
    private final Map<Integer, GenderEnum> genderEnumMap = new HashMap<>();

    public GenderEnumTypeHandler() {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            genderEnumMap.put(genderEnum.getValue(), genderEnum);
        }
    }

    /**
     * 从实体类获取value设置到数据库
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, GenderEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    /**
     * 从数据库的值获取枚举实例
     */
    @Override
    public GenderEnum getResult(ResultSet rs, String columnName) throws SQLException {
        Integer value = rs.getInt(columnName);
        return genderEnumMap.get(value);
    }

    @Override
    public GenderEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value = rs.getInt(columnIndex);
        return genderEnumMap.get(value);
    }

    @Override
    public GenderEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value = cs.getInt(columnIndex);
        return genderEnumMap.get(value);
    }
}
