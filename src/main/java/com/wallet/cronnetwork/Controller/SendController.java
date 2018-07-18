package com.wallet.cronnetwork.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendController {

    @RequestMapping("/send")
    public String getSendPage(Model model){
/*        AcoinAddressVO vo;
        try {
            vo = acoinaddressmapper.select("NHb3CJAWyw4Nj31VRWh36UkukG4b9dtyTs");
            model.addAttribute("address", vo.getAddress());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        return "send";
    }
}
