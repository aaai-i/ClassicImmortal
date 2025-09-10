package com.example.classicimmortalserver.mapper;

import com.example.classicimmortalserver.entity.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {

    @Select("select * from `classic_immortal`.admin where username=#{username}")
    Admin selectByUsername(String username);

    List<Admin> selectAll(Admin admin);

    void insert(Admin admin);

    void updateById(Admin admin);

    @Delete("select * from `classic_immortal`.admin where id=#{id}")
    void deleteById(Integer id);
}
