package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.Admin;
import com.back.bean.ResultModel;
import com.back.bean.Student;
import com.back.service.AdminService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.setContentType("application/json;charset=utf-8");
//        req.setCharacterEncoding("UTF-8");

        ResultModel resultModel = new ResultModel();
        List<Admin> adminsList = null;
        try {
            String adminNo = req.getParameter("account");
            String adminName = req.getParameter("name");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            adminsList = adminService.selectAll(adminNo, adminName, pageNum, pageSize);
            long total = adminService.count(adminNo, adminName);
            resultModel.setTotal(total);
            resultModel.setData(adminsList);
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
                Admin admin = JSON.toJavaObject(jsonData, Admin.class);
//                Admin admin = new Admin();
//                admin.setAccount(req.getParameter("account"));
//                admin.setName(req.getParameter("name"));
//                admin.setGender(Integer.valueOf(req.getParameter("gender")));
//                admin.setPhone(req.getParameter("phone"));
//                admin.setBirthday(req.getParameter("birthday"));
//                admin.setPassword(req.getParameter("password"));
//                admin.setNativeplace(req.getParameter("nativeplace"));
//                admin.setPicture(req.getParameter("picture"));

                resultModel = this.add(admin);
            }else if ("edit".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Admin admin = JSON.toJavaObject(jsonData, Admin.class);
//                Admin admin = new Admin();
//                admin.setId(Integer.parseInt(req.getParameter("id")));
//                admin.setAccount(req.getParameter("account"));
//                admin.setName(req.getParameter("name"));
//                admin.setGender(Integer.valueOf(req.getParameter("gender")));
//                admin.setPhone(req.getParameter("phone"));
//                admin.setBirthday(req.getParameter("birthday"));
//                admin.setPassword(req.getParameter("password"));
//                admin.setNativeplace(req.getParameter("nativeplace"));
//                admin.setPicture(req.getParameter("picture"));

                resultModel = this.edit(admin);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(Admin Admin) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = adminService.add(Admin);
        if (row > 0) {
            resultModel.setMsg("新增管理员信息成功");
            resultModel.setData(Admin);
        } else {
            resultModel.setMsg("新增管理员信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel edit(Admin Admin) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = adminService.edit(Admin);
        if (row > 0) {
            resultModel.setMsg("修改管理员信息成功");
        } else {
            resultModel.setMsg("修改管理员信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = adminService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除管理员信息成功");
        } else {
            resultModel.setMsg("删除管理员信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }
    
}
