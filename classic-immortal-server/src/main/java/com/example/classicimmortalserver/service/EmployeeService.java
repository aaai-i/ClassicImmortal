package com.example.classicimmortalserver.service;

import cn.hutool.core.util.StrUtil;
import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.entity.Admin;
import com.example.classicimmortalserver.entity.Employee;
import com.example.classicimmortalserver.exception.CustomException;
import com.example.classicimmortalserver.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class EmployeeService {

 @Autowired
 private EmployeeMapper employeeMapper;




    public Employee login(Account account) {
        String username=account.getUsername();
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if(dbEmployee==null){
            throw new CustomException("500","账号不存在"+account.getUsername());
        }
        //这里记一下，账号是账号，密码是密码.上面判断为空了，下面判断就是不为空时候与数据库里面的密码匹配不匹配
       String password= account.getPassword();
        if(!dbEmployee.getPassword().equals(password)){
            throw  new CustomException("500","账号或密码错误");

        }
        return dbEmployee;
    }




    public  PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Employee> list=employeeMapper.selectAll(employee);
        return  PageInfo.of(list);

    }
    /**
     * 新增员工
     * @param employee
     * @return
     */
    public void add(Employee employee) {
        String username=employee.getUsername();
      Employee dbEmployee=employeeMapper.selectByUsername(username);
      if(dbEmployee!=null){
          throw new CustomException("500","账号已存在，请更换别的账号");
      }
      Employee dbEmployee1=employeeMapper.selectByUsername(username);
    if (dbEmployee1!=null){
        throw new CustomException("500","工号已存在，请注意");
    }
    if (StrUtil.isBlank(employee.getPassword())){
        employee.setPassword("123");

    }
    if (StrUtil.isBlank(employee.getName())){
        employee.setName(employee.getUsername());

    }
    employee.setRole("EMP");
    employeeMapper.insert(employee);


    }

    /**
     * 修改散修
     * @param employee
     */
    public void update(Employee employee) {
        employeeMapper.updateById(employee);

    }
}




















