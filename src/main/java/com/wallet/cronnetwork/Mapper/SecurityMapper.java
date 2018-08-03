package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SecurityMapper {

    @Select("SELECT otp_key from cron_customer where id = #{id} and otp = 1")
    String getOtpKey(String id);

    @Insert("UPDATE cron_customer SET otp_key = #{encodedKey}, otp = 1 where id = #{id}")
    int insertOTP(@Param("id")String id, @Param("encodedKey")String encodedKey);

    @Delete("UPDATE cron_customer SET otp_key = '', otp = 0 where id = #{id}")
    int deteteOTP(@Param("id")String id);
}
