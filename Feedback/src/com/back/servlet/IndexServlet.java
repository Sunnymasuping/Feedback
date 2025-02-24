package com.back.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.back.bean.Admin;
import com.back.bean.Index;
import com.back.bean.ResultModel;
import com.back.service.IndexService;
import com.back.utils.HttpGetJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private IndexService indexService = new IndexService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        resp.setContentType("application/json;charset=utf-8");
//        req.setCharacterEncoding("UTF-8");

        ResultModel resultModel = new ResultModel();
        List<Index> indexsList = null;
        try {
            String content = req.getParameter("content");
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            indexsList = indexService.selectAll(content, pageNum, pageSize);
            long total = indexService.count(content);
            resultModel.setTotal(total);
            resultModel.setData(indexsList);
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
                Index index = JSON.toJavaObject(jsonData, Index.class);
//                Index index = new Index();
//                index.setContent(req.getParameter("content"));

                resultModel = this.add(index);
            }else if ("edit".equals(method)){
                JSONObject jsonData = HttpGetJson.getJson(req);
                Index index = JSON.toJavaObject(jsonData, Index.class);
//                Index index = new Index();
//                index.setId(Integer.parseInt(req.getParameter("id")));
//                index.setContent(req.getParameter("content"));

                resultModel = this.edit(index);
            }else if ("remove".equals(method)){
                resultModel = this.remove(Integer.parseInt(req.getParameter("id")));
            }
        } catch (SQLException e) {
            resultModel.setCode(500);
            resultModel.setMsg("系统异常");
        }

        resp.getWriter().print(JSON.toJSONString(resultModel));
    }

    private ResultModel add(Index Index) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = indexService.add(Index);
        if (row > 0) {
            resultModel.setMsg("新增评教指标信息成功");
            resultModel.setData(Index);
        } else {
            resultModel.setMsg("新增评教指标信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel edit(Index Index) throws SQLException {
        ResultModel resultModel = new ResultModel();
        int row = indexService.edit(Index);
        if (row > 0) {
            resultModel.setMsg("修改评教指标信息成功");
        } else {
            resultModel.setMsg("修改评教指标信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }

    private ResultModel remove(int id) throws SQLException {
        int row = indexService.remove(id);
        ResultModel resultModel = new ResultModel();
        if (row > 0) {
            resultModel.setMsg("删除评教指标信息成功");
        } else {
            resultModel.setMsg("删除评教指标信息失败");
            resultModel.setCode(500);
        }
        return resultModel;
    }
    
}
