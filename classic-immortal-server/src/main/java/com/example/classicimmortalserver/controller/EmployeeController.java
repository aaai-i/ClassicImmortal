package com.example.classicimmortalserver.controller;


import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.entity.Employee;
import com.example.classicimmortalserver.service.AdminService;
import com.example.classicimmortalserver.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 分页
     * @param employee
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Employee employee,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "4") Integer pageSize){


         PageInfo<Employee> pageInfo= employeeService.selectPage(employee,pageNum,pageSize);
         return  Result.success(pageInfo);
    }


    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Employee employee){
    employeeService.add(employee);
    return Result.success();
    }


    /**
     * 修改散修
     * @param employee
     * @return
     */
    @PutMapping("/update")
public Result update(@RequestBody Employee employee){
        employeeService.update(employee);
        return Result.success();
}



}

































