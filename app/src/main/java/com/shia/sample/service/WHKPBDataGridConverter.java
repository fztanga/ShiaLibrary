package com.shia.sample.service;

import com.google.gson.Gson;
import com.shia.library.bean.DataGrid;
import com.shia.library.http.DataGridConverter;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by administrator on 2017/4/8.
 */
public class WHKPBDataGridConverter extends DataGridConverter {

    private String rowsName;

    public WHKPBDataGridConverter(Class clazz, String rowsName) {
        super(clazz);
        this.rowsName = rowsName;
    }

    @Override
    public DataGrid convert(String json) throws Exception {
        DataGrid dataGrid = new DataGrid();
        JSONObject jsonObject = new JSONObject(json);
        dataGrid.setTotal(jsonObject.optInt("totalPage"));
        // 生成List<T> 中的 List<T>
        Type type = new ParameterizedTypeImpl(List.class, new Class[] { clazz });
        List list = new Gson().fromJson(jsonObject.getString(rowsName), type);
        dataGrid.setRows(list);
        return dataGrid;
    }
}
