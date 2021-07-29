# mybatis-demo

MyBatis基础配置示例项目, 包含以下

- MyBatis配置

- 自动生成实体类

- 数据库枚举类型转换



## 项目配置

配置统一写在`src/resources/application.yml`文件中, 简单配置如下:

~~~yml
# MyBatis相关配置路径
mybatis:
  mapper-locations: classpath:mybatis/mapper/*.xml
  config-location: classpath:mybatis/mybatis-config.xml
  type-aliases-package: com.glancebar.mybatis

# 数据库配置
spring:
  datasource:
    url: jdbc:mysql://your-host:3306/db_mybatis?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: your-own-password

# 启动端口
server:
	port: 8080
~~~





## MyBatis配置

Mapper文件位置: src/resources/mybatis/mapper

MyBatis配置文件: src/resources/mybatis/mybatis-config.xml

自动生成实体类配置文件: src/resources/mybatis/generateConfig.xml



## 自动生成实体类

通过使用MyBatis插件连接数据库, 直接生成项目中所需的实体类(数据库映射).

### 基础配置

src/resources/mybatis/generatorConfig.xml:

~~~xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
  <context id="MysqlContext" targetRuntime="MyBatis3Simple" defaultModelType="flat">
    <property name="beginningDelimiter" value="`"/>
    <property name="endingDelimiter" value="`"/>
    <property name="javaFileEncoding" value="UTF-8"/>

    <!-- entity comments -->
    <!-- https://mybatis.org/generator/configreference/commentGenerator.html -->
    <commentGenerator>
      <property name="suppressAllComments" value="true"/>
    </commentGenerator>

    <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                    connectionURL="jdbc:mysql://glancebar.com:3306/db_mybatis"
                    userId="root"
                    password="your-own-password">
    </jdbcConnection>
	
    <!-- 实体类路径 -->
    <javaModelGenerator targetPackage="com.glancebar.mybatis.entity"
                        targetProject="src/main/java">
      <property name="trimStrings" value="true"/>
    </javaModelGenerator>

    <!-- Mapper路径 -->
    <sqlMapGenerator targetPackage="mapper"
                     targetProject="src/main/resources/mybatis">
    </sqlMapGenerator>

    <javaClientGenerator type="XMLMAPPER" targetPackage="com.glancebar.mybatis.mapper"
                         targetProject="src/main/java">

    </javaClientGenerator>
    
		<!-- 添加要生成的表 -->
    <table tableName="sys_user">
      <!-- Specify selectKey or insertKey -->
      <generatedKey column="pk_id" sqlStatement="MySql"/>
    </table>
  </context>
</generatorConfiguration>
~~~



### 代码生成

com.glancebar.mybatis.config.MyGenerator:

~~~java
package com.glancebar.mybatis.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis Generator demo
 */
public class MyGenerator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        InputStream is = MyGenerator.class.getResourceAsStream("/mybatis/generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);

        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        warnings.forEach(System.out::println);
    }
}
~~~



### 



## 数据库枚举类型转换

~~~java
package com.glancebar.mybatis.utils;

import com.glancebar.mybatis.enums.GenderEnum;
import com.glancebar.mybatis.enums.Enabled;
import com.glancebar.mybatis.enums.GenderEnum;import org.apache.ibatis.type.JdbcType;
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
public class EnabledTypeHandler implements TypeHandler<GenderEnum> {
    // 使用一个Map来存放所有的映射，方便转换
    private final Map<Integer, GenderEnum> enabledMap = new HashMap<>();

    public EnabledTypeHandler() {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            enabledMap.put(genderEnum.getValue(), genderEnum);
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
        return enabledMap.get(value);
    }

    @Override
    public GenderEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value = rs.getInt(columnIndex);
        return enabledMap.get(value);
    }

    @Override
    public GenderEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value = cs.getInt(columnIndex);
        return enabledMap.get(value);
    }
}

~~~

