package com.rz.frame.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RzController {
    @GetMapping("test")
    public String login() throws Exception {
//        System.out.println(1);
//        SendMessage sendMessage=new SendMessage();
//        sendMessage.sentMsg();
        System.out.println(4);
        return "ok";

    }
}
