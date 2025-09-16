package com.example.classicimmortalserver.controller;

import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.entity.Article;
import com.example.classicimmortalserver.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {



    @Autowired
    private ArticleService articleService;


    /**
     * 分页
     * @param article
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectAll(Article article,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "4") Integer pageSize){
       PageInfo<Article> pageInfo =articleService.selectPage(article,pageNum,pageSize);
       return Result.success(pageInfo);
    }

    /**
     * 增加
     * @param article
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Article article){
 articleService.add(article);
 return Result.success();
    }

    /**
     * 修改散修
     * @param article
     * @return
     */
    @PutMapping("/update")
public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.success();
}

    /**
     * 删除散修
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
public Result delete(@PathVariable Integer id){
        articleService.deleteById(id);
        return  Result.success();
}

    /**
     * 批量删除散修
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatch")
public Result deleteBatch(@RequestBody List<Integer> ids){
        articleService.deleteBatch(ids);
        return Result.success();
}

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable  Integer id) {
        Article article = articleService.selectById(id);
        return Result.success(article);
    }


































}
