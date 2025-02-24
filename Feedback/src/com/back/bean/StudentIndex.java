package com.back.bean;

import java.io.Serializable;

/**
 * 学生评教表
 * @TableName t_student_index
 */
public class StudentIndex implements Serializable {

    private int id;

    /**
     * 学生选课id
     */
    private int studentCourseId;


    /**
     * 评教指标id
     */
    private Integer indexid;

    /**
     * 评教分数
     */
    private Integer score;

    /**
     * 评教时间
     */
    private String createTime;

    private String content;

    private String studentName;

    private String courseName;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public Integer getIndexid() {
        return indexid;
    }

    public void setIndexid(Integer indexid) {
        this.indexid = indexid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StudentIndex{" +
                "studentCourseId=" + studentCourseId +
                ", indexid=" + indexid +
                ", score=" + score +
                ", createtime='" + createTime + '\'' +
                '}';
    }
}