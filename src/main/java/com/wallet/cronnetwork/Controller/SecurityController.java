package com.wallet.cronnetwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("/security")
    public String getSecurityPage(Model model){


        return "security";
    }
}
