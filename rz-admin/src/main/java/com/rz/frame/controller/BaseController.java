package com.rz.frame.controller;

import com.rz.frame.model.UserInfo;
import com.rz.frame.utils.GlobalConstant;
import com.rz.frame.utils.RedisUtils;
import org.apache.http.HttpRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class BaseController {
    public UserInfo getCurrentUserInfo() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        Cookie authorCookie = Arrays.asList(cookies).stream().filter(x -> x.getName().equals(GlobalConstant.LoginInfo.AuthorKey)).findFirst().orElse(null);
        if (authorCookie == null) {
            return null;
        }
        String uuidKey = authorCookie.getValue();
        UserInfo userInfo = RedisUtils.get(uuidKey, UserInfo.class);
        return userInfo;

    }
}
