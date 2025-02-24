package com.back.bean;

import java.io.Serializable;

/**
 * 学生课程表
 * @TableName t_student_course
 */
public class StudentCourse implements Serializable {

    private int id;
    /**
     * 学号
     */
    private String studentNo;

    /**
     * 学生id
     */
    private int studentId;

    /**
     * 教师任课id
     */
    private int teacherCourseId;

    /**
     * 教工号
     */
    private String teacherno;

    /**
     * 课程id
     */
    private Integer courseid;

    /**
     * 创建时间
     */
    private String createTime;

    private String studentName; //学生姓名

    private String teacherName; //任课教师姓名

    private String courseName; //课程名称

    private String description; //课程描述
    private int credit; //学分
    private String textbook;    //教材
    private String type;    //课程类型
    private int hour;   //学时

    private String content; //评教内容

    private int status; //课程评教状态

    private int score;  //课程评教总分

    private static final long serialVersionUID = 1L;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherCourseId() {
        return teacherCourseId;
    }

    public void setTeacherCourseId(int teacherCourseId) {
        this.teacherCourseId = teacherCourseId;
    }

    /**
     * 学号
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * 学号
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 教工号
     */
    public String getTeacherno() {
        return teacherno;
    }

    /**
     * 教工号
     */
    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno;
    }

    /**
     * 课程id
     */
    public Integer getCourseid() {
        return courseid;
    }

    /**
     * 课程id
     */
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /**
     * 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
        return "StudentCourse{" +
                "id=" + id +
                ", studentno='" + studentNo + '\'' +
                ", teacherno='" + teacherno + '\'' +
                ", courseid=" + courseid +
                ", createtime='" + createTime + '\'' +
                ", studentName='" + studentName + '\'' +
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