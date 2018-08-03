package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

    @Update("UPDATE cron_customer SET password = #{password} where id = #{id}")
    int changePassword(@Param("id")String id, @Param("password")String password);

    @Select("SELECT count(*) FROM cron_customer where phone = #{phone}")
    int checkHp(@Param("phone") String phone);

    @Update("UPDATE cron_customer SET phone = #{phone} where id = #{id}")
    int changeHp(@Param("id") String id, @Param("phone") String phone);
}
