package com.example.classicimmortalserver.service;

import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.exception.CustomException;
import com.example.classicimmortalserver.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    public Admin login(Account account) {
        String username=account.getUsername();
        Admin dbAdmin=adminMapper.selectByUsername(username);
        if(dbAdmin==null){
            throw new CustomException("500","账号不存在");
        }
       String password=account.getPassword();
        if(!dbAdmin.getPassword().equals(password)){
            throw new CustomException("500","账号或密码错误");
        }
        return dbAdmin;

    }
}
