package com.turbouml.utils;

import java.util.HashMap;
import java.util.Map;

public class MappingUtils
{

    public static Map<String, Object> createParams(String[] params, Object[] arguments)
    {
        Map<String, Object> paramMap = new HashMap<>();
        for(int i = 0; i < params.length; i++) {
            paramMap.put(params[i], arguments[i]);
        }
        return paramMap;
    }

    @SafeVarargs
    public static Map<String, Object> createParams(Pair<String, Object>... pairs)
    {
        Map<String, Object> paramMap = new HashMap<>();
        for(Pair<String, Object> pair : pairs) {
            paramMap.put(pair.getLeft(), pair.getRight());
        }
        return paramMap;
    }
}
