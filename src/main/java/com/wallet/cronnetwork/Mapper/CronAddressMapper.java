package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wallet.cronnetwork.Data.AcoinAddressVO;
import com.wallet.cronnetwork.Data.CronAddressDto;

@Mapper
public interface CronAddressMapper {

	
	@Insert("insert into cron_address_info (id, address) values (#{id}, #{address})")
	public int insertAddress(CronAddressDto dto);
}
