package com.shia.library.util;

import com.google.gson.Gson;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
public class GsonUtil {

    public static <T> T getObject(String jsonObject, Class<T> clz) {
        return new Gson().fromJson(jsonObject, clz);
    }

    public static <T> List<T> getObjects(String jsonArray, Class<T> clz) {
        if (StringUtil.isEmpty(jsonArray)) {
            return new ArrayList<>();
        }
        Type type = new ParameterizedTypeImpl(List.class, new Class[] { clz });
        List list = new Gson().fromJson(jsonArray, type);
        return list;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
