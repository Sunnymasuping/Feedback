package com.back.service;

import com.back.bean.Teacher;
import com.back.dao.TeacherDao;

import java.sql.SQLException;
import java.util.List;

public class TeacherService {

    private TeacherDao teacherDao = new TeacherDao();

    public int add(Teacher teacher) throws SQLException {
        return teacherDao.insertTeacher(teacher);
    }

    public int edit(Teacher teacher) throws SQLException {
        return teacherDao.updateTeacher(teacher);
    }

    public int remove(int id) throws SQLException {
        return teacherDao.deleteTeacherById(id);
    }

    public List<Teacher> selectAll(String teacherNo, String teacheName, int pageNum, int pageSize) throws SQLException {
        return teacherDao.selectAllTeacher(teacherNo, teacheName, pageNum, pageSize);
    }

    public Teacher login(Teacher teacher) throws SQLException {
        return teacherDao.selectTeacherByAccountAndPassword(teacher);
    }

    public long count(String teacherNo, String teacheName) throws SQLException {
        return teacherDao.count(teacherNo, teacheName);
    }
}
