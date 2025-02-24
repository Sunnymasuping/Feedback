package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.Course;
import com.back.bean.ResultModel;
import com.back.service.CourseService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/course")
public class CourceServlet extends HttpServlet {

    private CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.setContentType("application/json;charset=utf-8");
//        req.setCharacterEncoding("UTF-8");

        ResultModel resultModel = new ResultModel();
        List<Course> coursesList = null;
        try {
            String courseName = req.getParameter("name");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            coursesList = courseService.selectAll(courseName, pageNum, pageSize);
            long total = courseService.count(courseName, pageNum, pageSize);
            resultModel.setData(coursesList);
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
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.setContentType("application/json;charset=utf-8");
//        req.setCharacterEncoding("UTF-8");
        ResultModel resultModel = new ResultModel();
        String method = req.getParameter("method");

        try {//将前端传送的数据解析为实体类
            if ("add".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Course course = JSON.toJavaObject(jsonData, Course.class);
                resultModel = this.add(course);
            }else if ("edit".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Course course = JSON.toJavaObject(jsonData, Course.class);
                resultModel = this.edit(course);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(Course Course) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = courseService.add(Course);
        if (row > 0) {
            resultModel.setMsg("新增课程信息成功");
            resultModel.setData(Course);
        } else {
            resultModel.setMsg("新增课程信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel edit(Course Course) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = courseService.edit(Course);
        if (row > 0) {
            resultModel.setMsg("修改课程信息成功");
        } else {
            resultModel.setMsg("修改课程信息败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = courseService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除课程信息成功");
        } else {
            resultModel.setMsg("删除课程信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

}
