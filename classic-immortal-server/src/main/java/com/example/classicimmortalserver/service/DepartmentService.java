package com.example.classicimmortalserver.service;

import cn.hutool.core.util.StrUtil;
import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.entity.Department;
import com.example.classicimmortalserver.exception.CustomException;
import com.example.classicimmortalserver.mapper.DepartmentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

 @Autowired
 private DepartmentMapper departmentMapper;
 
    public  PageInfo<Department> selectPage(Department department, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Department> list=departmentMapper.selectAll(department);
        return  PageInfo.of(list);

    }
    /**
     * 新增员工
     * @param department
     * @return
     */
    public void add(Department department) {
    departmentMapper.insert(department);
    }

    /**
     * 修改散修
     * @param department
     */
    public void update(Department department) {
        departmentMapper.updateById(department);

    }

    /**
     * 删除散修
     * @param id
     */
    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }

    /**
     * 批量删除散修
     * @param ids
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public Department selectById(Integer id) {
       return departmentMapper.selectById(id);
    }


    public List<Department> selectAll(Department department) {
        List<Department> list=departmentMapper.selectAll(department);
        return list;
    }
}




















