package com.rz.frame.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class AspectHandler {

    @Pointcut("execution(public * com.rz.frame.controller.*.*(..))")
    public void webLog() {

    }

    //前置
    @Before("webLog()")
    public void doBeafore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println(String.format("Name:%s,Value:%s",name,request.getParameter(name)));
        }
    }


    //后置
    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfter(Object ret) throws Throwable {
        System.out.println(JsonUtils.serializeObject(ret));
    }

}
