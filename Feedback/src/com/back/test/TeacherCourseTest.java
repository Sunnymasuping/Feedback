package com.back.test;

import com.back.bean.Course;
import com.back.bean.TeacherCourse;
import com.back.dao.TeacherCourseDao;
import com.back.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class TeacherCourseTest {
    private static TeacherCourseDao teacherCourseDao = new TeacherCourseDao();

    public static void main(String[] args) throws SQLException {
//        add();
//        remove();
        selectAll();
        count();
        selectByTeacherId();
        counByTeacherId();
    }

    public static void add() throws SQLException {
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourseid(15);
        teacherCourse.setTeacherNo("30003");
        int row = teacherCourseDao.insertTeacherCourse(teacherCourse);
        System.out.println(row);
    }

    public static void remove() throws SQLException {
        int row = teacherCourseDao.deleteTeacherCourseById(1);
        System.out.println(row);
    }

    public static void selectAll() throws SQLException {
        List<TeacherCourse> teacherCourses = teacherCourseDao.selectAllTeacherCourse(1, "明", "", 1, 10);
        System.out.println(teacherCourses);
    }

    public static void count() throws SQLException {
        long count = teacherCourseDao.count(1, "明", "");
        System.out.println(count);
    }

    public static void selectByTeacherId() throws SQLException {
        List<TeacherCourse> teacherCourses = teacherCourseDao.selectAllTeacherCourseByTeacherId(2, null, 1, 10);
        System.out.println(teacherCourses);
    }

    public static void counByTeacherId() throws SQLException {
        long count = teacherCourseDao.countByTeacherId(2, null);
        System.out.println(count);
    }
}
