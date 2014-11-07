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
    public String admin(Model model) {

        return "redirect:admin/dashboard";
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String dashboard(Model model) {

        model.addAttribute("title", "Admin | Helix");

        return "admin/dashboard";
    }


}
