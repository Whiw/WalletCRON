package com.wallet.cronnetwork.Data;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class MyAuthDto extends UsernamePasswordAuthenticationToken {

    NcoinCustomerDto ncoinCustomerDto;
    CronCustomerDto cronCustomerDto;

    public NcoinCustomerDto getNcoinCustomerDto() {
		return ncoinCustomerDto;
	}
    
    public CronCustomerDto getCronCustomerDto() {
    	return cronCustomerDto;
    }

	public void setNcoinCustomerDto(NcoinCustomerDto ncoinCustomerDto) {
		this.ncoinCustomerDto = ncoinCustomerDto;
	}
	
	public void setCronCustomerDto(CronCustomerDto cronCustomerDto) {
		this.cronCustomerDto = cronCustomerDto;
	}

	public MyAuthDto(String id, String password, List<GrantedAuthority> grantedAuthorityList, NcoinCustomerDto ncoinCustomerDto) {
        super(id, password, grantedAuthorityList);
        this.ncoinCustomerDto = ncoinCustomerDto;
    }
	
	public MyAuthDto(String id, String password, List<GrantedAuthority> grantedAuthorityList, CronCustomerDto cronCustomerDto) {
        super(id, password, grantedAuthorityList);
        this.cronCustomerDto = cronCustomerDto;
    }
}
