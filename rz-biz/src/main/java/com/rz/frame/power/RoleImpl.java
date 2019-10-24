package com.rz.frame.power;

import com.rz.frame.entity.RzRole;
import com.rz.frame.entity.RzUser;
import com.rz.frame.utils.DalFactory;

public class RoleImpl {
    public RzRole getRoleByUserName(String userName) {
        return DalFactory.getRzRoleExDao().getRoleByLoginName(userName);
    }
}
