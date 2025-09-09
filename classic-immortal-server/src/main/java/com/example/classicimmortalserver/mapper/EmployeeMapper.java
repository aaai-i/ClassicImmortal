package com.example.classicimmortalserver.mapper;

import com.example.classicimmortalserver.entity.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {


    @Select("select * from `employee` where  username=#{username} ")
    Employee selectByUsername(String username);



    List<Employee> selectAll(Employee employee);
}





























