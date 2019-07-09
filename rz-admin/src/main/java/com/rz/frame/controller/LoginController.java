package com.rz.frame.controller;

import com.rz.frame.model.JsonResult;
import com.rz.frame.model.Menu;
import com.rz.frame.model.UserInfo;
import com.rz.frame.utils.GlobalConstant;
import com.rz.frame.utils.RedisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Controller
public class LoginController extends BaseController {

    public JsonResult login(String userName, String password, String valideCode) {
        JsonResult result = new JsonResult();
        return result;
    }

    @GetMapping("login")
    public ModelAndView login() throws Exception {
        //        throw new RuntimeException("测试异常");
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @GetMapping("home")
    public ModelAndView home() {
        ModelAndView view = new ModelAndView();
        view.setViewName("home");
        view.addObject("menus", getMenus());
        view.addObject("userInfo", getCurrentUserInfo());
        return view;
    }

    @RequestMapping(value = "valide", method = RequestMethod.POST)
    public ModelAndView valide(HttpServletResponse response, String userName, String password) {
        UserInfo currentUserInfo = getCurrentUserInfo();
        if (currentUserInfo == null) {
            if (!userName.equals("admin") || !password.equals("111111")) {
                ModelAndView view = new ModelAndView();
                view.setViewName("login");
                view.addObject("errorMsg", "用户名或者密码错误");
                return view;
            }
            //登录成功
            String uid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(GlobalConstant.LoginInfo.AuthorKey, uid);
            response.addCookie(cookie);
            currentUserInfo = new UserInfo();
            currentUserInfo.setUserName(userName);
            currentUserInfo.setUserToken(uid);
            RedisUtils.set(uid, currentUserInfo);
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:home");
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
