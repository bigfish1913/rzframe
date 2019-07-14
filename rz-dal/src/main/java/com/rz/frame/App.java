package com.rz.frame;

import com.rz.frame.dao.RzUserDao;
import com.rz.frame.dbtool.UserDao;
import com.rz.frame.entity.RzUser;

import java.sql.*;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        try {
            ctripdal();

            UserDao userDao = new UserDao();
            List<RzUser> users = userDao.getUsers();
            for (RzUser user : users) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doJdbc() throws Exception {

        Driver mysqlDriver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        DriverManager.registerDriver(mysqlDriver);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rzframe?useSSL=false&serverTimezone=UTC", "root", "abc12345");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from rz_user");//查询


    }

    private static void ctripdal() throws Exception {

        RzUserDao userDao = new RzUserDao();
        List<RzUser> userList = userDao.queryAll();
        System.out.println(userList.size());


    }
}
