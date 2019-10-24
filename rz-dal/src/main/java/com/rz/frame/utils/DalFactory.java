package com.rz.frame.utils;

import com.rz.frame.dao.RzUserDao;
import com.rz.frame.exdao.RzMenuExDao;
import com.rz.frame.exdao.RzRoleExDao;
import com.rz.frame.exdao.RzUserExDao;

import java.sql.SQLException;

public class DalFactory {
    private static RzUserExDao rzUserExDao;
    private static RzRoleExDao rzRoleExDao;
    private static RzMenuExDao rzMenuExDao;

    public static RzUserExDao getRzUserExDao() {
        try {
            if (rzUserExDao == null) {
                rzUserExDao = new RzUserExDao();
            }
            return rzUserExDao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static RzRoleExDao getRzRoleExDao() {
        try {
            if (rzRoleExDao == null) {
                rzRoleExDao = new RzRoleExDao();
            }
            return rzRoleExDao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RzMenuExDao getRzMenuExDao() {
        try {
            if (rzMenuExDao == null) {
                rzMenuExDao = new RzMenuExDao();
            }
            return rzMenuExDao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }



}
