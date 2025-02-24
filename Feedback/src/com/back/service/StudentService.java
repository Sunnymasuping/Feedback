package com.back.service;

import com.back.bean.Student;
import com.back.dao.StudentDao;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private StudentDao studentDao = new StudentDao();

    public int add(Student student) throws SQLException {
        return studentDao.insertStudent(student);
    }

    public int edit(Student student) throws SQLException {
        return studentDao.updateStudent(student);
    }

    public int remove(int id) throws SQLException {
        return studentDao.deleteStudentById(id);
    }

    public List<Student> selectAll(String studentNo, String studentName, int pageNum, int pageSize) throws SQLException {
        return studentDao.selectAllStudent(studentNo, studentName,pageNum, pageSize);
    }

    public Student login(Student student) throws SQLException {
        return studentDao.selectStudentByAccountAndPassword(student);
    }

    public long count(String studentNo, String studentName) throws SQLException {
        return studentDao.count(studentNo, studentName);
    }
}
