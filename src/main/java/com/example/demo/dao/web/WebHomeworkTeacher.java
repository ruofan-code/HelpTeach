package com.example.demo.dao.web;

import com.example.demo.dao.entity.HomeworkTeacher;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WebHomeworkTeacher implements Serializable {
    private String id;

    private String teacherId;

    private String teacherName;

    private String courseId;

    private String courseName;

    private LocalDateTime createTime;

    private String title;

    private String content;

    private String filesrc;

    private LocalDateTime finishTime;

    private Integer finishCount;

    public WebHomeworkTeacher(HomeworkTeacher homeworkTeacher){
        this.id = homeworkTeacher.getId();
        this.teacherId = homeworkTeacher.getTeacherId();
        this.teacherName = homeworkTeacher.getTeacherName();
        this.courseId = homeworkTeacher.getCourseId();
        this.courseName = homeworkTeacher.getCourseName();
        this.createTime = homeworkTeacher.getCreateTime();
        this.title = homeworkTeacher.getTitle();
        this.content = homeworkTeacher.getContent();
        this.filesrc = homeworkTeacher.getFilesrc();
        this.finishCount = homeworkTeacher.getFinishCount();
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilesrc() {
        return filesrc;
    }

    public void setFilesrc(String filesrc) {
        this.filesrc = filesrc;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    @Override
    public String toString() {
        return "WebHomeworkTeacher{" +
                "id='" + id + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", filesrc='" + filesrc + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", finishCount=" + finishCount +
                '}';
    }
}
