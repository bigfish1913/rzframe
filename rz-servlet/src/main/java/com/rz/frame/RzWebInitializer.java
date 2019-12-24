package com.rz.frame;

import com.rz.frame.config.RzAppConfig;
import com.rz.frame.config.RzRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RzWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	//根容器
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RzRootConfig.class};
	}
	
	//子容器
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{RzAppConfig.class};
	}
	
	//获取DispatchServlet的映射信息
	//拦截所有请求：js,css,png,不包括jsp
	//jsp解析是tomcat的jsp的引擎解析的
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}
