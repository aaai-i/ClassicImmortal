package com.example.classicimmortalserver.mapper;

import com.example.classicimmortalserver.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectAll(Article article);

    void insert(Article article);

    void updateById(Article article);

    @Delete("delete from `classic_immortal`.article where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from `classic_immortal`.article where  id=#{id}")
    Article selectById(Integer id);
}

































