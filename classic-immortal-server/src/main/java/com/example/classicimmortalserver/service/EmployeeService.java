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
      Employee dbEmployee1=employeeMapper.selectByNo(employee.getNo());
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

    /**
     * 删除散修
     * @param id
     */
    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
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

    /**
     * 注册
     * @param employee
     */
    public void register(Employee employee) {
        this.add(employee);
    }

    public void updatePassword(Account account) {
        Integer id = account.getId();
        Employee employee=this.selectById(id);
        if(!employee.getPassword().equals(account.getPassword())){
            throw new CustomException("500","对不起,原密码错误");
        }
        employee.setPassword(account.getNewPassword());
        this.update(employee);
    }

    public Employee selectById(Integer id) {
       return employeeMapper.selectById(id);
    }
}




















