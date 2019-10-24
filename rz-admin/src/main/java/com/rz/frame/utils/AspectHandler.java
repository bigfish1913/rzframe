package com.rz.frame.utils;

import com.rz.frame.model.UserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


public class AspectHandler implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        UserInfo userInfo = checkLogin(httpServletRequest, httpServletResponse);
        return userInfo != null;
    }

    private UserInfo checkLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
        if (StringUtils.isEmpty(requestURI) || requestURI.equals("/")) {
            httpServletResponse.sendRedirect("/home");
            return null;
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null || cookies.length == 0) {
            httpServletResponse.sendRedirect("/login");
            return null;
        }
        Cookie authorCookie = Arrays.asList(cookies).stream().filter(x -> x.getName().equals(GlobalConstant.LoginInfo.AuthorKey)).findFirst().orElse(null);
        if (authorCookie == null) {
            httpServletResponse.sendRedirect("/login");
            return null;
        }
        String uuidKey = authorCookie.getValue();
        UserInfo userInfo = RedisUtils.get(uuidKey, UserInfo.class);
        if (userInfo == null) {
            httpServletResponse.sendRedirect("/login");
            return null;
        }
        return userInfo;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        UserInfo userInfo = checkLogin(httpServletRequest, httpServletResponse);
        if(userInfo==null){
            return;
        }
        if(modelAndView!=null){
            modelAndView.addObject("menus", userInfo.getMenuList());
            modelAndView.addObject("userInfo", userInfo);
        }


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
