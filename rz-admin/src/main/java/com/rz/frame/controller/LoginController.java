package com.rz.frame.controller;

import com.rz.frame.BizFactory;
import com.rz.frame.entity.RzMenu;
import com.rz.frame.entity.RzRole;
import com.rz.frame.entity.RzUser;
import com.rz.frame.model.JsonResult;
import com.rz.frame.model.Menu;
import com.rz.frame.model.UserInfo;
import com.rz.frame.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController

public class LoginController extends BaseController {

    public JsonResult login(String userName, String password, String valideCode) {
        JsonResult result = new JsonResult();
        return result;
    }

    @GetMapping("/login")
    public ModelAndView login() throws Exception {
        //        throw new RuntimeException("测试异常");
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @GetMapping("/loginout")
    public void loginout(HttpServletRequest request,HttpServletResponse response) throws Exception {

        CookieUtils.removeCookie(request,response);

    }

    @RequestMapping(value = "valide", method = RequestMethod.POST)
    public ModelAndView valide(HttpServletResponse response, String userName, String password) throws IOException {
        UserInfo currentUserInfo = getCurrentUserInfo();
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/home");
        if (currentUserInfo == null) {
            RzUser dbUser = BizFactory.userImpl.getUserByUserName(userName);
            if (dbUser == null || !MD5Utils.encrypt(password).toLowerCase().equals(dbUser.getLoginPassword())) {
                view.addObject("errorMsg", "用户名或者密码错误");
                return view;
            }
            RzRole rzRole = BizFactory.roleImpl.getRoleByUserName(userName);
            if (rzRole == null) {
                view.addObject("errorMsg", "用户无权登录系统");
                return view;
            }
            List<RzMenu> menuList = BizFactory.menuImpl.getMenusByRole(rzRole.getId());

            //登录成功
            String uid = UUID.randomUUID().toString();

            CookieUtils.setCookie(response,GlobalConstant.LoginInfo.AuthorKey, uid);

            currentUserInfo = new UserInfo();
            currentUserInfo.setUserName(dbUser.getUserName());
            currentUserInfo.setUserId(dbUser.getUserId());
            currentUserInfo.setUserToken(uid);
            currentUserInfo.setRoleId(rzRole.getId());
            currentUserInfo.setRoleName(rzRole.getRoleName());
            currentUserInfo.setMenuList(getMenuList(menuList));
            RedisUtils.set(uid, currentUserInfo);
            view.setViewName("redirect:/home");

            return view;
        }
        return view;
    }


    private List<Menu> getMenuList(List<RzMenu> menuList) {
        List<Menu> menus = new ArrayList<>();
        List<RzMenu> pMenuList = menuList.stream().filter(x -> x.getParentmenuId().equals(0) || x.getParentmenuId() == null).collect(Collectors.toList());
        for (RzMenu rm : pMenuList) {
            Menu pMenu = new Menu();
            pMenu.setMenuUrl(rm.getMenuSrc());
            pMenu.setIcon(StringUtils.isEmpty(rm.getMenuIcon()) ? "" : rm.getMenuIcon());
            pMenu.setMenuName(rm.getMenuName());
            List<Menu> subMenus = menuList.stream().filter(x -> x.getParentmenuId().equals(rm.getId())).map(x -> {
                Menu subMenu = new Menu();
                subMenu.setMenuName(x.getMenuName());
                subMenu.setIcon(StringUtils.isEmpty(rm.getMenuIcon()) ? "" : rm.getMenuIcon());
                subMenu.setMenuUrl(x.getMenuSrc());
                return subMenu;
            }).collect(Collectors.toList());
            pMenu.setSubMeuns(subMenus);
            menus.add(pMenu);
        }
         return menus;
    }


}
