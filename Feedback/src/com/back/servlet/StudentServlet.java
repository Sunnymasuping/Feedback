package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.Student;
import com.back.bean.ResultModel;
import com.back.bean.Teacher;
import com.back.service.StudentService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.setContentType("application/json;charset=utf-8");
//        req.setCharacterEncoding("UTF-8");

        ResultModel resultModel = new ResultModel();
        List<Student> studentsList = null;
        try {
            String studentNo = req.getParameter("no");
            String studentName = req.getParameter("name");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            studentsList = studentService.selectAll(studentNo, studentName, pageNum, pageSize);
            long total = studentService.count(studentNo, studentName);
            resultModel.setTotal(total);
            resultModel.setData(studentsList);
            resultModel.setCode(200);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        try {
            if ("add".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Student student = JSON.toJavaObject(jsonData, Student.class);
//                Student student = new Student();
//                student.setNo(req.getParameter("no"));
//                student.setName(req.getParameter("name"));
//                student.setGender(Integer.valueOf(req.getParameter("gender")));
//                student.setPhone(req.getParameter("phone"));
//                student.setBirthday(req.getParameter("birthday"));
//                student.setPassword(req.getParameter("password"));
//                student.setNativeplace(req.getParameter("nativeplace"));
//                student.setPicture(req.getParameter("picture"));

                resultModel = this.add(student);
            }else if ("edit".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Student student = JSON.toJavaObject(jsonData, Student.class);
//                Student student = new Student();
//                student.setId(Integer.parseInt(req.getParameter("id")));
//                student.setNo(req.getParameter("no"));
//                student.setName(req.getParameter("name"));
//                student.setGender(Integer.valueOf(req.getParameter("gender")));
//                student.setPhone(req.getParameter("phone"));
//                student.setBirthday(req.getParameter("birthday"));
//                student.setPassword(req.getParameter("password"));
//                student.setClassname(req.getParameter("classname"));
//                student.setNativeplace(req.getParameter("nativeplace"));
//                student.setPicture(req.getParameter("picture"));

                resultModel = this.edit(student);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(Student Student) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = studentService.add(Student);
        if (row > 0) {
            resultModel.setMsg("新增学生信息成功");
            resultModel.setData(Student);
        } else {
            resultModel.setMsg("新增学生信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel edit(Student Student) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = studentService.edit(Student);
        if (row > 0) {
            resultModel.setMsg("修改学生信息成功");
        } else {
            resultModel.setMsg("修改学生信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = studentService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除学生信息成功");
        } else {
            resultModel.setMsg("删除学生信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

}
