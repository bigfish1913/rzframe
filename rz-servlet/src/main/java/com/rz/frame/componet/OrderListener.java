package com.rz.frame.componet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//监听项目的启动和停止
public class OrderListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("OrderListener...contextInitialized");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		System.out.println("OrderListener...contextDestroyed");
	}
}
