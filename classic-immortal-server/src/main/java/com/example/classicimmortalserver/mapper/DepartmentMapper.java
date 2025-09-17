package com.example.classicimmortalserver.mapper;

import com.example.classicimmortalserver.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {

    List<Department> selectAll(Department department);

    /**
     * 增加
     * @param department
     */
    void insert(Department department);


    /**
     * 修改散修
     * @param department
     */

    void updateById(Department department);

    /**
     * 删除散修
     * @param id
     */
    @Delete("delete  from `classic_immortal`.department where  id=#{id}")
    void deleteById(Integer id);


    @Select("select  * from `classic_immortal`.department where  id=#{id}")
    Department selectById(Integer id);
}





























