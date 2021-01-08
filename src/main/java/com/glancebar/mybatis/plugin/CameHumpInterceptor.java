package com.glancebar.mybatis.plugin;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

@Intercepts(
        @Signature(
                type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class}
        )
)
public class CameHumpInterceptor implements Interceptor {
    public static String underlineToCamelHump(String inputString) {
        StringBuilder sb = new StringBuilder();
        String[] words = inputString.split("_");

        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                sb.append(words[i].toLowerCase());
            } else {
                if (words[i].length() > 2) {
                    sb.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1).toLowerCase());
                } else {
                    sb.append(words[i].toUpperCase());
                }
            }
        }

        return sb.toString();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 强制转换为List类型，invocation.proceed()返回list
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object o : list) {
            if (o instanceof Map) {
                processMap((Map<String, Object>) o);
            } else {
                break;
            }
        }
        return list;
    }

    private void processMap(Map<String, Object> map) {
        Set<String> keySet = new HashSet<>(map.keySet());
        for (String key :
                keySet) {
            if (key.charAt(0) >= 'A' && key.charAt(0) <= 'Z' || key.contains("_")) {
                Object value = map.get(key);
                map.remove(key);
                map.put(underlineToCamelHump(key), value);
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
