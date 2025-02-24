package com.back.dao;

import com.back.bean.Course;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    /**
     * 添加课程信息
     * @param course
     * @return
     * @throws SQLException
     */
    public int insertCourse(Course course) throws SQLException {
        int row = 0;
        String sql = "insert into t_course(name, description, credit, textbook, type, hour) values(?, ?, ?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, course.getName(), course.getDescription(), course.getCredit(), course.getTextbook(), course.getType(), course.getHour());
        return row;
    }

    /**
     * 更新课程信息
     * @param course
     * @return
     * @throws SQLException
     */
    public int updateCourse(Course course) throws SQLException {
        int row = 0;
        String sql = "update t_course set name = ?, description = ?, credit = ?, textbook = ?, type = ?, hour = ? where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql,  course.getName(), course.getDescription(), course.getCredit(), course.getTextbook(), course.getType(), course.getHour(), course.getId());
        return row;
    }

    /**
     * 删除课程信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteCourseById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_course where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询课程信息列表
     * @return
     * @throws SQLException
     */
    public List<Course> selectAllCourse(String courseName, int pageNum, int pageSize) throws SQLException {
        String sql = "select * from t_course where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (courseName != null && !"".equals(courseName)){
            sql += " and name like ?";
            paramList.add("%" + courseName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<Course> courseList = (List<Course>) runner.query(sql, new BeanListHandler(Course.class), paramList.toArray());
        return courseList;
    }

    public long count(String courseName, int pageNum, int pageSize) throws SQLException {
        String sql = "select count(*) from t_course where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (courseName != null && !"".equals(courseName)){
            sql += " and name like ?";
            paramList.add("%" + courseName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }
}
