package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Data.AcoinAddressVO;
import com.wallet.cronnetwork.Mapper.AcoinAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private AcoinAddressMapper acoinaddressmapper;

    @RequestMapping("/")
    public String getMainPage(Model model){

        AcoinAddressVO vo;
        try {
            vo = acoinaddressmapper.select("NHb3CJAWyw4Nj31VRWh36UkukG4b9dtyTs");
            model.addAttribute("amount", vo.getAmount());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "main";
    }
}
