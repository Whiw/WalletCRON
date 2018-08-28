package com.wallet.cronnetwork.Controller;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FindPwController {

    @RequestMapping("/findPw")
    public String getFindPwPage(){

        AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

        if(trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())){
            return "resetPw";
        }else{
            return "redirect:/";
        }

    }
}
