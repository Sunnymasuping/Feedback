package com.back.dao;

import com.back.bean.TeacherCourse;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherCourseDao {

    /**
     * 添加任课信息
     * @param teacherCourse
     * @return
     * @throws SQLException
     */
    public int insertTeacherCourse(TeacherCourse teacherCourse) throws SQLException {
        int row = 0;
        String sql = "insert into t_teacher_course(teacherId, courseid, createTime) values(?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, teacherCourse.getTeacherId(), teacherCourse.getCourseid(), teacherCourse.getCreateTime());
        return row;
    }


    /**
     * 删除任课信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteTeacherCourseById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_teacher_course where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询任课信息列表
     * @return
     * @throws SQLException
     */
    public List<TeacherCourse> selectAllTeacherCourse(int teacherId, String teacherName, String courseName, int pageNum, int pageSize) throws SQLException {
        String sql = "select tc.id, tc.createTime, t.no as teacherNo, t.name as teacherName, c.name as courseName, c.description, c.credit, c.textbook, c.type, c.hour " +
                "from t_teacher_course as tc, t_teacher as t, t_course as c " +
                "where 1 = 1 and tc.teacherId = t.id and tc.courseId = c.id";
        ArrayList<String> paramList = new ArrayList<>();
        if (teacherId > 0 ){
            sql += " and t.id = ?";
            paramList.add(teacherId + "");
        }
        if (teacherName != null && !"".equals(teacherName)){
            sql += " and t.name like ?";
            paramList.add("%" + teacherName + "%");
        }
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<TeacherCourse> teacherCourseList = (List<TeacherCourse>) runner.query(sql, new BeanListHandler(TeacherCourse.class), paramList.toArray());
        return teacherCourseList;
    }

    public long count(int teacherId, String teacherName, String courseName) throws SQLException {
        String sql = "select count(*) from t_teacher_course as tc, t_teacher as t, t_course as c" +
                " where 1 = 1 and tc.teacherId = t.id and tc.courseId = c.id ";
        ArrayList<String> paramList = new ArrayList<>();
        if (teacherId > 0 ){
            sql += " and t.id = ?";
            paramList.add(teacherId + "");
        }
        if (teacherName != null && !"".equals(teacherName)){
            sql += " and t.name like ?";
            paramList.add("%" + teacherName + "%");
        }
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }


























    /**
     * 根据教师查询任课信息列表
     * @return
     * @throws SQLException
     */
    public List<TeacherCourse> selectAllTeacherCourseByTeacherId(int id, String courseName, int pageNum, int pageSize) throws SQLException {
        String sql = "select *, t.name as teacherName, c.name as courseName from t_teacher_course as tc, t_teacher as t, t_course as c " +
                "where 1 = 1 and tc.teacherId = t.id and tc.courseId = c.id";
        ArrayList<String> paramList = new ArrayList<>();
        if (id > 0 ){
            sql += " and t.id = ?";
            paramList.add(id + "");
        }
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<TeacherCourse> teacherCourseList = (List<TeacherCourse>) runner.query(sql, new BeanListHandler(TeacherCourse.class), paramList.toArray());
        return teacherCourseList;
    }

    public long countByTeacherId(int id, String courseName) throws SQLException {
        String sql = "select count(*) from t_teacher_course as tc, t_teacher as t, t_course as c" +
                " where 1 = 1 and tc.teacherId = t.id and tc.courseId = c.id ";
        ArrayList<String> paramList = new ArrayList<>();
        if (id > 0 ){
            sql += " and t.id = ?";
            paramList.add(id + "");
        }
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }
}
