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
public class HomeworkTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String teacherId;

    private String teacherName;

    private String courseId;

    private String courseName;

    private LocalDateTime createTime;

    private String title;

    private String content;

    private String filesrc;

    private Integer finishCount;


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

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    @Override
    public String toString() {
        return "HomeworkTeacher{" +
        "id=" + id +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", courseId=" + courseId +
        ", courseName=" + courseName +
        ", createTime=" + createTime +
        ", title=" + title +
        ", content=" + content +
        ", filesrc=" + filesrc +
        ", finishCount=" + finishCount +
        "}";
    }
}
