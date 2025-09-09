package com.example.classicimmortalserver.controller;


import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.entity.Employee;
import com.example.classicimmortalserver.service.AdminService;
import com.example.classicimmortalserver.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

@GetMapping("/selectPage")
    public Result selectPage(Employee employee,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "4") Integer pageSize){


         PageInfo<Employee> pageInfo= employeeService.selectPage(employee,pageNum,pageSize);
         return  Result.success(pageInfo);
    }


}

































