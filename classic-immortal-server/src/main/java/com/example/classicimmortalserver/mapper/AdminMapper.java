package com.example.classicimmortalserver.mapper;

import com.example.classicimmortalserver.entity.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {

    @Select("select * from `admin` where username=#{username}")
    Admin selectByUsername(String username);

    List<Admin> selectAll(Admin admin);
}
