package com.wallet.cronnetwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TradeLogController {

    @RequestMapping("/tradeLog")
    public String getTradeLogPage(Model model){


        return "tradeLog";
    }
}
