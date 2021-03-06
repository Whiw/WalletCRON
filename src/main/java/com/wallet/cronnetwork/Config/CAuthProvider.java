package com.wallet.cronnetwork.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.wallet.cronnetwork.Data.CronCustomerDto;
import com.wallet.cronnetwork.Data.MyAuthDto;
import com.wallet.cronnetwork.Service.CUserServiceImpl;

@Component
public class CAuthProvider implements AuthenticationProvider {
    @Autowired
    CUserServiceImpl userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);

    }



    public Authentication authenticate(String id, String password)  throws AuthenticationException {
        String role = "";
        CronCustomerDto cronCustomerDto = userService.findByUsername(id, password);
        if(cronCustomerDto == null){
            return null;
        }


        switch (cronCustomerDto.getEnabled()){
            case 1:
                role = "ROLE_ADMIN";
                break;
            case 2:
                role = "ROLE_USER";
                break;

        }

        if(cronCustomerDto.getOtp().equals("1")){
            role = "ROLE_TEMPORARY";
        }


        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role));

        return new MyAuthDto(id, password, grantedAuthorityList, cronCustomerDto);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
