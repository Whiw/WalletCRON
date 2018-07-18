package com.wallet.cronnetwork.Service;

import com.wallet.cronnetwork.Data.NcoinCustomerDto;

public interface UserService {
	
	//public void saveUser(NcoinCustomerDto user);

	NcoinCustomerDto findByUsername(String username, String password);
}
