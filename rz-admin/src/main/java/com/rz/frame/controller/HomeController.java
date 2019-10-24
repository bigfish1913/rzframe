package com.rz.frame.controller;

import com.rz.frame.model.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("home")
public class HomeController extends BaseController {

    @GetMapping("")
    public ModelAndView home() {
        ModelAndView view = new ModelAndView();
        view.setViewName("home");
        return view;
    }
}
