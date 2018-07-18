package com.wallet.cronnetwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecPwController {

    @RequestMapping("/sPw")
    public String getSecPwPage(Model model){


        return "sPw";
    }
}
