package com.wallet.cronnetwork.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wallet.cronnetwork.Mapper.LoginMapper;
import com.wallet.cronnetwork.Service.OTPService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    OTPService otpService;

    @RequestMapping("/login")
    public String getLoginPage(){

        AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
        Set<String> roles =
                AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println(roles);

        if(roles.contains("ROLE_ANONYMOUS")){
            return "login";
        }else if(roles.contains("ROLE_TEMPORARY")){
            return "loginOtp";
        }else{
            return "redirect:/";
        }

    }


    @RequestMapping("/login_error")
    public String getErrorPage(){

        AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

        if(trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())){
            return "error";
        }else{
            return "redirect:/";
        }

    }

    @RequestMapping("/api/loginOTP")
    @ResponseBody
    public String loginOtpPage(@Param("code") String code){

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        String encodeKey = loginMapper.getOtpKey(id);

        boolean result = otpService.checkOTP(encodeKey, code);

        if(result){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
            updatedAuthorities.clear();
            updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(newAuth);

            return "otpLoginSuccess!!";
        }else{
            return "otpLoginFail!!";
        }
    }
}
