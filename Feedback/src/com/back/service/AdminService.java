package com.back.service;

import com.back.bean.Admin;
import com.back.dao.AdminDao;

import java.sql.SQLException;
import java.util.List;

public class AdminService {

    private AdminDao adminDao = new AdminDao();

    public int add(Admin admin) throws SQLException {
        return adminDao.insertAdmin(admin);
    }

    public int edit(Admin admin) throws SQLException {
        return adminDao.updateAdmin(admin);
    }

    public int remove(int id) throws SQLException {
        return adminDao.deleteAdminById(id);
    }

    public List<Admin> selectAll(String adminNo, String adminName, int pageNum, int pageSize) throws SQLException {
        return adminDao.selectAllAdmin(adminNo, adminName, pageNum, pageSize);
    }

    public Admin login(Admin admin) throws SQLException {
        return adminDao.selectAdminByAccountAndPassword(admin);
    }


    public long count(String adminNo, String adminName) throws SQLException {
        return adminDao.count(adminNo, adminName);
    }
}
