package com.back.service;

import com.back.bean.Notice;
import com.back.dao.NoticeDao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoticeService {

    private NoticeDao noticeDao = new NoticeDao();

    public int add(Notice notice) throws SQLException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notice.setCreateTime(format.format(date));
        return noticeDao.insertNotice(notice);
    }

    public int edit(Notice notice) throws SQLException {
        return noticeDao.updateNotice(notice);
    }

    public int remove(int id) throws SQLException {
        return noticeDao.deleteNoticeById(id);
    }

    public List<Notice> selectAll(int pageNum, int pageSize) throws SQLException {
        return noticeDao.selectAllNotice(pageNum, pageSize);
    }

    public long count() throws SQLException {
        return noticeDao.count();
    }
}
