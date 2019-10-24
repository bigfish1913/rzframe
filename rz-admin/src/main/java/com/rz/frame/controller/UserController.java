package com.rz.frame.controller;

import com.rz.frame.BizFactory;
import com.rz.frame.entity.RzUser;
import com.rz.frame.model.JsonResult;
import com.rz.frame.utils.DalFactory;
import com.rz.frame.utils.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping("user")
    public ModelAndView user() {
        ModelAndView view = new ModelAndView();
        view.setViewName("user");
        return view;
    }

    @RequestMapping("user/getAllUser")
    public JsonResult getAllUser() {
        JsonResult<RzUser> jsonResult = new JsonResult<RzUser>();
        jsonResult.setCode(0);
        jsonResult.setMessage("OK");
        List<RzUser> allUser = BizFactory.userImpl.getAllUser();
        jsonResult.setData(allUser);
        return jsonResult;
    }

}
