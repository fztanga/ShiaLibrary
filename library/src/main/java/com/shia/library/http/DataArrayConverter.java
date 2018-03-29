package com.shia.library.http;

import com.google.gson.Gson;
import com.shia.library.bean.DataGrid;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataArrayConverter extends DataGridConverter {

    public DataArrayConverter(Class clazz) {
        super(clazz);
    }

    @Override
    public DataGrid convert(String json) throws Exception {
        DataGrid dataGrid = new DataGrid();
        // 生成List<T> 中的 List<T>
        Type type = new ParameterizedTypeImpl(List.class, new Class[] { clazz });
        JSONArray array = new JSONObject(json).optJSONArray("data");
        if (array != null && array.length() > 0) {
            List list = new Gson().fromJson(array.toString(), type);
            dataGrid.setRows(list);
            dataGrid.setTotal(list.size());
        } else {
            dataGrid.setRows(new ArrayList());
            dataGrid.setTotal(0);
        }
        dataGrid.setNextFlag("N");
        return dataGrid;
    }
}
