package com.wallet.cronnetwork.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wallet.cronnetwork.Data.CronCustomerDto;
import com.wallet.cronnetwork.Data.MyAuthDto;
import com.wallet.cronnetwork.Data.NcoinCustomerDto;
import com.wallet.cronnetwork.Mapper.CronCustomerMapper;
import com.wallet.cronnetwork.Mapper.NcoinCustomerMapper;

@Service
public class CUserServiceImpl implements CUserService {

	@Autowired
	private CronCustomerMapper cronCustomerMapper;

	 public CronCustomerDto findByUsername(String username, String password) {

	 	CronCustomerDto cronCustomerDto = cronCustomerMapper.findById(username);
	 	if(cronCustomerDto == null || !cronCustomerDto.getPassword().equals(password)){
	 		return null;
		}
	 	return cronCustomerDto;
	 }

/*	@Override
	public void saveUser(NcoinCustomerDto user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setId(user.getId());
	}*/

	public static CronCustomerDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof MyAuthDto)
			return ((MyAuthDto) authentication).getCronCustomerDto();
		return null;
	}

	public static void setCurrentUser(CronCustomerDto cronCustomerDto) {
		((MyAuthDto)
				SecurityContextHolder.getContext().getAuthentication()).setCronCustomerDto(cronCustomerDto);
	}

}
