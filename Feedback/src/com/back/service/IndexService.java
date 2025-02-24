package com.back.service;

import com.back.bean.Index;
import com.back.dao.IndexDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IndexService {

    private IndexDao indexDao = new IndexDao();

    public int add(Index index) throws SQLException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        index.setCreatetime(format.format(date));
        return indexDao.insertIndex(index);
    }

    public int edit(Index index) throws SQLException {
        return indexDao.updateIndex(index);
    }

    public int remove(int id) throws SQLException {
        return indexDao.deleteIndexById(id);
    }

    public List<Index> selectAll(String content, int pageNum, int pageSize) throws SQLException {
        return indexDao.selectAllIndex(content, pageNum, pageSize);
    }

    public long count(String content) throws SQLException {
        return indexDao.count(content);
    }
}
