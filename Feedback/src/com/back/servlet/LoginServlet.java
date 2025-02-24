package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.*;
import com.back.service.AdminService;
import com.back.service.CourseService;
import com.back.service.StudentService;
import com.back.service.TeacherService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AdminService adminService = new AdminService();
    private TeacherService teacherService = new TeacherService();
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel = new ResultModel();
        String method = req.getParameter("method");

        try {
            if ("admin".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Admin admin = JSON.toJavaObject(jsonData, Admin.class);
                resultModel = this.adminLogin(admin);
            }else if ("teacher".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Teacher teacher = JSON.toJavaObject(jsonData, Teacher.class);
                resultModel = this.teacherLogin(teacher);
            }else if ("student".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Student student = JSON.toJavaObject(jsonData, Student.class);
                resultModel = this.studentLogin(student);
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel adminLogin(Admin admin) throws SQLException {
        ResultModel resultModel = new ResultModel();
        Admin login = adminService.login(admin);
        if (login != null) {
            resultModel.setMsg("登录成功");
            resultModel.setData(login);
        } else {
            resultModel.setMsg("账号或密码不正确！");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel teacherLogin(Teacher teacher) throws SQLException {
        ResultModel resultModel = new ResultModel();
        Teacher login = teacherService.login(teacher);
        if (login != null) {
            resultModel.setMsg("登录成功");
            resultModel.setData(login);
        } else {
            resultModel.setMsg("账号或密码不正确！");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel studentLogin(Student student) throws SQLException {
        ResultModel resultModel = new ResultModel();
        Student login = studentService.login(student);
        if (login != null) {
            resultModel.setMsg("登录成功");
            resultModel.setData(login);
        } else {
            resultModel.setMsg("账号或密码不正确！");
            resultModel.setCode(500);
        }
        return resultModel;
    }
}
