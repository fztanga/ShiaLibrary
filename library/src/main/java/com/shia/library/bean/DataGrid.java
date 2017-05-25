package com.shia.library.bean;

import java.util.List;

/**
 * Created by hehz on 2017/3/30.
 */
public class DataGrid<T> {
    private String nextFlag;
    private int total;
    private List<T> rows;

    public String getNextFlag() {
        return nextFlag;
    }

    public void setNextFlag(String nextFlag) {
        this.nextFlag = nextFlag;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
