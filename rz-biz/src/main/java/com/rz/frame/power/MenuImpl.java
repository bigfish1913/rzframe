package com.rz.frame.power;

import com.rz.frame.entity.RzMenu;
import com.rz.frame.entity.RzRole;
import com.rz.frame.utils.DalFactory;

import java.util.List;

public class MenuImpl {

    public List<RzMenu> getMenusByRole(long roleId) {
        return DalFactory.getRzMenuExDao().getMenuByRoleId(roleId);
    }
}
