package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.Notice;
import com.back.bean.ResultModel;
import com.back.service.NoticeService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {

    private NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResultModel resultModel = new ResultModel();
        List<Notice> noticesList = null;
        try {
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            noticesList = noticeService.selectAll(pageNum, pageSize);
            long total = noticeService.count();
            resultModel.setData(noticesList);
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
                Notice notice = JSON.toJavaObject(jsonData, Notice.class);
                resultModel = this.add(notice);
            }else if ("edit".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Notice notice = JSON.toJavaObject(jsonData, Notice.class);
                resultModel = this.edit(notice);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(Notice Notice) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = noticeService.add(Notice);
        if (row > 0) {
            resultModel.setMsg("新增系统公告成功");
            resultModel.setData(Notice);
        } else {
            resultModel.setMsg("新增系统公告失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel edit(Notice Notice) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = noticeService.edit(Notice);
        if (row > 0) {
            resultModel.setMsg("修改系统公告成功");
        } else {
            resultModel.setMsg("修改系统公告失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = noticeService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除系统公告成功");
        } else {
            resultModel.setMsg("删除系统公告失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

}
