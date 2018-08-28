package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ResetMapper {

    @Select("SELECT count(*) FROM cron_customer WHERE id = #{id} and phone = #{phone}")
    int checkHp(@Param("id") String id, @Param("phone") String phone);

    @Update("UPDATE cron_customer SET password = #{password} WHERE id = #{id}")
    int updatePw(@Param("id") String id, @Param("password") String password);
}
