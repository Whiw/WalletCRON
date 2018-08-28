package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Mapper.TradeLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class TradeLogController {
    @Autowired
    TradeLogMapper tradeLogMapper;

    @RequestMapping("/tradeLog")
    public String getTradeLogPage(Model model){

        ArrayList<String> addressList;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();

        addressList = tradeLogMapper.getAddress(id);

        model.addAttribute("addressList", addressList);

        return "tradeLog";
    }
}
