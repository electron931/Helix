package com.satanssoft.helix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SecurityController {


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model, @RequestParam(required = false) String failed) {

        if (failed != null) {
            model.addAttribute("authFailed", true);
        }
        else {
            model.addAttribute("authFailed", false);
        }

        return "login";
    }


}
