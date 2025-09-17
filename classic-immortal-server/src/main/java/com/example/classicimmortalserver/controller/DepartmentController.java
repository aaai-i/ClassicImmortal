package com.example.classicimmortalserver.controller;


import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Department;
import com.example.classicimmortalserver.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    /**
     * 分页
     * @param department
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Department department,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "4") Integer pageSize){


         PageInfo<Department> pageInfo= departmentService.selectPage(department,pageNum,pageSize);
         return  Result.success(pageInfo);
    }


    /**
     * 新增员工
     * @param department
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Department department){
    departmentService.add(department);
    return Result.success();
    }


    /**
     * 修改散修
     * @param department
     * @return
     */
    @PutMapping("/update")
public Result update(@RequestBody Department department){
        departmentService.update(department);
        return Result.success();
}

    /**
     * 删除散修
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
public Result delete(@PathVariable Integer id){
        departmentService.deleteById(id);
        return Result.success();
}

    /**
     * 批量删除散修
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatch")
public Result deleteBatch(@RequestBody List<Integer> ids){

        departmentService.deleteBatch(ids);
        return Result.success();
}


    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable  Integer id){
        Department department= departmentService.selectById(id);
        return Result.success(department);
    }

    @GetMapping("/selectAll")
public Result selectAll(Department department){
        List<Department> list=departmentService.selectAll(department);
        return Result.success(list);
}


}

































