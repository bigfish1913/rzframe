package com.rz.frame.rzdal;

import com.rz.frame.entity.RzUser;
import com.rz.frame.rzdal.pool.PoolManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDao {

    public List<RzUser> getUsers() {


        try {
            String sql="select * from rz_user";
            ResultSet resultSet = PoolManager.getInstance().querySql(sql);
            List<RzUser> userList = resultSetToList(resultSet, RzUser.class);
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
