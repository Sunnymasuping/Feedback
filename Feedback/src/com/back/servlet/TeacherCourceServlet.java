package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.TeacherCourse;
import com.back.bean.ResultModel;
import com.back.service.TeacherCourseService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/teacherCourse")
public class TeacherCourceServlet extends HttpServlet {

    private TeacherCourseService teacherTeacherCourseService = new TeacherCourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResultModel resultModel = new ResultModel();
        List<TeacherCourse> teacherCoursesList = null;
        try {
            String teacherIdString = req.getParameter("teacherId");
            Integer teacherId = Integer.valueOf(teacherIdString==null? "0" : teacherIdString);
            String teacherName = req.getParameter("teacherName");
            String courseName = req.getParameter("courseName");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            teacherCoursesList = teacherTeacherCourseService.selectAll(teacherId, teacherName,courseName, pageNum, pageSize);
            long total = teacherTeacherCourseService.count(teacherId, teacherName, courseName);
            resultModel.setData(teacherCoursesList);
            resultModel.setTotal(total);
            resultModel.setCode(200);
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }
        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel = new ResultModel();
        String method = req.getParameter("method");

        try {
            if ("add".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                TeacherCourse teacherCourse = JSON.toJavaObject(jsonData, TeacherCourse.class);
                resultModel = this.add(teacherCourse);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(TeacherCourse TeacherCourse) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = teacherTeacherCourseService.add(TeacherCourse);
        if (row > 0) {
            resultModel.setMsg("新增任课信息成功");
            resultModel.setData(TeacherCourse);
        } else {
            resultModel.setMsg("新增任课信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = teacherTeacherCourseService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除任课信息成功");
        } else {
            resultModel.setMsg("删除任课信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

}
