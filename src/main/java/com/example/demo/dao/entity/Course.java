package com.example.demo.dao.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 若凡
 * @since 2020-07-17
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String teacherName;

    private String teacherId;

    private String field1;

    private String field2;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Override
    public String toString() {
        return "Course{" +
        "id=" + id +
        ", name=" + name +
        ", teacherName=" + teacherName +
        ", teacherId=" + teacherId +
        ", field1=" + field1 +
        ", field2=" + field2 +
        "}";
    }
}
