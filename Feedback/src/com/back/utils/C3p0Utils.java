package com.back.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Utils {
    private static DataSource ds;
    static {
        ds = new ComboPooledDataSource();
    }
    public static DataSource getDataSource() {
        return ds;
    }
}
