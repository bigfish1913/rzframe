package com.rz.frame.controller;

import com.rz.frame.utils.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreeMarkerController {

    @Autowired
    private Resource resource;

    @RequestMapping(value = "author")
    public String index(ModelMap map){
        map.addAttribute("resource",resource);
        return "author";
    }
}
