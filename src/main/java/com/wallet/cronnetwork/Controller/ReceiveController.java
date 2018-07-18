package com.wallet.cronnetwork.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class ReceiveController {

    @RequestMapping("/receive")
    public String getReceivePage(Model model) {

/*        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();
        ArrayList<String> addressList = receiveMapper.getAddressList(id);

        model.addAttribute("addressList", addressList);*/

        return "receive";
    }
}
