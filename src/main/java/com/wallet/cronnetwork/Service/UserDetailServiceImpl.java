package com.wallet.cronnetwork.Service;

import com.wallet.cronnetwork.Data.NcoinCustomerDto;
import com.wallet.cronnetwork.Mapper.NcoinCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private NcoinCustomerMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//System.out.println(mapper.findById(username).getId());
		System.out.println(username);
		NcoinCustomerDto customer = mapper.findById(username);

		if (customer == null)
			throw new UsernameNotFoundException(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new User(customer.getId(), customer.getPassword() ,grantedAuthorities);
	}

}
