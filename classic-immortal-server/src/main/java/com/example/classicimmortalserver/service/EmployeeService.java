package com.example.classicimmortalserver.service;

import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.entity.Employee;
import com.example.classicimmortalserver.exception.CustomException;
import com.example.classicimmortalserver.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class EmployeeService {

 @Autowired
 private EmployeeMapper employeeMapper;



    public Employee login(Account account) {
        String username=account.getUsername();
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if(dbEmployee==null){
            throw new CustomException("500","账号不存在"+account.getUsername());
        }
        //这里记一下，账号是账号，密码是密码.上面判断为空了，下面判断就是不为空时候与数据库里面的密码匹配不匹配
       String password= account.getPassword();
        if(!dbEmployee.getPassword().equals(password)){
            throw  new CustomException("500","账号或密码错误");

        }
        return dbEmployee;
    }
}
