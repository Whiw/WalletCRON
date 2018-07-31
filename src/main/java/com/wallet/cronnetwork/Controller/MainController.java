package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Data.AcoinAddressVO;
import com.wallet.cronnetwork.Mapper.AcoinAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

   
    @RequestMapping("/")
    public String getMainPage(Model model){


    	System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
    	System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        return "main";
    }
}
