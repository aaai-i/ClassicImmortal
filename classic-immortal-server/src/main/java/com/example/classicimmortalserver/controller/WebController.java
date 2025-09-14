package com.example.classicimmortalserver.controller;

import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.entity.Employee;
import com.example.classicimmortalserver.exception.CustomException;
import com.example.classicimmortalserver.service.AdminService;
import com.example.classicimmortalserver.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//编写登录，
@RestController
public class WebController {


    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;


    /**
     * 登录
     * @param account
     * @return
     */
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

    /**
     * 注册
     * @param employee
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Employee employee ){
    employeeService.register(employee);
    return Result.success();
    }

    @PutMapping("/updatePassword")
public Result updatePassword(@RequestBody Account account){
        if ("ADMIN".equals(account.getRole())){
            adminService.updatePassword(account);
        } else if ("EMP".equals(account.getRole())) {
            employeeService.updatePassword(account);
        }else{
            throw new CustomException("500","非法输入");
        }
        return Result.success();
}
















}





























