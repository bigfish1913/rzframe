package com.rz.frame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
	@ResponseBody
	@RequestMapping("/buy")
	public String buy() {
		return "buy success....";
	}
}
