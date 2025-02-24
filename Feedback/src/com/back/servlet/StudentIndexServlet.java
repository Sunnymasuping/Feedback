package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.*;
import com.back.service.*;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/studentIndex")
public class StudentIndexServlet extends HttpServlet {

    private StudentIndexService studentIndexService = new StudentIndexService();

    private StudentCourseService studentCourseService = new StudentCourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel = new ResultModel();
        String method = req.getParameter("method");
        try {
            if ("detail".equals(method)){
                int studentCourseId = Integer.parseInt(req.getParameter("studentCourseId"));
                List<StudentIndex> studentIndexList = studentIndexService.selectDetailByStudentCourseId(studentCourseId);
                resultModel.setData(studentIndexList);
                resultModel.setCode(200);
            }else {
                List<StudentIndex> studentIndexList = null;
                int teacherId = Integer.parseInt(req.getParameter("teacherId"));
                String courseName = req.getParameter("courseName");
                int pageNum = Integer.parseInt(req.getParameter("pageNum"));
                int pageSize = Integer.parseInt(req.getParameter("pageSize"));
                studentIndexList = studentIndexService.selectAllTeacherCourseComment(teacherId, courseName, pageNum, pageSize);
                long total = studentIndexService.countComment(teacherId, courseName);
                resultModel.setData(studentIndexList);
                resultModel.setTotal(total);
                resultModel.setCode(200);
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }
        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel = new ResultModel();
        try {
            JSONObject jsonData = HttpGetJson.getJson(req);
            int totalScore = 0;
            JSONArray studentIndexList = (JSONArray) jsonData.get("studentIndexList");
            for (int i = 0; i < studentIndexList.size(); ++i){
                JSONObject jsonObject = studentIndexList.getJSONObject(i);
                StudentIndex studentIndex = jsonObject.toJavaObject(StudentIndex.class);
                totalScore += studentIndex.getScore();
                studentIndexService.add(studentIndex);
            }
            //获取评教平均分
            totalScore /= studentIndexList.size();
            //更新学生选课信息
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setId((Integer) jsonData.get("studentCourseId"));
            studentCourse.setScore(totalScore);
            studentCourse.setStatus(1);
            studentCourse.setContent((String) jsonData.get("content"));
            studentCourseService.edit(studentCourse);

            resultModel.setCode(200);
            resultModel.setMsg("评教成功");
        } catch (Exception e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }


}
