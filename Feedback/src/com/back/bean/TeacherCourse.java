package com.back.bean;

import java.io.Serializable;

/**
 * 教师任课表
 * @TableName t_teacher_course
 */
public class TeacherCourse implements Serializable {

    private int id;
    /**
     * 任课教师
     */
    private String teacherNo;

    /**
     * 任课教师
     */
    private int teacherId;

    /**
     * 课程id
     */
    private Integer courseid;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 教师名称
     */
    private String teacherName;
    private String courseName;    //课程名称
    private String description; //课程描述
    private int credit; //学分
    private String textbook;    //教材
    private String type;    //课程类型
    private int hour;   //学时

    private Double avgScore; //评教平均分

    private static final long serialVersionUID = 1L;

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "teacherno='" + teacherNo + '\'' +
                ", courseid=" + courseid +
                ", createtime='" + createTime + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", credit=" + credit +
                ", textbook='" + textbook + '\'' +
                ", type='" + type + '\'' +
                ", hour=" + hour +
                '}';
    }
}