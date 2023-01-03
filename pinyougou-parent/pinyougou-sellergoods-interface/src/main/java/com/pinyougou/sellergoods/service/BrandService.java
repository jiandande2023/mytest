package com.pinyougou.sellergoods.service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.model.Brand;

import java.util.List;

/***
 *
 * @Author:itheima
 * @Description:Brand的增删改查
 * @date: 2018年11月7日19:19:07
 *
 ****/
public interface BrandService {

    PageInfo<Brand> list(Integer pageNum, Integer pageSize, Brand brand);

    int add(Brand brand);

    Brand getById(Long id);

    int update(Brand brand);

    int delete(List<Long> ids);
}
