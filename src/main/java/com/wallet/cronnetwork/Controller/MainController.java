package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Data.AcoinAddressVO;
import com.wallet.cronnetwork.Mapper.AcoinAddressMapper;
import com.wallet.cronnetwork.Mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    MainMapper mainMapper;

   
    @RequestMapping("/")
    public String getMainPage(Model model){

        ArrayList<String> addressList;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();

        addressList = mainMapper.getAddress(id);

        model.addAttribute("addressList", addressList);

        return "main";
    }
}
