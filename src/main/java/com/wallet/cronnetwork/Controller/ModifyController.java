package com.wallet.cronnetwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModifyController {

    @RequestMapping("/modify")
    public String getTradeLogPage(Model model){


        return "modify";
    }
}
