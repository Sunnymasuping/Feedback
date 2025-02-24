package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.ResultModel;
import com.back.bean.StudentCourse;
import com.back.service.StudentCourseService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/studentCourse")
public class StudentCourceServlet extends HttpServlet {

    private StudentCourseService studentCourseService = new StudentCourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResultModel resultModel = new ResultModel();
        List<StudentCourse> studentCoursesList = null;
        try {
            String studentIdString = req.getParameter("studentId");
            Integer studentId = Integer.valueOf(studentIdString==null? "0" : studentIdString);
            String studentName = req.getParameter("studentName");
            String courseName = req.getParameter("courseName");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            studentCoursesList = studentCourseService.selectAll(studentId, studentName,courseName, pageNum, pageSize);
            long total = studentCourseService.count(studentId, studentName, courseName);
            resultModel.setData(studentCoursesList);
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
                StudentCourse studentCourse = JSON.toJavaObject(jsonData, StudentCourse.class);
                resultModel = this.add(studentCourse);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(StudentCourse StudentCourse) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = studentCourseService.add(StudentCourse);
        if (row > 0) {
            resultModel.setMsg("新增选课信息成功");
            resultModel.setData(StudentCourse);
        } else {
            resultModel.setMsg("新增选课信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = studentCourseService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除选课信息成功");
        } else {
            resultModel.setMsg("删除选课信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

}
