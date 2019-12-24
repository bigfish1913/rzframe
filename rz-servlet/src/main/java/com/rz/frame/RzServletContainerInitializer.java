package com.rz.frame;

import com.rz.frame.componet.OrderFilter;
import com.rz.frame.componet.OrderListener;
import com.rz.frame.componet.OrderServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

@HandlesTypes(value = RzService.class)
public class RzServletContainerInitializer implements ServletContainerInitializer {
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		System.out.println("Start container");
		for (Class<?> cls : c) {
			System.out.println(cls);
		}
		//注册Servlet
		ServletRegistration.Dynamic orderServlet = ctx.addServlet("orderServlet", new OrderServlet());
		orderServlet.addMapping("/orderServlet");
		
		//注册监听器
		ctx.addListener(OrderListener.class);
		
		//注册filter
		FilterRegistration.Dynamic orderFilter = ctx.addFilter("orderFilter", OrderFilter.class);
		//指定拦截哪些Servlet
		orderFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
		
		
		
	}
}
