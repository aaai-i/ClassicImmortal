package com.example.classicimmortalserver.controller;

import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.service.AdminService;
import com.example.classicimmortalserver.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//编写登录，
@RestController
public class WebController {


    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;

@PostMapping("/login")
    public Result login(@RequestBody Account account){
        Account result =null;
        if("ADMIN".equals(account.getRole())){
            result=adminService.login(account);
        }else  if("EMP".equals(account.getRole())){
            result=employeeService.login(account);
        }
        return Result.success(result);
    }
}
