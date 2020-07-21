package com.example.demo.dao.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 若凡
 * @since 2020-07-19
 */
public class SignupStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String studentId;

    private String studentName;

    private String signupTeacherId;

    private String signupTeacherName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime signTime;

    private String courseId;

    private String courseName;

    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSignupTeacherId() {
        return signupTeacherId;
    }

    public void setSignupTeacherId(String signupTeacherId) {
        this.signupTeacherId = signupTeacherId;
    }

    public String getSignupTeacherName() {
        return signupTeacherName;
    }

    public void setSignupTeacherName(String signupTeacherName) {
        this.signupTeacherName = signupTeacherName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getSignTime() {
        return signTime;
    }

    public void setSignTime(LocalDateTime signTime) {
        this.signTime = signTime;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SignupStudent{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", studentName=" + studentName +
        ", signupTeacherId=" + signupTeacherId +
        ", signupTeacherName=" + signupTeacherName +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", signTime=" + signTime +
        ", courseId=" + courseId +
        ", courseName=" + courseName +
        ", status=" + status +
        "}";
    }
}
