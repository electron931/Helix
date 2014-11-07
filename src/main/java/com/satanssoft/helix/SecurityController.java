package com.satanssoft.helix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SecurityController {


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {

        return "login";
    }

    /*@RequestMapping(value = {"/process-login"}, method = RequestMethod.POST)
    @ResponseBody
    public String processLogin(Model model) {

        return "process-login";
    }*/

    @RequestMapping(value = {"/success-login"}, method = RequestMethod.GET)
    @ResponseBody
    public String successLogin(Model model) {

        return "success-login";
    }


    /*@RequestMapping(value = {"/error-login"}, method = RequestMethod.GET)
    @ResponseBody
    public String errorLogin(Model model) {

        return "error-login";
    }*/

}
