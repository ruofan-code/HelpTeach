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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String username;

    private String password;

    /**
     * 1教师 2学生
     */
    private Integer status;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
        "id=" + id +
        ", name=" + name +
        ", username=" + username +
        ", password=" + password +
        ", status=" + status +
        "}";
    }
}
