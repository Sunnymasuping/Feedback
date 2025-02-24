package com.back.service;

import com.back.bean.StudentIndex;
import com.back.dao.StudentIndexDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentIndexService {

    private StudentIndexDao studentIndexDao = new StudentIndexDao();

    public int add(StudentIndex studentIndex) throws SQLException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        studentIndex.setCreateTime(format.format(date));
        return studentIndexDao.insertStudentIndex(studentIndex);
    }

    public int remove(int id) throws SQLException {
        return studentIndexDao.deleteStudentIndexById(id);
    }

    public List<StudentIndex> selectAllTeacherCourseComment(int teacherId, String courseName, int pageNum, int pageSize) throws SQLException {
        return studentIndexDao.selectAllTeacherCourseComment(teacherId, courseName, pageNum, pageSize);
    }

    public long countComment(int teacherId, String courseName) throws SQLException {
        return studentIndexDao.countComment(teacherId, courseName);
    }

    public List<StudentIndex> selectDetailByStudentCourseId(int studentCourseId) throws SQLException {
        return studentIndexDao.selectDetailByStudentCourseId(studentCourseId);
    }

}
