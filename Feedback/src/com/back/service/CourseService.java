package com.back.service;

import com.back.bean.Course;
import com.back.dao.CourseDao;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private CourseDao courseDao = new CourseDao();

    public int add(Course course) throws SQLException {
        return courseDao.insertCourse(course);
    }

    public int edit(Course course) throws SQLException {
        return courseDao.updateCourse(course);
    }

    public int remove(int id) throws SQLException {
        return courseDao.deleteCourseById(id);
    }

    public List<Course> selectAll(String courseName, int pageNum, int pageSize) throws SQLException {
        return courseDao.selectAllCourse(courseName, pageNum, pageSize);
    }

    public long count(String courseName, int pageNum, int pageSize) throws SQLException {
        return courseDao.count(courseName, pageNum, pageSize);
    }
}
