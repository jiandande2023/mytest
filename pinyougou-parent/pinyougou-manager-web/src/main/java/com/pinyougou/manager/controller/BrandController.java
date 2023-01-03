package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.manager.controller
 * @date 2023/1/1 18:27
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /*
    * 品牌查询
    * */
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Brand> list(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "pageSize",required = false,defaultValue = "3") Integer pageSize,@RequestBody Brand brand){
        PageInfo<Brand> pageInfo = brandService.list(pageNum,pageSize,brand);
		Sysetem.out.println("测试Git的使用");
        return pageInfo;
    }

    /*
    * 品牌添加
    * */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Brand brand){
        try {
            int i = brandService.add(brand);
            if(i > 0){
                return new Result(true,"品牌添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false,"品牌添加失败");
    }

    /*
    * 根据品牌Id查询
    * */
    @RequestMapping("/one/{id}")
    @ResponseBody
    public Brand getById(@PathVariable(value = "id") Long id){
         return brandService.getById(id);
    }

    /*
    *
    * 品牌修改
    * */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Brand brand){
        try {
            int i = brandService.update(brand);
            if(i > 0){
                return new Result(true,"品牌修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false,"品牌修改失败");
    }

    /*
    * 品牌删除
    * */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody List<Long> ids){
        try {
            int i = brandService.delete(ids);
            if(i > 0){
                return new Result(true,"品牌删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false,"品牌删除失败");
    }
}
