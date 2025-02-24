package com.back.dao;

import com.back.bean.Notice;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDao {

    /**
     * 添加公告信息
     * @param notice
     * @return
     * @throws SQLException
     */
    public int insertNotice(Notice notice) throws SQLException {
        int row = 0;
        String sql = "insert into t_notice(title, content, createTime) values(?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, notice.getTitle(), notice.getContent(), notice.getCreateTime());
        return row;
    }

    /**
     * 更新公告信息
     * @param notice
     * @return
     * @throws SQLException
     */
    public int updateNotice(Notice notice) throws SQLException {
        int row = 0;
        String sql = "update t_notice set title = ?, content = ?where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, notice.getTitle(), notice.getContent(), notice.getId());
        return row;
    }

    /**
     * 删除公告信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteNoticeById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_notice where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询公告信息列表
     * @return
     * @throws SQLException
     */
    public List<Notice> selectAllNotice(int pageNum, int pageSize) throws SQLException {
        String sql = "select * from t_notice where 1 = 1 ";
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<Notice> noticeList = (List<Notice>) runner.query(sql, new BeanListHandler(Notice.class));
        return noticeList;
    }

    public long count() throws SQLException {
        String sql = "select count(*) from t_notice where 1 = 1 ";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>());
        return total;
    }
}
