package com.wallet.cronnetwork.Mapper;


import com.wallet.cronnetwork.Data.AcoinAddressVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AcoinAddressMapper {

	@Select("select * from wiz_address_info where address = #{address}")
	public AcoinAddressVO select(@Param("address") String address);
}
