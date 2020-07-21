package com.example.demo.dao.entity;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 若凡
 * @since 2020-07-17
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 外键
     */
    private Integer roleCode;

    private String name;

    private String username;

    private String password;

    private String sex;

    private LocalDate birthday;

    private String phone;

    private String email;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", roleCode=" + roleCode +
        ", name=" + name +
        ", username=" + username +
        ", password=" + password +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", phone=" + phone +
        ", email=" + email +
        "}";
    }
}
