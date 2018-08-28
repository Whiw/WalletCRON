package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wallet.cronnetwork.Data.AcoinAddressVO;
import com.wallet.cronnetwork.Data.CronAddressDto;

import java.util.ArrayList;

@Mapper
public interface CronAddressMapper {


	@Insert("INSERT into cron_address_info (id, address, reg_date) values(#{id}, #{address}, now()) ")
	int insertAddress(@Param("id")String id, @Param("address")String address);

	@Select("SELECT address from cron_address_info WHERE id = #{id}")
	ArrayList<String> getAddressList(String id);

	@Select("SELECT count(*) FROM cron.cron_address_info where address = #{address} and id = #{id}")
	int checkAddress(@Param("id")String id, @Param("address")String address);

}
