package com.wallet.cronnetwork.Controller;

import com.wallet.cronnetwork.Mapper.SecPwMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecPwController {

    @Autowired
    SecPwMapper secPwMapper;

    @RequestMapping("/sPw")
    public String getSecPwPage(Model model){


        return "sPw";
    }

    @RequestMapping("/api/checkSecPw")
    @ResponseBody
    public String checkSecPw(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        int checkResult = secPwMapper.checkSecPW(id);

        if(checkResult > 0){
            return "secPwExist!!";
        }else{
            return "secPwNone!!";
        }
    }

    @RequestMapping("/api/setSecPw")
    @ResponseBody
    public String setSecPw(@RequestParam("sPw") String sPw){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        int setResult = secPwMapper.insertSecPw(id, sPw);

        if(setResult > 0){
            return "setSuccess!!";
        }else{
            return "setFail!!";
        }
    }

    @RequestMapping("/api/deleteSecPw")
    @ResponseBody
    public String deleteSecPw(@RequestParam("sPw") String sPw){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        int deleteResult = secPwMapper.deleteSecPw(id, sPw);

        if(deleteResult > 0){
            return "sPwDeleteSuccess!!";
        }else{
            return "sPwDeleteFail!!";
        }
    }
}
