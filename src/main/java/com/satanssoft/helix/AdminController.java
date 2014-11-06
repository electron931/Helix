package com.satanssoft.helix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    @ResponseBody
    public String admin(Model model) {


        return "admin";
    }



}
