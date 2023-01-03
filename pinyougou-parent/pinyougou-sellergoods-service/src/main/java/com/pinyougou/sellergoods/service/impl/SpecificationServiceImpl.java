package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.model.Specification;
import com.pinyougou.model.SpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author atom.hu
 * @version V1.0
 * @Package com.pinyougou.sellergoods.service.impl
 * @date 2023/1/1 22:40
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageInfo<Specification> list(Integer pageNum, Integer pageSize, Specification specification) {
        PageHelper.startPage(pageNum,pageSize);

       List<Specification> specifications =  specificationMapper.findAll(specification);
        return new PageInfo<Specification>(specifications);
    }

    @Override
    public int add(Specification specification) {
        int i =  specificationMapper.addSpecification(specification);

        for (SpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            specificationOption.setSpecId(specification.getId());
        }
        //批量增加规格选项
        specificationOptionMapper.addSpecificationOption(specification.getSpecificationOptionList());
        return i;
    }

    @Override
    public Specification getById(Long id) {
        return specificationMapper.getById(id);
    }

    @Override
    public int update(Specification specification) {
        int i = specificationMapper.updateSpecification(specification);

        for (SpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            specificationOption.setSpecId(specification.getId());
        }
        //删除之前的记录
        specificationOptionMapper.deleteBySpecId(specification.getId());
        //再新增
        specificationOptionMapper.addSpecificationOption(specification.getSpecificationOptionList());
        return i;
    }

    @Override
    public int delete(List<Long> ids) {
        int i = specificationMapper.deleteSpecification(ids);

        specificationOptionMapper.deleteSpecificationOption(ids);
        return i;
    }
}
