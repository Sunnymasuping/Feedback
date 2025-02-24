package com.back.dao;

import com.back.bean.Admin;
import com.back.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    /**
     * 添加管理信息
     * @param admin
     * @return
     * @throws SQLException
     */
    public int insertAdmin(Admin admin) throws SQLException {
        int row = 0;
        String sql = "insert into t_admin(account,name,gender,phone,birthday,password,nativePlace) values(?, ?, ?, ?, ?, ?, ?)";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, admin.getAccount(), admin.getName(), admin.getGender(), admin.getPhone(), admin.getBirthday(),
                admin.getPassword(), admin.getNativeplace());
        return row;
    }

    /**
     * 更新管理员信息
     * @param admin
     * @return
     * @throws SQLException
     */
    public int updateAdmin(Admin admin) throws SQLException {
        int row = 0;
        String sql = "update t_admin set account = ?, name = ?, gender = ?, phone = ?, birthday = ?, password = ?, nativePlace = ? where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, admin.getAccount(), admin.getName(), admin.getGender(), admin.getPhone(), admin.getBirthday(),
                admin.getPassword(), admin.getNativeplace(), admin.getId());
        return row;
    }

    /**
     * 删除管理员信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteAdminById(int id) throws SQLException {
        int row = 0;
        String sql = "delete from t_admin where id = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        row = runner.update(sql, id);
        return row;
    }

    /**
     * 查询管理员列表
     * @return
     * @throws SQLException
     */
    public List<Admin> selectAllAdmin(String adminNo, String adminName, int pageNum, int pageSize) throws SQLException {
        String sql = "select * from t_admin where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (adminNo != null && !"".equals(adminNo)){
            sql += " and account = ?";
            paramList.add(adminNo);
        }
        if (adminName != null && !"".equals(adminName)){
            sql += " and name like ?";
            paramList.add("%" + adminName + "%");
        }
        sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize * pageNum;
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        List<Admin> adminList = (List<Admin>) runner.query(sql, new BeanListHandler(Admin.class), paramList.toArray());
        return adminList;
    }

    /**
     * 根据账号和密码查询管理员
     * @param admin
     * @return
     * @throws SQLException
     */
    public Admin selectAdminByAccountAndPassword(Admin admin) throws SQLException {
        String sql = "select * from t_admin where account = ? and password = ?";
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        Admin adminRuselt = (Admin) runner.query(sql, new BeanHandler(Admin.class), admin.getAccount(), admin.getPassword());
        return adminRuselt;
    }

    public long count(String adminNo, String adminName) throws SQLException {
        String sql = "select count(*) from t_admin where 1 = 1 ";
        ArrayList<String> paramList = new ArrayList<>();
        if (adminNo != null && !"".equals(adminNo)){
            sql += " and account = ?";
            paramList.add(adminNo);
        }
        if (adminName != null && !"".equals(adminName)){
            sql += " and name like ?";
            paramList.add("%" + adminName + "%");
        }
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        long total =  runner.query(sql, new ScalarHandler<Long>(), paramList.toArray());
        return total;
    }
}
