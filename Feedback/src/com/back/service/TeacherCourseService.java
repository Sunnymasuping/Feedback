package com.back.service;

import com.back.bean.TeacherCourse;
import com.back.dao.StudentCourseDao;
import com.back.dao.TeacherCourseDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TeacherCourseService {

    private TeacherCourseDao teacherCourseDao = new TeacherCourseDao();

    private StudentCourseDao studentCourseDao = new StudentCourseDao();

    public int add(TeacherCourse teacherCourse) throws SQLException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        teacherCourse.setCreateTime(format.format(date));
        return teacherCourseDao.insertTeacherCourse(teacherCourse);
    }

    public int remove(int id) throws SQLException {
        return teacherCourseDao.deleteTeacherCourseById(id);
    }

    public List<TeacherCourse> selectAll(int teacherId, String teacherName, String courseName, int pageNum, int pageSize) throws SQLException {
        List<TeacherCourse> teacherCourseList = teacherCourseDao.selectAllTeacherCourse(teacherId, teacherName, courseName, pageNum, pageSize);
        //查询评教平均分
        for (TeacherCourse tc : teacherCourseList){
            Double avgScore = studentCourseDao.selectAvgScoreByTeacherCourseId(tc.getId());
            tc.setAvgScore(avgScore);
        }
        return teacherCourseList;
    }

    public long count(int teacherId, String teacherName, String courseName) throws SQLException {
        return teacherCourseDao.count(teacherId, teacherName, courseName);
    }

}
