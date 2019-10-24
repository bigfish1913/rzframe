package com.rz.frame;

import com.mysql.cj.jdbc.Driver;
import com.rz.frame.dao.RzUserDao;
import com.rz.frame.rzdal.UserDao;
import com.rz.frame.entity.RzUser;

import java.sql.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        try {
            doJdbc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        try {
//            ctripdal();
//
//            UserDao userDao = new UserDao();
//            List<RzUser> users = userDao.getUsers();
//            for (RzUser user : users) {
//                System.out.println(user);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private static void doJdbc() throws Exception {

        Driver mysqlDriver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        DriverManager.registerDriver(mysqlDriver);

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.103:3306/qmq_produce?characterEncoding=utf8&useSSL=true&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC", "root", "abc12345");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from broker_group");//查询
        System.out.println(resultSet);


    }

    private static void ctripdal() throws Exception {

        RzUserDao userDao = new RzUserDao();
        List<RzUser> userList = userDao.queryAll();
        System.out.println(userList.size());


    }
}
