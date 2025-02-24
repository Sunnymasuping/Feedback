package com.back.dao;

import com.back.bean.Course;
import com.back.bean.StudentIndex;
import com.back.bean.TeacherCourse;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentIndexDao {

    /**
     * 添加评教信息
     * @param studentIndex
     * @return
     * @throws SQLException
     */
    public int insertStudentIndex(StudentIndex studentIndex) throws SQLException {
        int row = 0;
        String sql = "insert into t_student_index(studentCourseId, indexId, score, createTime) values(?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, studentIndex.getStudentCourseId(), studentIndex.getIndexid(), studentIndex.getScore(), studentIndex.getCreateTime());
        return row;
    }

    /**
     * 删除评教信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteStudentIndexById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_student_index where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 根据学生选课id查询评教信息列表
     * @return
     * @throws SQLException
     */
    public List<StudentIndex> selectAllStudentIndexByStudentCourseId(int studentCourseId) throws SQLException {
        String sql = "select * from t_student_index where studentCourseId = ? ";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<StudentIndex> studentIndexList = (List<StudentIndex>) runner.query(sql, new BeanListHandler(StudentIndex.class), studentCourseId);
        return studentIndexList;
    }

    /**
     * 根据教师id查询所任课的所有评教信息
     * @return
     * @throws SQLException
     */
    public List<StudentIndex> selectAllTeacherCourseComment(int teacherId, String courseName, int pageNum, int pageSize) throws SQLException {
        String sql = "select sc.id, s.name studentName, c.name courseName, sc.score, sc.createTime, sc.content  " +
                "from t_teacher t, t_teacher_course tc, t_course c, t_student_course sc, t_student s " +
                "where t.id = tc.teacherId and tc.courseId = c.id and sc.teacherCourseId = tc.id and  s.id = sc.studentId and sc.status = 1 and t.id = ?";
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add(teacherId + "");
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<StudentIndex> teacherCourseList = (List<StudentIndex>) runner.query(sql, new BeanListHandler(StudentIndex.class), paramList.toArray());
        return teacherCourseList;
    }

    public long countComment(int teacherId, String courseName) throws SQLException {
        String sql = "select count(*) " +
                "from t_teacher t, t_teacher_course tc, t_course c, t_student_course sc, t_student s " +
                "where t.id = tc.teacherId and tc.courseId = c.id and sc.teacherCourseId = tc.id and  s.id = sc.studentId and sc.status = 1 and t.id = ?";
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add(teacherId + "");
        if (courseName != null && !"".equals(courseName)){
            sql += " and c.name like ?";
            paramList.add("%" + courseName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }

    /**
     * 根据课程id查询评教评分详情信息
     * @return
     * @throws SQLException
     */
    public List<StudentIndex> selectDetailByStudentCourseId(int studentCourseId) throws SQLException {
        String sql = "select si.score, i.content " +
                "from t_student_course sc, t_student_index si, t_index i " +
                "where sc.id = si.studentCourseId and si.indexId = i.id and sc.id = ?";

        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<StudentIndex> teacherCourseList = (List<StudentIndex>) runner.query(sql, new BeanListHandler(StudentIndex.class), studentCourseId);
        return teacherCourseList;
    }
}
