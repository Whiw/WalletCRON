package com.wallet.cronnetwork.Mapper;

import com.wallet.cronnetwork.Data.CronCustomerDto;
import com.wallet.cronnetwork.Data.NcoinCustomerDto;
import com.wallet.cronnetwork.Data.SmsDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CronCustomerMapper {
	//public void addcustomer(NcoinCustomerDto ncoincustomerDTO) throws RuntimeException;

	@Select("SELECT * from cron_customer where id = #{userid}")
	CronCustomerDto findById(@Param("userid") String userid) throws RuntimeException;

	@Insert("INSERT into cron_customer(id, password, country, phone, reg_date, update_date, enabled) " +
			"values(#{id}, #{password}, #{country}, #{phone}, now(), now(), 2)")
	int addcustomer(CronCustomerDto cronCustomerDto);

	@Select("SELECT count(*) from cron_customer WHERE id = #{id}")
	int idDuplicateChk(String id);

	@Insert("INSERT into cron_auth_number (mobile, otp, useYN, reg_date, update_date, ip_addr) " +
			"values(#{num}, #{code}, 'N', now(), now(), #{ip_addr})" +
			"ON DUPLICATE KEY UPDATE mobile = #{num} , otp = #{code}")
	int insertAuthNumber(SmsDto smsDto);

	@Select("SELECT count(*) from cron_auth_number WHERE otp = #{code} and mobile = #{num}")
	int chkCode(SmsDto smsDto);
}
