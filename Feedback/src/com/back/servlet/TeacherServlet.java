package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.Course;
import com.back.bean.Teacher;
import com.back.bean.ResultModel;
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

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

    private TeacherService teacherService = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.setContentType("application/json;charset=utf-8");
//        req.setCharacterEncoding("UTF-8");

        ResultModel resultModel = new ResultModel();
        List<Teacher> teachersList = null;
        try {
            String teacherNo = req.getParameter("no");
            String teacheName = req.getParameter("name");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            teachersList = teacherService.selectAll(teacherNo, teacheName, pageNum, pageSize);
            long total = teacherService.count(teacherNo, teacheName);
            resultModel.setTotal(total);
            resultModel.setData(teachersList);
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
                Teacher teacher = JSON.toJavaObject(jsonData, Teacher.class);
//                Teacher teacher = new Teacher();
//                teacher.setNo(req.getParameter("no"));
//                teacher.setName(req.getParameter("name"));
//                teacher.setGender(Integer.valueOf(req.getParameter("gender")));
//                teacher.setPhone(req.getParameter("phone"));
//                teacher.setBirthday(req.getParameter("birthday"));
//                teacher.setPassword(req.getParameter("password"));
//                teacher.setTitle(req.getParameter("title"));
//                teacher.setNativePlace(req.getParameter("nativeplace"));
//                teacher.setPicture(req.getParameter("picture"));

                resultModel = this.add(teacher);
            }else if ("edit".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Teacher teacher = JSON.toJavaObject(jsonData, Teacher.class);
//                Teacher teacher = new Teacher();
//                teacher.setId(Integer.parseInt(req.getParameter("id")));
//                teacher.setNo(req.getParameter("no"));
//                teacher.setName(req.getParameter("name"));
//                teacher.setGender(Integer.valueOf(req.getParameter("gender")));
//                teacher.setPhone(req.getParameter("phone"));
//                teacher.setBirthday(req.getParameter("birthday"));
//                teacher.setPassword(req.getParameter("password"));
//                teacher.setTitle(req.getParameter("title"));
//                teacher.setNativePlace(req.getParameter("nativeplace"));
//                teacher.setPicture(req.getParameter("picture"));

                resultModel = this.edit(teacher);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(Teacher Teacher) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = teacherService.add(Teacher);
        if (row > 0) {
            resultModel.setMsg("新增教师信息成功");
            resultModel.setData(Teacher);
        } else {
            resultModel.setMsg("新增教师信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel edit(Teacher Teacher) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = teacherService.edit(Teacher);
        if (row > 0) {
            resultModel.setMsg("修改教师信息成功");
        } else {
            resultModel.setMsg("修改教师信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = teacherService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除教师信息成功");
        } else {
            resultModel.setMsg("删除教师信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }
    
}
