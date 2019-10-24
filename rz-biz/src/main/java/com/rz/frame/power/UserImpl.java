package com.rz.frame.power;

import com.rz.frame.entity.RzUser;
import com.rz.frame.utils.DalFactory;

import java.util.List;

public class UserImpl {

    public RzUser getUserByUserName(String userName) {
        return DalFactory.getRzUserExDao().getUserByLoginName(userName);
    }

    public List<RzUser> getAllUser() {
        return DalFactory.getRzUserExDao().getAllUser();
    }
}
