package com.example.demo.dao.web;

import com.example.demo.dao.entity.SignupStudent;
import com.example.demo.dao.entity.SignupTeacher;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WebSignupTeacher implements Serializable {

    private String id;

    private String teacherId;

    private String teacherName;

    private String courseId;

    private String courseName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer signupCount;

    private LocalDateTime signupTime;

    private String status;

    public WebSignupTeacher(SignupTeacher signupTeacher){
        this.id = signupTeacher.getId();
        this.teacherId = signupTeacher.getTeacherId();
        this.teacherName = signupTeacher.getTeacherName();
        this.startTime = signupTeacher.getStartTime();
        this.endTime = signupTeacher.getEndTime();
        this.courseId = signupTeacher.getCourseId();
        this.courseName = signupTeacher.getCourseName();

    }

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

    public LocalDateTime getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(LocalDateTime signupTime) {
        this.signupTime = signupTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WebSignupTeacher{" +
                "id='" + id + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", signupCount=" + signupCount +
                ", signupTime=" + signupTime +
                ", status='" + status + '\'' +
                '}';
    }
}
