package com.back.bean;

import java.io.Serializable;

/**
 * 管理员表
 * @TableName t_admin
 */
public class Admin implements Serializable {

    private int id; //主键
    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 出生年月
     */
    private String birthday;

    /**
     * 密码
     */
    private String password;

    /**
     * 籍贯
     */
    private String nativeplace;

    /**
     * 照片
     */
    private String picture;

    /**
     * 角色：admin
     */
    private String role;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 出生年月
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 出生年月
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 籍贯
     */
    public String getNativeplace() {
        return nativeplace;
    }

    /**
     * 籍贯
     */
    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    /**
     * 照片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 照片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 角色：admin
     */
    public String getRole() {
        return role;
    }

    /**
     * 角色：admin
     */
    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", account=").append(account);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", phone=").append(phone);
        sb.append(", birthday=").append(birthday);
        sb.append(", password=").append(password);
        sb.append(", nativeplace=").append(nativeplace);
        sb.append(", picture=").append(picture);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}