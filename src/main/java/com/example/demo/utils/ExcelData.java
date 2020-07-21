package com.example.demo.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author ruofan
 * @date 2019/11/16 23:00
 */
public class ExcelData implements Serializable {
    private List<String> hearer;
    private List<StudentExcel> data;

    public List<String> getHearer() {
        return hearer;
    }

    public void setHearer(List<String> hearer) {
        this.hearer = hearer;
    }

    public List<StudentExcel> getData() {
        return data;
    }

    public void setData(List<StudentExcel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "hearer=" + hearer +
                ", data=" + data +
                '}';
    }
}
