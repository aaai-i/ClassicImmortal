package com.example.classicimmortalserver.controller;

import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
public class AdminController {



    @Autowired
    private AdminService adminService;


    /**
     * 分页
     * @param admin
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectAll(Admin admin,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "4") Integer pageSize){
       PageInfo<Admin> pageInfo =adminService.selectPage(admin,pageNum,pageSize);
       return Result.success(pageInfo);
    }

    /**
     * 增加
     * @param admin
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin){
 adminService.add(admin);
 return Result.success();
    }

    /**
     * 修改散修
     * @param admin
     * @return
     */
    @GetMapping("/update")
public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
}











































}
