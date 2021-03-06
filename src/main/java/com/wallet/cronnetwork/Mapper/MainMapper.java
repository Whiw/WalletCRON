package com.wallet.cronnetwork.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MainMapper {

    @Select("Select address from cron_address_info where id = #{id}")
    ArrayList<String> getAddress(String id);
}
