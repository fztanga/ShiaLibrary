package com.shia.library.http;

import com.google.gson.Gson;
import com.shia.library.bean.DataGrid;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by hehz on 2017/4/8.
 */
public class DataGridConverter {

    protected Class clazz;

    public DataGridConverter() {
    }

    public DataGridConverter(Class clazz) {
        this.clazz = clazz;
    }

    public static DataGridConverter create(Class clazz) {
        return new DataGridConverter(clazz);
    }

    public DataGrid convert(String json) throws Exception {
        Gson gson = new Gson();
        // 生成DataGrid<T> 中的 DataGrid<T>
        Type dataGridType = new ParameterizedTypeImpl(DataGrid.class, new Class[] { clazz });
        // 根据DataGrid<T>生成完整的Response<DataGrid<T>>
        Type type = new ParameterizedTypeImpl(Response.class, new Type[] { dataGridType });
        Response<DataGrid> response = gson.fromJson(json, type);
        return response.getData();
    }

    public class ParameterizedTypeImpl implements ParameterizedType {
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
