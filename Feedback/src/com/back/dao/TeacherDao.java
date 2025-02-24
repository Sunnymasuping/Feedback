package com.back.dao;

import com.back.bean.Teacher;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {

    /**
     * 添加教师信息
     * @param teacher
     * @return
     * @throws SQLException
     */
    public int insertTeacher(Teacher teacher) throws SQLException {
        int row = 0;
        String sql = "insert into t_teacher(no,name,gender,phone,birthday,password,title,nativePlace) values(?, ?, ?, ?, ?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, teacher.getNo(), teacher.getName(), teacher.getGender(), teacher.getPhone(), teacher.getBirthday(),
                teacher.getPassword(), teacher.getTitle(), teacher.getNativePlace());
        return row;
    }

    /**
     * 更新教师信息
     * @param teacher
     * @return
     * @throws SQLException
     */
    public int updateTeacher(Teacher teacher) throws SQLException {
        int row = 0;
        String sql = "update t_teacher set no = ?, name = ?, gender = ?, phone = ?, birthday = ?, password = ?,title = ?, nativePlace = ? where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, teacher.getNo(), teacher.getName(), teacher.getGender(), teacher.getPhone(), teacher.getBirthday(),
                teacher.getPassword(), teacher.getTitle(), teacher.getNativePlace(), teacher.getId());
        return row;
    }

    /**
     * 删除教师信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteTeacherById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_teacher where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询教师列表
     * @return
     * @throws SQLException
     */
    public List<Teacher> selectAllTeacher(String teacherNo, String teacheName, int pageNum, int pageSize) throws SQLException {
        String sql = "select * from t_teacher where  1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (teacherNo != null && !"".equals(teacherNo)){
            sql += " and no = ?";
            paramList.add(teacherNo);
        }
        if (teacheName != null && !"".equals(teacheName)){
            sql += " and name like ?";
            paramList.add("%" + teacheName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        List<Teacher> teacherList = (List<Teacher>) runner.query(sql, new BeanListHandler(Teacher.class), paramList.toArray());
        return teacherList;
    }

    /**
     * 根据账号和密码查询教师
     * @param teacher
     * @return
     * @throws SQLException
     */
    public Teacher selectTeacherByAccountAndPassword(Teacher teacher) throws SQLException {
        String sql = "select * from t_teacher where no = ? and password = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        Teacher teacherRuselt = (Teacher) runner.query(sql, new BeanHandler(Teacher.class), teacher.getNo(), teacher.getPassword());
        return teacherRuselt;
    }

    public long count(String teacherNo, String teacheName) throws SQLException {
        String sql = "select count(*) from t_teacher where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (teacherNo != null && !"".equals(teacherNo)){
            sql += " and no = ?";
            paramList.add(teacherNo);
        }
        if (teacheName != null && !"".equals(teacheName)){
            sql += " and name like ?";
            paramList.add("%" + teacheName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }
}
