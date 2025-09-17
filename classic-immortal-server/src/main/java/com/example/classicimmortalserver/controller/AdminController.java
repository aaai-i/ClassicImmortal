package com.example.classicimmortalserver.controller;

import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
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
    @PutMapping("/update")
public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
}

    /**
     * 删除散修
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return  Result.success();
}

    /**
     * 批量删除散修
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatch")
public Result deleteBatch(@RequestBody List<Integer> ids){
        adminService.deleteBatch(ids);
        return Result.success();
}

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable  Integer id) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }



    /**
     * 查询所有的数据
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin){
        List<Admin> list=adminService.selectAll( admin);
        return Result.success(list);
    }































}
