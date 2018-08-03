package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SecPwMapper {
    @Select("SELECT count(*) FROM cron_customer_sec_pw where id = #{id}")
    int checkSecPW(@Param("id")String id);

    @Insert("INSERT into cron_customer_sec_pw(id, sec_pw, reg_date, updated) values(#{id}, #{sPw}, now(), now())")
    int insertSecPw(@Param("id")String id, @Param("sPw")String sPw);

    @Update("DELETE FROM cron_customer_sec_pw WHERE id IN (" +
            "    SELECT * FROM (" +
            "        SELECT id FROM cron_customer_sec_pw where id = #{id} and sec_pw = #{sPw}" +
            "    ) AS p)")
    int deleteSecPw(@Param("id") String id, @Param("sPw")String sPw);
}
