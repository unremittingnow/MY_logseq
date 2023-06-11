package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理
 */
@RestController
//在Spring中@RestController的作用等同于@Controller + @ResponseBody
//@ResponseBody接收数据为JSON格式
@RequestMapping("/category")
//请求的路径就是/category
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        //传入参数是一个Category类型的
        //拿到页面传回的JSON格式数据用@RequestBody注解
        //返回类R的泛型根据list.html的回调数据res，看res所用到的属性只有code ==> 所以为String
        log.info("category:{}",category);
        categoryService.save(category);
        //直接调用categoryService的方法save保存传入的category对象==>保存新增分类
        return R.success("新增分类成功");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        //分页构造器
        Page<Category> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);
        //双冒号是方法调用

        //分页查询
        categoryService.page(pageInfo,queryWrapper);
        //分页查询的第一个参数是分页构造器，第二个参数是条件构造器
        return R.success(pageInfo);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        //返回信息res只需要id属性
        //Long id参数没有加注解，因为传回是用url地址+？id=的方式,可以
        log.info("删除分类，id为：{}",id);

        //categoryService.removeById(id);
        categoryService.remove(id);

        return R.success("分类信息删除成功");
    }

    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);

        categoryService.updateById(category);

        return R.success("修改分类信息成功");
    }
}
