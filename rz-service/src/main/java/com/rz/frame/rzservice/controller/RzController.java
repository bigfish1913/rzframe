package com.rz.frame.rzservice.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@RestController
public class RzController {
	@RequestMapping(value = "/api/apiList")
	public String apiList() {
	 return "12312";
	}
	
	
}
