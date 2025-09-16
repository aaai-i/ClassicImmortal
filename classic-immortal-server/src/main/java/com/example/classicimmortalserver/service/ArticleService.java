package com.example.classicimmortalserver.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.example.classicimmortalserver.entity.Account;
import com.example.classicimmortalserver.entity.Article;
import com.example.classicimmortalserver.exception.CustomException;
import com.example.classicimmortalserver.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;




    /**
     * 分页功能
     * @param article
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Article> selectPage(Article article, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list=articleMapper.selectAll(article);
        return  PageInfo.of(list);


    }

    /**
     * 增加散修
     * @param article
     */
    public void add(Article article) {
        article.setTime(DateUtil.now());
        articleMapper.insert(article);
    }

    /**
     * 修改散修
     * @param article
     */
    public void update(Article article) {
        articleMapper.updateById(article);
    }

    /**
     * 删除散修
     * @param id
     */
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
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



    public Article selectById(Integer id) {
return  articleMapper.selectById(id);
    }
}

























