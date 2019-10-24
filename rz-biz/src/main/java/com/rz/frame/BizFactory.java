package com.rz.frame;

import com.rz.frame.power.MenuImpl;
import com.rz.frame.power.RoleImpl;
import com.rz.frame.power.UserImpl;

public class BizFactory {
    public final static UserImpl userImpl = new UserImpl();
    public final static RoleImpl roleImpl = new RoleImpl();
    public final static MenuImpl menuImpl = new MenuImpl();
}
