package com.wallet.cronnetwork.Service;

import com.wallet.cronnetwork.Data.CronCustomerDto;
import com.wallet.cronnetwork.Data.NcoinCustomerDto;

public interface CUserService {
	
	CronCustomerDto findByUsername(String username, String password);

}
