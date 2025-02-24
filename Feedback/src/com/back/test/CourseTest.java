package com.back.test;

import com.back.bean.Course;
import com.back.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseTest {
    private static CourseService courseService = new CourseService();

    public static void main(String[] args) throws SQLException {

        Course course = new Course();
//        course.setId(6);
        course.setName("线性代数");
        course.setTextbook("线性代数");
        course.setDescription("这是个线性代数");
        course.setCredit(4);
        course.setType("必修课");
        course.setHour(64);

//        int row = courseService.remove(6);
//        System.out.println(row);

//        int row = courseService.edit(course);
//        System.out.println(row);

        int row = courseService.add(course);
        System.out.println(row);

//        List<Course> courses = courseService.selectAll();
//        System.out.println(courses);
    }



}
