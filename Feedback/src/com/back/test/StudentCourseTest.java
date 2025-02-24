package com.back.test;

import com.back.bean.StudentCourse;
import com.back.dao.StudentCourseDao;

import java.sql.SQLException;
import java.util.List;

public class StudentCourseTest {
    private static StudentCourseDao studentCourseDao = new StudentCourseDao();

    public static void main(String[] args) throws SQLException {
//        add();
//        remove();
        selectAll();
        count();
        selectByStudentId();
        counByStudentId();
    }

    public static void add() throws SQLException {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseid(1);
        studentCourse.setStudentNo("20002");
        studentCourse.setTeacherno("30003");
        int row = studentCourseDao.insertStudentCourse(studentCourse);
        System.out.println(row);
    }

    public static void remove() throws SQLException {
        int row = studentCourseDao.deleteStudentCourseById(1);
        System.out.println(row);
    }

    public static void selectAll() throws SQLException {
        List<StudentCourse> studentCourses = studentCourseDao.selectAllStudentCourse(1, "", "", 1, 10);
        System.out.println(studentCourses);
    }

    public static void count() throws SQLException {
        long count = studentCourseDao.count(1, "", "");
        System.out.println(count);
    }

    public static void selectByStudentId() throws SQLException {
        List<StudentCourse> studentCourses = studentCourseDao.selectAllStudentCourseByStudentId(2, null, 1, 10);
        System.out.println(studentCourses);
    }

    public static void counByStudentId() throws SQLException {
        long count = studentCourseDao.countByStudentId(2, null);
        System.out.println(count);
    }
}
