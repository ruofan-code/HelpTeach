package com.example.demo.dao.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 若凡
 * @since 2020-07-17
 */
public class SignupTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String teacherId;

    private String teacherName;

    private String courseId;

    private String courseName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer signupCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getSignupCount() {
        return signupCount;
    }

    public void setSignupCount(Integer signupCount) {
        this.signupCount = signupCount;
    }

    @Override
    public String toString() {
        return "SignupTeacher{" +
        "id=" + id +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", courseId=" + courseId +
        ", courseName=" + courseName +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", signupCount=" + signupCount +
        "}";
    }
}
