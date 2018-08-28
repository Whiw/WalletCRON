package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("SELECT otp_key from cron_customer where id = #{id} and otp = 1")
    String getOtpKey(String id);
}
