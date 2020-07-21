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
public class HomeworkStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 外键
     */
    private String studentId;

    private String studentName;

    /**
     * 外键
     */
    private String homeworkTeacherId;

    private String homeworkTeacherName;

    private String courseId;

    private String courseName;

    private LocalDateTime finishTime;

    private String title;

    private String content;

    private String filesrc;

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

    public String getHomeworkTeacherId() {
        return homeworkTeacherId;
    }

    public void setHomeworkTeacherId(String homeworkTeacherId) {
        this.homeworkTeacherId = homeworkTeacherId;
    }

    public String getHomeworkTeacherName() {
        return homeworkTeacherName;
    }

    public void setHomeworkTeacherName(String homeworkTeacherName) {
        this.homeworkTeacherName = homeworkTeacherName;
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

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HomeworkStudent{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", studentName=" + studentName +
        ", homeworkTeacherId=" + homeworkTeacherId +
        ", homeworkTeacherName=" + homeworkTeacherName +
        ", courseId=" + courseId +
        ", courseName=" + courseName +
        ", finishTime=" + finishTime +
        ", title=" + title +
        ", content=" + content +
        ", filesrc=" + filesrc +
        ", status=" + status +
        "}";
    }
}
