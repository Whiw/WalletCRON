package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ModifyController {
    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/modify")
    public String getTradeLogPage(Model model){


        return "modify";
    }

    @RequestMapping(value = "/api/changePw", method = RequestMethod.GET)
    @ResponseBody
    public String changePw(@RequestParam("password") String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        int result = memberMapper.changePassword(id, password);
        if (result > 0){
            return "pwChangeSuccess!!";
        }else{
            return "pwChangeFail!!";
        }

    }

    @RequestMapping(value = "/api/changeHp", method = RequestMethod.GET)
    @ResponseBody
    public String changeHp(@RequestParam("phone") String phone){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        int result = memberMapper.changeHp(id, phone);

        if(result > 0){
            return "HpChangeSuccess!!";
        }else{
            return "HpChangeFail!!";
        }
    }


}
