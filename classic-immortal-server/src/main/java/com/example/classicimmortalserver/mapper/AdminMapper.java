package com.example.classicimmortalserver.mapper;

import com.example.classicimmortalserver.entity.Admin;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("select * from `admin` where username=#{username}")
    Admin selectByUsername(String username);
}
