package com.example.classicimmortalserver.mapper;

import com.example.classicimmortalserver.entity.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {


    @Select("select * from `classic_immortal`.employee where  username=#{username} ")
    Employee selectByUsername(String username);



    List<Employee> selectAll(Employee employee);


    /**
     * 增加
     * @param employee
     */
    void insert(Employee employee);
}





























