package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.model.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.sellergoods.service.impl
 * @date 2023/1/1 18:31
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> list(Integer pageNum, Integer pageSize, Brand brand) {

        PageHelper.startPage(pageNum,pageSize);
        List<Brand> list = brandMapper.list(brand);
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(list);

        return pageInfo;
    }


    @Override
    public int add(Brand brand) {
        return brandMapper.addBrand(brand);
    }

    @Override
    public Brand getById(Long id) {
        return brandMapper.getById(id);
    }

    @Override
    public int update(Brand brand) {
        return brandMapper.updateBrand(brand);
    }

    @Override
    public int delete(List<Long> ids) {
        return brandMapper.deleteBrand(ids);
    }
}
