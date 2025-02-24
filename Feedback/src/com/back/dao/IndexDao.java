package com.back.dao;

import com.back.bean.Index;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndexDao {


    /**
     * 添加评教指标息
     * @param index
     * @return
     * @throws SQLException
     */
    public int insertIndex(Index index) throws SQLException {
        int row = 0;
        String sql = "insert into t_index(content, createTime) values(?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, index.getContent(), index.getCreatetime());
        return row;
    }

    /**
     * 更新评教指标信息
     * @param index
     * @return
     * @throws SQLException
     */
    public int updateIndex(Index index) throws SQLException {
        int row = 0;
        String sql = "update t_index set content = ?, createTime = ? where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, index.getContent(), index.getCreatetime(), index.getId());
        return row;
    }

    /**
     * 删除评教指标信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteIndexById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_index where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询评教指标信息列表
     * @return
     * @throws SQLException
     */
    public List<Index> selectAllIndex(String content, int pageNum, int pageSize) throws SQLException {
        String sql = "select * from t_index where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (content != null && !"".equals(content)){
            sql += " and content like ?";
            paramList.add("%" + content + "%");
        }
        if (pageNum > 0 && pageSize > 0){
            sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<Index> indexList = (List<Index>) runner.query(sql, new BeanListHandler(Index.class), paramList.toArray());
        return indexList;
    }

    public long count(String content) throws SQLException {
        String sql = "select count(*) from t_index where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (content != null && !"".equals(content)){
            sql += " and content like ?";
            paramList.add("%" + content + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }
}
