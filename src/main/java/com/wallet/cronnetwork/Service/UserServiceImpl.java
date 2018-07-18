package com.wallet.cronnetwork.Service;

import com.wallet.cronnetwork.Data.MyAuthDto;
import com.wallet.cronnetwork.Data.NcoinCustomerDto;
import com.wallet.cronnetwork.Mapper.NcoinCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private NcoinCustomerMapper ncoinCustomerMapper;

	 public NcoinCustomerDto findByUsername(String username, String password) {

	 	NcoinCustomerDto ncoinCustomerDto = ncoinCustomerMapper.findById(username);
	 	if(ncoinCustomerDto == null || !ncoinCustomerDto.getPassword().equals(password)){
	 		return null;
		}
	 	return ncoinCustomerDto;
	 }

/*	@Override
	public void saveUser(NcoinCustomerDto user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setId(user.getId());
	}*/

	public static NcoinCustomerDto getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof MyAuthDto)
			return ((MyAuthDto) authentication).getNcoinCustomerDto();
		return null;
	}

	public static void setCurrentUser(NcoinCustomerDto ncoinCustomerDto) {
		((MyAuthDto)
				SecurityContextHolder.getContext().getAuthentication()).setNcoinCustomerDto(ncoinCustomerDto);
	}
}
