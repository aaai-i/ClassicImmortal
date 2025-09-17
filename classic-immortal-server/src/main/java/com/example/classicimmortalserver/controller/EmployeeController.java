package com.example.classicimmortalserver.controller;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.entity.Employee;
import com.example.classicimmortalserver.service.AdminService;
import com.example.classicimmortalserver.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

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

    /**
     * 删除散修
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
public Result delete(@PathVariable Integer id){
        employeeService.deleteById(id);
        return Result.success();
}

    /**
     * 批量删除散修
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatch")
public Result deleteBatch(@RequestBody List<Integer> ids){

        employeeService.deleteBatch(ids);
        return Result.success();
}


    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable  Integer id){
        Employee employee= employeeService.selectById(id);
        return Result.success(employee);
    }


    /**
     * 查询所有的数据
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(Employee employee){
        List<Employee> list=employeeService.selectAll( employee);
        return Result.success(list);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 1. 查询所有员工数据
        List<Employee> employeeList = employeeService.selectAll(null);

        // 2. 创建 Excel 写入器（内存中操作，不生成文件）
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 3. 设置表头中文别名（让列名变成中文）
        writer.addHeaderAlias("username", "账号");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("departmentName", "部门");
        writer.setOnlyAlias(true);
        // 4. 把数据写入 Excel
        writer.write(employeeList, true);

        // 5. 设置浏览器下载响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("员工信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        // 6. 把 Excel 写出到浏览器（下载）
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    /**
     * excel 导入
     */
    @PostMapping("/import")
    public Result importData(MultipartFile file) throws Exception {
        // 1. 拿到输入流 构建 reader
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 2. 读取 excel里面的数据
        reader.addHeaderAlias("账号", "username");
        reader.addHeaderAlias("名称", "name");
        reader.addHeaderAlias("性别", "sex");
        reader.addHeaderAlias("工号", "no");
        reader.addHeaderAlias("年龄", "age");
        reader.addHeaderAlias("个人介绍", "description");
        reader.addHeaderAlias( "部门", "departmentName");
        List<Employee> employeeList = reader.readAll(Employee.class);
        // 3. 写入list数据到数据库
        for (Employee employee : employeeList) {
            employeeService.add(employee);
        }
        return Result.success();
    }

}

































