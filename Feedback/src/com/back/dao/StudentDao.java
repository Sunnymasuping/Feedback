package com.back.dao;

import com.back.bean.Student;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    /**
     * 添加学生信息
     * @param student
     * @return
     * @throws SQLException
     */
    public int insertStudent(Student student) throws SQLException {
        int row = 0;
        String sql = "insert into t_student(no,name,gender,phone,birthday,password,className,nativePlace) values(?, ?, ?, ?, ?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, student.getNo(), student.getName(), student.getGender(), student.getPhone(), student.getBirthday(),
                student.getPassword(), student.getClassname(), student.getNativeplace());
        return row;
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     * @throws SQLException
     */
    public int updateStudent(Student student) throws SQLException {
        int row = 0;
        String sql = "update t_student set no = ?, name = ?, gender = ?, phone = ?, birthday = ?, password = ?,className = ?, nativePlace = ? where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, student.getNo(), student.getName(), student.getGender(), student.getPhone(), student.getBirthday(),
                student.getPassword(), student.getClassname(), student.getNativeplace(), student.getId());
        return row;
    }

    /**
     * 删除学生信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteStudentById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_student where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询学生列表
     * @return
     * @throws SQLException
     */
    public List<Student> selectAllStudent(String studentNo, String studentName, int pageNum, int pageSize) throws SQLException {
        String sql = "select * from t_student where 1 = 1";
        ArrayList<String> paramList = new ArrayList<>();
        if (studentNo != null && !"".equals(studentNo)){
            sql += " and no = ?";
            paramList.add(studentNo);
        }
        if (studentName != null && !"".equals(studentName)){
            sql += " and name like ?";
            paramList.add("%" + studentName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<Student> studentList = (List<Student>) runner.query(sql, new BeanListHandler(Student.class), paramList.toArray());
        return studentList;
    }

    /**
     * 根据账号和密码查询学生
     * @param student
     * @return
     * @throws SQLException
     */
    public Student selectStudentByAccountAndPassword(Student student) throws SQLException {
        String sql = "select * from t_student where no = ? and password = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        Student studentRuselt = (Student) runner.query(sql, new BeanHandler(Student.class), student.getNo(), student.getPassword());
        return studentRuselt;
    }

    public long count(String studentNo, String studentName) throws SQLException {
        String sql = "select count(*) from t_student where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (studentNo != null && !"".equals(studentNo)){
            sql += " and no = ?";
            paramList.add(studentNo);
        }
        if (studentName != null && !"".equals(studentName)){
            sql += " and name like ?";
            paramList.add("%" + studentName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }
}
