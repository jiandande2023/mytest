package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.http.Result;
import com.pinyougou.model.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.manager.controller
 * @date 2023/1/1 22:38
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

    @RequestMapping("/list")
    public PageInfo<Specification> list(@RequestParam(value = "pageNum",required = false,defaultValue = "1" ) Integer pageNum,
                                        @RequestParam(value = "PageSize",required = false,defaultValue = "3") Integer pageSize,
                                        @RequestBody Specification specification){
        return specificationService.list(pageNum,pageSize,specification);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Specification specification){
        try {
            int i = specificationService.add(specification);
            if (i > 0) {
                return new Result(true,"规格添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"规格添加失败");
    }
    /*
    * 根据Id查询规格信息
    * */
    @RequestMapping(value = "/one/{id}",method = RequestMethod.POST)
    public Specification getById(@PathVariable(value = "id") Long id){
        return specificationService.getById(id);
    }

    /*
    * 修改规格
    * */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Specification specification){
        try {
            int i = specificationService.update(specification);
            if (i > 0) {
                return new Result(true,"规格修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"规格修改失败");
    }

    /*
     * 删除规格
     * */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody List<Long> ids){
        try {
            int i = specificationService.delete(ids);
            if (i > 0) {
                return new Result(true,"规格删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,"规格删除失败");
    }
}
