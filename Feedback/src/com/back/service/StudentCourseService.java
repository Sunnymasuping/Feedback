package com.back.service;

import com.back.bean.StudentCourse;
import com.back.dao.StudentCourseDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentCourseService {

    private StudentCourseDao studentCourseDao = new StudentCourseDao();

    public int add(StudentCourse studentCourse) throws SQLException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        studentCourse.setCreateTime(format.format(date));
        return studentCourseDao.insertStudentCourse(studentCourse);
    }

    public int edit(StudentCourse studentCourse) throws SQLException {
        return studentCourseDao.updateCourse(studentCourse);
    }

    public int remove(int id) throws SQLException {
        return studentCourseDao.deleteStudentCourseById(id);
    }

    public List<StudentCourse> selectAll(int studentId, String studentName, String courseName, int pageNum, int pageSize) throws SQLException {
        return studentCourseDao.selectAllStudentCourse(studentId, studentName, courseName, pageNum, pageSize);
    }

    public long count(int studentId, String studentName, String courseName) throws SQLException {
        return studentCourseDao.count(studentId, studentName, courseName);
    }

}
