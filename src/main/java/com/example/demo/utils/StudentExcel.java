package com.example.demo.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author ruofan
 * @date 2019/11/16 23:08
 */
public class StudentExcel implements Serializable {
    private String username;
    private String name;
    private List<String> checkList;
    private Integer checkTotal;
    private Integer checked;
    private Integer nocheck;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<String> checkList) {
        this.checkList = checkList;
    }

    public Integer getCheckTotal() {
        return checkTotal;
    }

    public void setCheckTotal(Integer checkTotal) {
        this.checkTotal = checkTotal;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getNocheck() {
        return nocheck;
    }

    public void setNocheck(Integer nocheck) {
        this.nocheck = nocheck;
    }

    @Override
    public String toString() {
        return "StudentExcel{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", checkList=" + checkList +
                ", checkTotal=" + checkTotal +
                ", checked=" + checked +
                ", nocheck=" + nocheck +
                '}';
    }
}
