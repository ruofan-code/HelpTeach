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
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String courseId;

    private String courseName;

    private String teacherId;

    private String teacherName;

    private String studentId;

    private String studentName;

    private String field1;

    private String field2;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
        return "StudentCourse{" +
        "id=" + id +
        ", courseId=" + courseId +
        ", courseName=" + courseName +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", studentId=" + studentId +
        ", studentName=" + studentName +
        ", field1=" + field1 +
        ", field2=" + field2 +
        "}";
    }
}
