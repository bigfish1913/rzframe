package com.rz.frame.controller;

import com.rz.frame.model.JsonResult;
import com.rz.frame.model.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Controller
public class LoginController {

    public JsonResult login(String userName, String password, String valideCode) {
        JsonResult result = new JsonResult();
        return result;
    }

    @GetMapping("login")
    public ModelAndView login() throws Exception {
        throw new RuntimeException("测试异常");
//        ModelAndView view = new ModelAndView();
//        view.setViewName("login");
//        return view;
    }

    @GetMapping("index")
    public ModelAndView home() {
        ModelAndView view = new ModelAndView();
        view.setViewName("home");
        view.addObject("menus", getMenus());
        return view;
    }

    private List<Menu> getMenus() {
        List<Menu> menuList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Menu menu = new Menu();
            menu.setMenuName("菜单" + i);
            menu.setIcon("fa-desktop");
            menu.setMenuUrl("/");
            menu.setSubMeuns(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                Menu subMenu = new Menu();
                subMenu.setMenuName("子菜单" + j);
                subMenu.setIcon("fa-desktop");
                subMenu.setMenuUrl("/");
                menu.getSubMeuns().add(subMenu);
            }
            menuList.add(menu);
        }
        return menuList;
    }


}
