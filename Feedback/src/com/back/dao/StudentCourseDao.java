package com.back.dao;

import com.back.bean.StudentCourse;
import com.back.bean.StudentIndex;
import com.back.bean.TeacherCourse;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao {

    /**
     * 添加选课信息
     * @param studentCourse
     * @return
     * @throws SQLException
     */
    public int insertStudentCourse(StudentCourse studentCourse) throws SQLException {
        int row = 0;
        String sql = "insert into t_student_course(studentid, teacherCourseId, createTime) values(?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, studentCourse.getStudentId(), studentCourse.getTeacherCourseId(), studentCourse.getCreateTime());
        return row;
    }


    /**
     * 更新课程信息
     * @param studentCourse
     * @return
     * @throws SQLException
     */
    public int updateCourse(StudentCourse studentCourse) throws SQLException {
        int row = 0;
        String sql = "update t_student_course set content = ?, score = ?, status = ? where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, studentCourse.getContent(), studentCourse.getScore(), studentCourse.getStatus(), studentCourse.getId());
        return row;
    }
//教师id去查询任课，被学生选课后所评价的评教信息
    /**
     * 删除选课信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteStudentCourseById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_student_course where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询选课信息列表
     * @return
     * @throws SQLException
     */
    public List<StudentCourse> selectAllStudentCourse(int studentId, String studentName, String courseName, int pageNum, int pageSize) throws SQLException {
        String sql = "select sc.id, sc.status, sc.score, sc.createTime, s.no as studentNo, s.name as studentName, c.name as courseName, c.description, c.credit, c.textbook, c.type, c.hour, t.name as teacherName " +
                "from t_student_course as sc, t_teacher_course as tc, t_student as s, t_teacher as t, t_course as c " +
                "where 1 = 1 and sc.studentId = s.id and sc.teacherCourseId = tc.id and tc.teacherId = t.id and tc.courseId = c.id";
        ArrayList<String> paramList = new ArrayList<>();
        if (studentId > 0 ){
            sql += " and s.id = ?";
            paramList.add(studentId + "");
        }
        if (studentName != null && !"".equals(studentName)){
            sql += " and s.name like ?";
            paramList.add("%" + studentName + "%");
        }
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<StudentCourse> studentCourseList = (List<StudentCourse>) runner.query(sql, new BeanListHandler(StudentCourse.class), paramList.toArray());
        return studentCourseList;
    }

    public long count(int studentId, String studentName, String courseName) throws SQLException {
        String sql = "select count(*) from t_student_course as sc, t_teacher_course as tc, t_student as s, t_teacher as t, t_course as c" +
                " where 1 = 1 and sc.studentId = s.id and sc.teacherCourseId = tc.id and tc.teacherId = t.id and tc.courseId = c.id ";
        ArrayList<String> paramList = new ArrayList<>();
        if (studentId > 0 ){
            sql += " and s.id = ?";
            paramList.add(studentId + "");
        }
        if (studentName != null && !"".equals(studentName)){
            sql += " and s.name like ?";
            paramList.add("%" + studentName + "%");
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
     * 根据教师任课id查询评教平均分
     * @param teacherCourseId
     * @return
     * @throws SQLException
     */
    public Double selectAvgScoreByTeacherCourseId(int teacherCourseId) throws SQLException {
        //当平均分为null时，返回0
        String sql = "select COALESCE(avg(score), 0) as avgScore from t_student_course tsc where tsc.status = 1 and tsc.teacherCourseId = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        Object avgScore =  runner.query(sql, new ScalarHandler(), teacherCourseId);
        return ((BigDecimal) avgScore).doubleValue();
    }































    /**
     * 根据学生查询选课信息列表
     * @return
     * @throws SQLException
     */
    public List<StudentCourse> selectAllStudentCourseByStudentId(int id, String courseName, int pageNum, int pageSize) throws SQLException {
        String sql = "select *, s.name as studentName, c.name as courseName, t.name as teacherName from t_student_course as sc, t_teacher_course as tc, t_student as s, t_teacher as t, t_course as c" +
                " where 1 = 1 and sc.teacherCourseId = tc.id and tc.teacherId = t.id and tc.courseId = c.id ";
        ArrayList<String> paramList = new ArrayList<>();
        if (id > 0 ){
            sql += " and s.id = ?";
            paramList.add(id + "");
        }
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<StudentCourse> studentCourseList = (List<StudentCourse>) runner.query(sql, new BeanListHandler(StudentCourse.class), paramList.toArray());
        return studentCourseList;
    }

    public long countByStudentId(int id, String courseName) throws SQLException {
        String sql = "select count(*) from t_student_course as sc, t_student as s, t_teacher_course as tc, t_teacher as t, t_course as c" +
                " where 1 = 1 and sc.teacherCourseId = tc.id and tc.teacherId = t.id and tc.courseId = c.id ";
        ArrayList<String> paramList = new ArrayList<>();
        if (id > 0 ){
            sql += " and s.id = ?";
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
