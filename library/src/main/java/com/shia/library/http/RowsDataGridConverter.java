package com.shia.library.http;

import com.google.gson.Gson;
import com.shia.library.bean.DataGrid;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017/5/29.
 */
public class RowsDataGridConverter extends DataGridConverter {

    public RowsDataGridConverter(Class clazz) {
        super(clazz);
    }

    @Override
    public DataGrid convert(String json) throws Exception {
        DataGrid dataGrid = new DataGrid();
        JSONObject jsonObject = new JSONObject(json).getJSONObject("data");
        dataGrid.setTotal(jsonObject.optInt("total"));
        // 生成List<T> 中的 List<T>
        Type type = new ParameterizedTypeImpl(List.class, new Class[] { clazz });
        List list = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), type);
        dataGrid.setRows(list);
        return dataGrid;
    }
}
