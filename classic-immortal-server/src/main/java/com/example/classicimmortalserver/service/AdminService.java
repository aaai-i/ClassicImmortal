package com.example.classicimmortalserver.service;

import cn.hutool.core.util.StrUtil;
import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.exception.CustomException;
import com.example.classicimmortalserver.mapper.AdminMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    /**
     * 登录功能
     * @param account
     * @return
     */
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

    /**
     * 分页功能
     * @param admin
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> list=adminMapper.selectAll(admin);
        return  PageInfo.of(list);


    }

    /**
     * 增加散修
     * @param admin
     */
    public void add(Admin admin) {
        String username=admin.getUsername();
        Admin dbAdmin=adminMapper.selectByUsername(username);
        if(dbAdmin!=null){
            throw new CustomException("500","账号已存在");
        }
        Admin dbAdmin1=adminMapper.selectByUsername(username);
        if (dbAdmin1!=null){
            throw new CustomException("500","工号已存在");
        }
        if (StrUtil.isBlank(admin.getPassword())){
admin.setPassword("123");
        }
        if (StrUtil.isBlank(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole("admin");
        adminMapper.insert(admin);
    }

    /**
     * 修改散修
     * @param admin
     */
    public void update(Admin admin) {
        adminMapper.updateById(admin);




    }
}
